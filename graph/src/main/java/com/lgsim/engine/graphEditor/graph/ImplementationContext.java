package com.lgsim.engine.graphEditor.graph;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphEncoder;
import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocumentFile;
import com.lgsim.engine.graphEditor.api.graph.IGraphStyle;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import org.jetbrains.annotations.NotNull;

public class ImplementationContext
{
  public static final ImplementationContext INSTANCE = new ImplementationContext();
  private IStencilContext stencilContext;
  private IVertexTable vertexTable;
  private IGraphEncoder graphEncoder;
  private IGraphDocumentFile graphDocumentFile;
  private IGraph graph;
  private IGraphStyle graphStyle;


  private ImplementationContext()
  {
    try
    {
      stencilContext = ImplementationUtil.getInstanceOf(IStencilContext.class);
      vertexTable = ImplementationUtil.getInstanceOf(IVertexTable.class);
      graphEncoder = ImplementationUtil.getInstanceOf(IGraphEncoder.class);
      graphDocumentFile = ImplementationUtil.getInstanceOf(IGraphDocumentFile.class);
      graph = ImplementationUtil.getInstanceOf(IGraph.class);
      graphStyle = ImplementationUtil.getInstanceOf(IGraphStyle.class);
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


  public @NotNull IGraphEncoder getGraphEncoder()
  {
    return graphEncoder;
  }


  public @NotNull IGraphDocumentFile getGraphDocumentFile()
  {
    return graphDocumentFile;
  }


  public @NotNull IGraph getGraph()
  {
    return graph;
  }


  public @NotNull IGraphStyle getGraphStyle()
  {
    return graphStyle;
  }
}
