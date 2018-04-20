package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IGraphStyle
{
  @NotNull List<IVertexStyle> getVertexStyles();
}
