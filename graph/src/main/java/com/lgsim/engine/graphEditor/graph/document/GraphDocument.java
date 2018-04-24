package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentFileImpl;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentImpl;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphStyleImpl;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;

public class GraphDocument extends GraphDocumentImpl {
  private mxGraphComponent graphComponent;

  public GraphDocument(@NotNull mxGraphComponent comp)
  {
    super(null, new GraphDocumentFileImpl(), (IGraph) comp.getGraph(), new GraphStyleImpl(), false);
    setGraphComponent(comp);
  }

  @Override
  public @NotNull String getTitle()
  {
    return MessageBundle.get("graphDocument.newDocumentTitle") + (isModified() ? "*" : "");
  }

  public @NotNull mxGraphComponent getGraphComponent()
  {
    return graphComponent;
  }

  private void setGraphComponent(@NotNull mxGraphComponent graphComponent)
  {
    this.graphComponent = graphComponent;
  }
}
