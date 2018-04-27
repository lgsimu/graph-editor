package com.lgsim.engine.graphEditor.graph.action;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.calc.SolverEnvironment;
import com.lgsim.engine.graphEditor.graph.document.Document;
import com.lgsim.engine.graphEditor.ui.SolverSettingsDialog;
import com.lgsim.engine.graphEditor.ui.bean.SolverSettingsBean;
import com.lgsim.engine.graphEditor.util.Configuration;
import org.apache.commons.lang.SerializationUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SolverSettingAction extends SolverAction {

  private static final Logger log = LoggerFactory.getLogger("graph.action.SolverSettingAction");
  private SolverSettingsBean settingsBean;


  private static @Nullable SolverSettingsBean loadSettings(@NotNull File solverDirectory) {
    File file = getSettingsFile(solverDirectory);
    if (file.exists()) {
      try {
        byte[] bytes = Files.toByteArray(file);
        Object o = SerializationUtils.deserialize(bytes);
        if (o instanceof SolverSettingsBean) {
          return (SolverSettingsBean) o;
        }
      }
      catch (IOException e) {
        return null;
      }
    }
    return null;
  }


  @Contract(pure = true)
  private static void saveSettings(@NotNull SolverSettingsBean bean, @NotNull File solverDirectory) throws IOException {
    File file = getSettingsFile(solverDirectory);
    byte[] bytes = SerializationUtils.serialize(bean);
    Files.write(bytes, file);
  }


  private static @NotNull File getSettingsFile(@NotNull File solverDirectory) {
    return new File(solverDirectory, "calcEnv.bin");
  }


  @SuppressWarnings("WeakerAccess")
  public SolverSettingAction(@NotNull Document document) {
    super(document);
    Configuration configuration = document.getApplication().getConfiguration();
    this.settingsBean = loadSettings(configuration.getSolverDirectory());
  }


  @Override
  public void actionPerformed(ActionEvent event)
  {
    if (settingsBean == null) {
      showSettingsDialog();
    }
    else {
      setSolverEnvironment(document, settingsBean);
    }
  }


  private static void setSolverEnvironment(@NotNull Document document,
                                           @NotNull SolverSettingsBean bean)
  {
    SolverEnvironment env = new SolverEnvironment(bean.getCaseName(), new File(bean.getExecutable()),
                                                  bean.getArguments(), document.getGraph());
    document.getApplication().setSolverEnvironment(env);
  }


  private void showSettingsDialog() {
    Configuration configuration = document.getApplication().getConfiguration();
    BiConsumer<SolverSettingsDialog, SolverSettingsBean> settingsFun =
        genConsumeSettingsFun(document, configuration.getSolverDirectory(), this::setSettingsBean);
    SolverSettingsDialog dialog = new SolverSettingsDialog(settingsFun);
    dialog.pack();
    dialog.setVisible(true);
  }


  private static BiConsumer<SolverSettingsDialog, SolverSettingsBean> genConsumeSettingsFun(@NotNull Document document,
                                                                                            @NotNull File solverDirectory,
                                                                                            @NotNull Consumer<SolverSettingsBean> settingsBean)
  {
    return (dialog, bean) -> {
      try {
        bean.setCaseName(document.getTitle());
        settingsBean.accept(bean);
        dialog.updateUI().accept(bean);
        dialog.dispose();
        SolverEnvironment env = new SolverEnvironment(bean.getCaseName(), new File(bean.getExecutable()),
                                                      bean.getArguments(), document.getGraph());
        document.getApplication().setSolverEnvironment(env);
        saveSettings(bean, solverDirectory);
      }
      catch (IOException e) {
        log.debug("{}", e);
      }
    };
  }


  @SuppressWarnings("WeakerAccess")
  public void setSettingsBean(SolverSettingsBean settingsBean) {
    this.settingsBean = settingsBean;
  }
}
