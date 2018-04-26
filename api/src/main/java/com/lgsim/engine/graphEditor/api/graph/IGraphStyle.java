package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public interface IGraphStyle extends Serializable
{
  @NotNull List<IVertexStyle> getVertexStyles();
}
