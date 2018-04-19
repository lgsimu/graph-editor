package com.lgsim.engine.graphEditor.graph;

import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import org.jetbrains.annotations.NotNull;

public class ImplementationContext
{
  public static final ImplementationContext INSTANCE = new ImplementationContext();
  private IStencilContext stencilContext;
  private IVertexTable vertexTable;


  private ImplementationContext()
  {
    try
    {
      stencilContext = ImplementationUtil.getInstanceOf(IStencilContext.class);
      vertexTable = ImplementationUtil.getInstanceOf(IVertexTable.class);
    }
    catch (InstantiationException e)
    {
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
}
