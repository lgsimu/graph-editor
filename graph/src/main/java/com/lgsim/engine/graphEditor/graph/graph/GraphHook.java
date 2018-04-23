package com.lgsim.engine.graphEditor.graph.graph;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.graph.IntCounter;
import com.mxgraph.model.mxCell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphHook {
  private static final Logger log = LoggerFactory.getLogger(GraphHook.class);

  public static void cellAdded(@NotNull mxCell cell, @NotNull IntCounter counter) {
    GraphSupport.applyCellSettings(cell, counter);
  }

  public static void cavityCellMoved(@Nullable Object x, @NotNull Graph graph) {
    if (x instanceof mxCell) {
      mxCell cell = (mxCell) x;
      if (cell.isVertex()) {
        Object value = cell.getValue();
        if (value instanceof IVertex) {
          IVertex vertex = (IVertex) value;
          if (vertex.isCavity()) {
            log.debug("refresh after cavity cell moved");
            graph.refresh();
          }
        }
      }
    }
  }
}
