package com.lgsim.engine.graphEditor.graph.action;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.calc.SolverEnvironment;
import com.lgsim.engine.graphEditor.graph.document.Document;
import com.lgsim.engine.graphEditor.ui.SolverSettingsDialog;
import com.lgsim.engine.graphEditor.ui.bean.SolverEnvBean;
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

@SuppressWarnings("WeakerAccess")
public class SolverSettingAction extends SolverAction {

  private static final Logger log = LoggerFactory.getLogger("graph.action.SolverSettingAction");
  private Configuration configuration;


  public SolverSettingAction(@NotNull Document document) {
    super(document);
    configuration = document.getApplication().getConfiguration();
  }


  @Override
  public void actionPerformed(ActionEvent event)
  {
    log.debug("perform setting action");
    SolverSettingsDialog dialog = new SolverSettingsDialog(this::updateCalcEnv);
    Consumer<SolverEnvBean> hook = dialog.getBeanHook();
    try {
      SolverEnvBean bean = loadSolverSettings();
      if (bean != null) {
        hook.accept(bean);
      }
    }
    catch (IOException e) {
      log.debug("{}", e);
    }
    dialog.pack();
    dialog.setVisible(true);
  }


  private void updateCalcEnv(@NotNull SolverSettingsDialog dialog) {
    try {
      String executable, arguments;
      executable = dialog.getExecutable();
      arguments = dialog.getArguments();
      SolverEnvironment env = new SolverEnvironment();
      env.setExecutableFile(new File(executable));
      env.setSolverCommandlineArguments(arguments);
      env.setGraph(document.getGraph());
      env.setCaseName(document.getTitle());
      document.getApplication().setSolverEnvironment(env);
      saveSolverSettings(env);
    }
    catch (IOException e) {
      log.debug("{}", e);
    }
  }


  private File getCalcEnvFile() {
    File dir = configuration.getSolverDirectory();
    return new File(dir, "calcEnv.bin");
  }


  private void saveSolverSettings(@NotNull SolverEnvironment env) throws IOException {
    File file = getCalcEnvFile();
    SolverEnvBean bean = new SolverEnvBean();
    bean.setCaseName(env.getCaseName());
    bean.setExecutable(env.getExecutableFile().getPath());
    bean.setArguments(env.getSolverCommandlineArguments());
    byte[] bytes = SerializationUtils.serialize(bean);
    Files.write(bytes, file);
  }


  private @Nullable SolverEnvBean loadSolverSettings() throws IOException {
    File file = getCalcEnvFile();
    if (file.exists()) {
      byte[] bytes = Files.toByteArray(file);
      Object o = SerializationUtils.deserialize(bytes);
      if (o instanceof SolverEnvBean) {
        return (SolverEnvBean) o;
      }
    }
    return null;
  }
}
