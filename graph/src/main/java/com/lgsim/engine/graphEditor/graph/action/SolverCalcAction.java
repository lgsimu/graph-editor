package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocument;
import com.lgsim.engine.graphEditor.api.graph.IGraphEditor;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SolverCalcAction extends AbstractAction
{
  private static final Logger log = LoggerFactory.getLogger(SolverCalcAction.class);
  private ISolverEnvironment env;
  private IGraphEditor editor;


  public SolverCalcAction(@NotNull ISolverEnvironment env, @NotNull IGraphEditor editor)
  {
    this.env = env;
    this.editor = editor;
  }


  @Override
  public void actionPerformed(ActionEvent evt)
  {
    log.debug("perform solver calc action");
    ISolver solver = ImplementationContext.INSTANCE.getSolver();
    try
    {
      IGraph graph = solver.calc(env);
      IGraphDocument document = editor.getCurrentGraphDocument();
      if (document != null)
      {
        document.setGraph(graph);
      }
    }
    catch (CalcException e)
    {
      ExceptionManager.INSTANCE.dealWith(e);
    }
  }
}