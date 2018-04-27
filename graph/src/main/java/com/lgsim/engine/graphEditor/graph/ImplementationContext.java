package com.lgsim.engine.graphEditor.graph;

import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.data.IGraphCodec;
import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.widget.IApplicationToolbar;
import com.lgsim.engine.graphEditor.api.widget.console.IConsole;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import org.jetbrains.annotations.NotNull;

public class ImplementationContext {

  public static final ImplementationContext INSTANCE = new ImplementationContext();
  private IStencilContext stencilContext;
  private IVertexTable vertexTable;
  private IGraphCodec graphCodec;
  private ISolver solver;
  private IApplicationToolbar applicationToolbar;
  private IConsole console;


  private ImplementationContext()
  {
    try {
      stencilContext = ImplementationUtil.getInstanceOf(IStencilContext.class);
      vertexTable = ImplementationUtil.getInstanceOf(IVertexTable.class);
      graphCodec = ImplementationUtil.getInstanceOf(IGraphCodec.class);
      solver = ImplementationUtil.getInstanceOf(ISolver.class);
      applicationToolbar = ImplementationUtil.getInstanceOf(IApplicationToolbar.class);
    }
    catch (InstantiationException e) {
      ExceptionManager.INSTANCE.dealWith(e);
    }
  }


  @NotNull
  public IStencilContext getStencilContext()
  {
    return stencilContext;
  }


  @NotNull
  public IVertexTable getVertexTable()
  {
    return vertexTable;
  }


  public @NotNull IGraphCodec getGraphCodec()
  {
    return graphCodec;
  }


  public @NotNull ISolver getSolver()
  {
    return solver;
  }


  public IApplicationToolbar getApplicationToolbar() {
    return applicationToolbar;
  }


  public IConsole getConsole() {
    return console;
  }
}
