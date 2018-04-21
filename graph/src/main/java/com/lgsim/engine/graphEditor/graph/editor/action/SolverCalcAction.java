package com.lgsim.engine.graphEditor.graph.editor.action;

import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.api.graph.IGraphEditor;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.lgsim.engine.graphEditor.util.CollectionUtil;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

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
      final List<IVertexOutput> outputs = solver.calc(env);
      final IVertex currentVertex = editor.getCurrentVertex();
      VertexImpl vertex = new VertexImpl();
      vertex.setOutputs(outputs);
      if (currentVertex != null)
      {
        IVertex cloneVertex = CollectionUtil.clone(currentVertex);
        vertex.setID(cloneVertex.getID());
        vertex.setTypeID(cloneVertex.getTypeID());
        vertex.setCavity(cloneVertex.isCavity());
        vertex.setArguments(cloneVertex.getArguments());
        vertex.setInputPorts(cloneVertex.getInputPorts());
        vertex.setOutputPorts(cloneVertex.getOutputPorts());
      }
      editor.renderVertex(vertex);
    }
    catch (CalcException e)
    {
      ExceptionManager.INSTANCE.dealWith(e);
    }
  }
}
