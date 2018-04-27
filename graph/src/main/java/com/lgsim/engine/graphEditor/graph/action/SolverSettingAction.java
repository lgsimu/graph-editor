package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.api.calc.SolverEnvironment;
import com.lgsim.engine.graphEditor.graph.document.Document;
import com.lgsim.engine.graphEditor.ui.SolverSettingsDialog;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.io.File;

@SuppressWarnings("WeakerAccess")
public class SolverSettingAction extends SolverAction {

  private static final Logger log = LoggerFactory.getLogger("graph.action.SolverSettingAction");


  public SolverSettingAction(@NotNull Document document) {
    super(document);
  }


  @Override
  public void actionPerformed(ActionEvent e)
  {
    log.debug("perform setting action");
    SolverSettingsDialog dialog = new SolverSettingsDialog(this::updateCalcEnv);
    dialog.pack();
    dialog.setVisible(true);
  }


  private void updateCalcEnv(@NotNull SolverSettingsDialog dialog) {
    String executable = dialog.getExecutable();
    String arguments = dialog.getArguments();
    SolverEnvironment env = new SolverEnvironment();
    env.setExecutableFile(new File(executable));
    env.setSolverCommandlineArguments(arguments);
    env.setGraph(document.getGraph());
    env.setCaseName(document.getTitle());
    document.getApplication().setSolverEnvironment(env);
  }
}
