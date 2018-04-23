package com.lgsim.engine.graphEditor.graph.graph;

import com.lgsim.engine.graphEditor.graph.IntCounter;
import com.lgsim.engine.graphEditor.graph.graph.GraphSupport;
import com.mxgraph.model.mxCell;
import org.jetbrains.annotations.NotNull;

public class GraphHook {
  public static void cellAdded(@NotNull mxCell cell, @NotNull IntCounter counter) {
    GraphSupport.modifyCell(cell, counter);
  }
}
