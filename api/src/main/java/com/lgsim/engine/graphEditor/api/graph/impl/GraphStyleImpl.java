package com.lgsim.engine.graphEditor.api.graph.impl;

import com.lgsim.engine.graphEditor.api.graph.IGraphStyle;
import com.lgsim.engine.graphEditor.api.graph.IVertexStyle;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Vector;

public class GraphStyleImpl implements IGraphStyle
{
  private List<IVertexStyle> vertexStyles;


  public GraphStyleImpl()
  {
    setVertexStyles(new Vector<>());
  }


  public GraphStyleImpl(List<IVertexStyle> vertexStyles)
  {
    setVertexStyles(vertexStyles);
  }


  @NotNull
  @Override
  public List<IVertexStyle> getVertexStyles()
  {
    return vertexStyles;
  }


  public void setVertexStyles(List<IVertexStyle> vertexStyles)
  {
    this.vertexStyles = vertexStyles;
  }
}
