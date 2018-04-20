package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface IGraphDocumentFile
{
  @NotNull File getEntryFile();


  @NotNull File getModelFile();


  @NotNull File getStyleFile();
}
