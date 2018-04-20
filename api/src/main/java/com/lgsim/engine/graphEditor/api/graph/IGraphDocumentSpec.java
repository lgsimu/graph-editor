package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.NotNull;

public interface IGraphDocumentSpec
{
  @NotNull String getImplementationTitle();

  @NotNull String getImplementationVersion();

  @NotNull String getImplementationVendor();
}
