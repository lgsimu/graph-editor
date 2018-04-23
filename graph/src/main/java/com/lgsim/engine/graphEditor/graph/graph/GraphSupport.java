package com.lgsim.engine.graphEditor.graph.graph;

import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.graph.IntCounter;
import com.mxgraph.model.mxCell;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GraphSupport {
  public static void applyCellSettings(@NotNull mxCell cell, @NotNull IntCounter counter) {
    VertexImpl vertex = extractVertexImpl(cell);
    if (vertex != null) {
      String id = counter.get() + "";
      counter.inc();
      cell.setId(id);
      vertex.setID(id);
      vertex.setDisplayName(id);
    }
  }

  @Contract("null -> null")
  private static @Nullable VertexImpl extractVertexImpl(@Nullable Object o) {
    if (o != null) {
      if (o instanceof mxCell) {
        Object v = ((mxCell) o).getValue();
        if (v instanceof VertexImpl) {
          return (VertexImpl) v;
        }
      }
    }
    return null;
  }

  public static void applyGraphSettings(@NotNull Graph graph) {
    graph.setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
    graph.setSplitEnabled(false);
    graph.setAllowDanglingEdges(false);
    graph.setCellsResizable(false);
    graph.setCellsEditable(true);
    graph.setKeepEdgesInForeground(true);
  }
}
