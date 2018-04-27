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


  @SuppressWarnings("WeakerAccess")
  public SolverCalcAction(@NotNull Document document) {
    super(document);
  }


  @Override
  public void actionPerformed(ActionEvent event)
  {
    final Document document = getDocument();
    ISolverEnvironment env = document.getApplication().getSolverEnvironment();
    if (env != null) {
      calc(document, env);
    }
    else {
      performCalcSettingAction(event);
    }
  }


  private void calc(@NotNull Document document, @NotNull ISolverEnvironment env) {
    try {
      ISolver solver = ImplementationContext.INSTANCE.getSolver();
      IGraph graphBean = solver.calc(env);
      IGraph graphModel = document.getGraph();
      graphModel.retrieveCalcOutputs(graphBean);
      log.debug("The calc task has finished, update graph model");
    }
    catch (CalcException e) {
      // TODO: use IConsole notify user
      ExceptionManager.INSTANCE.dealWith(e);
    }
  }


  private void performCalcSettingAction(@NotNull ActionEvent evt) {
    ActionEvent actionEvent = ActionSupport.createActionEvent(document.getSwingComponent(), evt);
    document.getApplication().getApplicationAction().getSolverSettingAction().actionPerformed(actionEvent);
  }
}
