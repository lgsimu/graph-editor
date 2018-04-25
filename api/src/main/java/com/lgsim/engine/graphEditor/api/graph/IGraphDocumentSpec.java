package com.lgsim.engine.graphEditor.api.graph;

import com.lgsim.engine.graphEditor.util.Configuration;
import org.jetbrains.annotations.NotNull;

public interface IGraphDocumentSpec {
  @NotNull String getImplementationTitle();


  @NotNull String getImplementationVersion();


  @NotNull String getImplementationVendor();


  @NotNull Configuration getConfiguration();
}
