package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.graph.IntCounter;
import com.mxgraph.model.mxCell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class GraphSupport {
  static void modifyCell(@NotNull mxCell cell, @NotNull IntCounter counter) {
    VertexImpl vertex = extractVertexImpl(cell);
    if (vertex != null) {
      String id = counter.get() + "";
      counter.inc();
      cell.setId(id);
      vertex.setID(id);
      vertex.setDisplayName(id);
    }
  }

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
}
