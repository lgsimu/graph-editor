package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.GraphDocument;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("WeakerAccess")
public abstract class VertexCellAction extends DocumentAction {
  protected mxGraphComponent graphComponent;

  public VertexCellAction(@NotNull GraphDocument document) {
    super(document);
    graphComponent = document.getGraphComponent();
  }
}
