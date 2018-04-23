package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.graph.IntCounter;
import com.mxgraph.model.mxCell;
import org.jetbrains.annotations.NotNull;

class GraphHook {
  static void cellAdded(@NotNull mxCell cell, @NotNull IntCounter counter) {
    GraphSupport.modifyCell(cell, counter);
  }
}
