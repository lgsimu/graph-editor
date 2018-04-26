package com.lgsim.engine.graphEditor.api.graph.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocument;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocumentFile;
import com.lgsim.engine.graphEditor.api.graph.IGraphStyle;
import org.jetbrains.annotations.NotNull;

public class GraphDocumentImpl implements IGraphDocument {
  private String title;
  private IGraphDocumentFile graphDocumentFile;
  private IGraph graph;
  private IGraphStyle graphStyle;
  private boolean modified;


  public GraphDocumentImpl() {
  }


  @NotNull
  @Override
  public String getTitle()
  {
    if (title == null) {
      return "";
    } else {
      return title;
    }
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  @NotNull
  @Override
  public IGraphDocumentFile getGraphDocumentFile()
  {
    return graphDocumentFile;
  }

  public void setGraphDocumentFile(IGraphDocumentFile graphDocumentFile)
  {
    this.graphDocumentFile = graphDocumentFile;
  }

  @NotNull
  @Override
  public IGraph getGraph()
  {
    return graph;
  }

  public void setGraph(@NotNull IGraph graph)
  {
    this.graph = graph;
  }

  @NotNull
  @Override
  public IGraphStyle getGraphStyle()
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
