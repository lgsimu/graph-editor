package com.lgsim.engine.graphEditor.graph.graph;

import com.mxgraph.model.mxCell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("WeakerAccess")
public class GraphHook {
  private static final Logger log = LoggerFactory.getLogger(GraphHook.class);


  public static void cellAdded(@NotNull mxCell cell, @NotNull IntCounter counter, @NotNull Graph graph) {
    GraphSupport.applyCellSettings(cell, counter, graph);
  }

  public static void cavityCellMoved(@Nullable mxCell cell, @NotNull Graph graph) {
    log.debug("refresh after cavity cell {} moved", cell);
    graph.refresh();
  }
}
