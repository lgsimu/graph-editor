package com.lgsim.engine.graphEditor.graph.action;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.calc.SolverEnvironment;
import com.lgsim.engine.graphEditor.graph.document.Document;
import com.lgsim.engine.graphEditor.ui.SolverSettingsDialog;
import com.lgsim.engine.graphEditor.ui.bean.SolverSettingsBean;
import com.lgsim.engine.graphEditor.util.Configuration;
import org.apache.commons.lang.SerializationUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class SolverSettingAction extends SolverAction {

  private static final Logger log = LoggerFactory.getLogger("graph.action.SolverSettingAction");
  private Configuration configuration;


  @SuppressWarnings("WeakerAccess")
  public SolverSettingAction(@NotNull Document document) {
    super(document);
    configuration = document.getApplication().getConfiguration();
  }


  @Override
  public void actionPerformed(ActionEvent event)
  {
    log.debug("perform setting action");
    SolverSettingsDialog dialog = new SolverSettingsDialog(this::consumeSettings);
    Consumer<SolverSettingsBean> dialogInput = dialog.dialogInput();
    try {
      SolverSettingsBean bean = loadSettings();
      if (bean != null) {
        consumeSettings(bean);
        dialogInput.accept(bean);
        dialog.dispose();
      }
      else {
        dialog.pack();
        dialog.setVisible(true);
      }
    }
    catch (IOException e) {
      log.debug("{}", e);
    }
  }


  private void consumeSettings(@NotNull SolverSettingsBean bean) {
    try {
      bean.setCaseName(document.getTitle());
      SolverEnvironment env = new SolverEnvironment(bean.getCaseName(), new File(bean.getExecutable()),
                                                    bean.getArguments(), document.getGraph());
      document.getApplication().setSolverEnvironment(env);
      saveSettings(bean);
    }
    catch (IOException e) {
      log.debug("{}", e);
    }
  }


  private void saveSettings(@NotNull SolverSettingsBean bean) throws IOException {
    File file = getSettingsFile();
    byte[] bytes = SerializationUtils.serialize(bean);
    Files.write(bytes, file);
  }


  private @Nullable SolverSettingsBean loadSettings() throws IOException {
    File file = getSettingsFile();
    if (file.exists()) {
      byte[] bytes = Files.toByteArray(file);
      Object o = SerializationUtils.deserialize(bytes);
      if (o instanceof SolverSettingsBean) {
        return (SolverSettingsBean) o;
      }
    }
    return null;
  }


  private @NotNull File getSettingsFile() {
    File dir = configuration.getSolverDirectory();
    return new File(dir, "calcEnv.bin");
  }
}
