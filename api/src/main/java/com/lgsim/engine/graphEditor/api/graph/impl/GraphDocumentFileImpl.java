package com.lgsim.engine.graphEditor.api.graph.impl;

import com.lgsim.engine.graphEditor.api.graph.IGraphDocumentFile;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"WeakerAccess", "unused"})
public class GraphDocumentFileImpl implements IGraphDocumentFile {

  private String entryFile;
  private String modelFile;
  private String styleFile;


  public GraphDocumentFileImpl() {
  }


  @NotNull
  @Override
  public String getEntryFile() {
    return entryFile;
  }


  public void setEntryFile(@NotNull String entryFile) {
    this.entryFile = entryFile;
  }


  @NotNull
  @Override
  public String getModelFile() {
    return modelFile;
  }


  public void setModelFile(@NotNull String modelFile) {
    this.modelFile = modelFile;
  }


  @NotNull
  @Override
  public String getStyleFile() {
    return styleFile;
  }


  public void setStyleFile(@NotNull String styleFile) {
    this.styleFile = styleFile;
  }
}
