package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.Document;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("WeakerAccess")
public abstract class VertexCellAction extends DocumentAction {

  protected mxGraphComponent graphComponent;


  public VertexCellAction(@NotNull Document document) {
    super(document);
    graphComponent = document.getSwingComponent();
  }
}
