package com.lgsim.engine.graphEditor.graph.editor.action;

import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.calc.impl.SolverEnvironmentImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SolverCalcAction extends AbstractAction
{
  private static final Logger log = LoggerFactory.getLogger(SolverCalcAction.class);


  @Override
  public void actionPerformed(ActionEvent e)
  {
    log.debug("perform solver calc action");
//    ISolver solver = ImplementationContext.INSTANCE.getSolver();
//    ISolverEnvironment env = createSolverEnv();
//    int status = solver.calc(env);
//    log.debug("calc status {}", status);
  }


  @NotNull
  private ISolverEnvironment createSolverEnv()
  {
    SolverEnvironmentImpl env = new SolverEnvironmentImpl();
    env.setCaseName("test1");
    env.setExecutableFile(new File(""));
    env.setSolverInputFile(new File(""));
    return env;
  }
}
