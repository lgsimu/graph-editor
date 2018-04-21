package com.lgsim.engine.graphEditor.graph.editor.action;

import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.calc.impl.SolverEnvironmentImpl;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
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
  public void actionPerformed(ActionEvent evt)
  {
    log.debug("perform solver calc action");
    ISolver solver = ImplementationContext.INSTANCE.getSolver();
    ISolverEnvironment env = createSolverEnv();
    try
    {
      solver.calc(env);
    }
    catch (CalcException e)
    {
      ExceptionManager.INSTANCE.dealWith(e);
    }
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
