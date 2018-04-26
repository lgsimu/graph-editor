package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"WeakerAccess", "unused"})
public interface IGraphDocumentFile {

  @NotNull String getEntryFile();


  @NotNull String getModelFile();


  @NotNull String getStyleFile();
}
