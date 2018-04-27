package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.lgsim.engine.graphEditor.graph.document.Document;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;

public class SolverCalcAction extends SolverAction {

  private static final Logger log = LoggerFactory.getLogger(SolverCalcAction.class);


  public SolverCalcAction(@NotNull Document document) {
    super(document);
  }


  @Override
  public void actionPerformed(ActionEvent evt)
  {
    log.debug("perform solver calc action");
    ISolver solver = ImplementationContext.INSTANCE.getSolver();
    try {
      ISolverEnvironment env = document.getApplication().getSolverEnvironment();
      IGraph graph = solver.calc(env);
      if (document != null) {
        document.setGraph(graph);
      }
    }
    catch (CalcException e) {
      ExceptionManager.INSTANCE.dealWith(e);
    }
  }
}
