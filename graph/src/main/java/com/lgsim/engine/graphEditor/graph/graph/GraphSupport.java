package com.lgsim.engine.graphEditor.graph.graph;

import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.graph.IntCounter;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventSource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings("WeakerAccess")
public class GraphSupport {
  private static final Logger log = LoggerFactory.getLogger(GraphSupport.class);

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
  public static @Nullable VertexImpl extractVertexImpl(@Nullable Object o) {
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

  public static mxEventSource.mxIEventListener cellConnectedListener(@NotNull Graph graph, @NotNull Runnable runnable) {
    return cellConnectedListener(graph::setSourceNode, graph::setTargetNode, graph::setHandDrawnEdge, runnable);
  }

  @Contract(pure = true)
  private static mxEventSource.mxIEventListener cellConnectedListener(@NotNull Consumer<mxCell> sourceConsumer,
                                                                      @NotNull Consumer<mxCell> targetConsumer,
                                                                      @NotNull Consumer<mxCell> edgeConsumer,
                                                                      @NotNull Runnable runnable)
  {
    return (sender, evt) -> {
      Map<String, Object> properties = evt.getProperties();
      mxCell terminal = (mxCell) properties.get("terminal");
      mxCell edge = (mxCell) properties.get("edge");
      boolean isSource = (boolean) properties.get("source");
      if (isSource) {
        sourceConsumer.accept(terminal);
        edgeConsumer.accept(edge);
        log.debug("connecting cells, from {}", terminal);
      } else {
        targetConsumer.accept(terminal);
        runnable.run();
        log.debug("cells connected, target is {}", terminal);
      }
    };
  }

  private static mxEventSource.mxIEventListener cellsMovedListener() {
    return (sender, evt) -> {

    };
  }
}
