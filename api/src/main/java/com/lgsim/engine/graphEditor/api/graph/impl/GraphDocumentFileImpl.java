package com.lgsim.engine.graphEditor.api.graph.impl;

import com.lgsim.engine.graphEditor.api.graph.IGraphDocumentFile;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public class GraphDocumentFileImpl implements IGraphDocumentFile
{
  private File entryFile;
  private File modelFile;
  private File styleFile;


  public GraphDocumentFileImpl()
  {
  }


  public GraphDocumentFileImpl(File entryFile, File modelFile, File styleFile)
  {
    this.entryFile = entryFile;
    this.modelFile = modelFile;
    this.styleFile = styleFile;
  }


  @Nullable
  @Override
  public File getEntryFile()
  {
    return entryFile;
  }


  public void setEntryFile(File entryFile)
  {
    this.entryFile = entryFile;
  }


  @Nullable
  @Override
  public File getModelFile()
  {
    return modelFile;
  }


  public void setModelFile(File modelFile)
  {
    this.modelFile = modelFile;
  }


  @Nullable
  @Override
  public File getStyleFile()
  {
    return styleFile;
  }


  public void setStyleFile(File styleFile)
  {
    this.styleFile = styleFile;
  }
}
