package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.Nullable;

import java.io.File;

public interface IGraphDocumentFile
{
  @Nullable File getEntryFile();


  @Nullable File getModelFile();


  @Nullable File getStyleFile();
}
