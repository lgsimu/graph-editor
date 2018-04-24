package com.lgsim.engine.graphEditor.graph.graph;

import com.lgsim.engine.graphEditor.graph.IntCounter;
import com.mxgraph.model.mxCell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("WeakerAccess")
public class GraphHook {
  private static final Logger log = LoggerFactory.getLogger(GraphHook.class);

  public static void cellAdded(@NotNull mxCell cell, @NotNull IntCounter counter) {
    GraphSupport.applyCellSettings(cell, counter);
  }

  public static void cavityCellMoved(@Nullable mxCell cell, @NotNull Graph graph) {
    log.debug("refresh after cavity cell {} moved", cell);
    graph.refresh();
  }
}
