package com.lgsim.engine.graphEditor.api.graph.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.graph.IDocument;
import com.lgsim.engine.graphEditor.api.graph.IGraphStyle;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"WeakerAccess", "unused"})
public class GraphDocumentImpl implements IDocument {

  private String title;
  private String entryFilePath;
  private IGraph graph;
  private IGraphStyle graphStyle;
  private boolean modified;


  public GraphDocumentImpl() {
  }


  @Override
  public @NotNull String getTitle()
  {
    if (title == null) {
      return "";
    }
    else {
      return title;
    }
  }


  public void setTitle(@NotNull String title)
  {
    this.title = title;
  }


  @NotNull
  @Override
  public String getEntryFilePath() {
    return entryFilePath;
  }


  public void setEntryFilePath(String entryFilePath) {
    this.entryFilePath = entryFilePath;
  }


  @Override
  public @NotNull IGraph getGraph()
  {
    return graph;
  }


  public void setGraph(@NotNull IGraph graph)
  {
    this.graph = graph;
  }


  @Override
  public @NotNull IGraphStyle getGraphStyle()
  {
    return graphStyle;
  }


  public void setGraphStyle(IGraphStyle graphStyle)
  {
    this.graphStyle = graphStyle;
  }


  @Override
  public boolean isModified()
  {
    return modified;
  }


  @Override
  public void setModified(boolean modified)
  {
    this.modified = modified;
  }


  @Override
  public String toString() {
    return getTitle();
  }
}
