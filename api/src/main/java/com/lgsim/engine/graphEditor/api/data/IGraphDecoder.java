package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;

public interface IGraphDecoder
{
  @NotNull IGraph decode(@NotNull Serializable serializable) throws IOException, ClassNotFoundException;
}
