package com.lgsim.engine.graphEditor.graph.graph;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxGraph;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Collection;
import java.util.Vector;

@SuppressWarnings("WeakerAccess")
public class Graph extends mxGraph implements IGraph {

  private static final Logger log = LoggerFactory.getLogger(Graph.class);
  private static final IStencilContext stencilContext = ImplementationContext.INSTANCE.getStencilContext();
  private static final boolean disableContainsCavities = true;
  private final mxIEventListener cellConnectedListener = GraphSupport.cellConnectedListener(this, this::paintCavityBetween);
  private final IntCounter vertexCounter = new IntCounter(1);
  private mxCell sourceNode;
  private mxCell targetNode;
  private mxCell handDrawnEdge;
  private final Collection<IVertex> vertexes;


  public Graph()
  {
    vertexes = new Vector<>();
    GraphSupport.applyGraphSettings(this);
    addListener(mxEvent.CELL_CONNECTED, cellConnectedListener);
    addListener(mxEvent.CELLS_MOVED, GraphSupport.cellsMovedListener(this));
  }


  public void setSourceNode(mxCell sourceNode) {
    this.sourceNode = sourceNode;
  }


  public void setTargetNode(mxCell targetNode) {
    this.targetNode = targetNode;
  }


  public void setHandDrawnEdge(mxCell handDrawnEdge) {
    this.handDrawnEdge = handDrawnEdge;
  }


  public void paintCavityBetween()
  {
    if (disableContainsCavities) {
      boolean notContains = !GraphSupport.isCavity(sourceNode) && !GraphSupport.isCavity(targetNode);
      if (notContains) {
        paintCavityBetween0();
      }
    }
    else {
      paintCavityBetween0();
    }
  }


  private void paintCavityBetween0() {
    log.debug("paint cavity node");
    getModel().beginUpdate();
    try {
      final Point position = getCavityPosition(sourceNode.getGeometry().getPoint(), targetNode.getGeometry().getPoint());
      final Object p = getDefaultParent();
      mxCell cavityCell = createCavityCell(position, p);
      handDrawnEdge.setTerminal(cavityCell, false);
      insertEdge(getDefaultParent(), null, null, cavityCell, targetNode);
    }
    finally {
      removeListener(cellConnectedListener);
      getModel().endUpdate();
      addListener(mxEvent.CELL_CONNECTED, cellConnectedListener);
    }
  }


  private @NotNull mxCell createCavityCell(@NotNull Point position, @NotNull Object p) {
    final IVertexStencil stencil = stencilContext.getCavityStencil();
    VertexImpl value = GraphSupport.createVertex(stencil, true);
    mxCell cell = (mxCell) insertVertex(p, null, value, position.x, position.y, 48, 48);
    GraphSupport.applyCellSettings(cell, vertexCounter, this);
    settingCavityCellStyle(cell, stencil);
    return cell;
  }


  private void settingCavityCellStyle(@NotNull mxCell cavity, @NotNull IVertexStencil stencil) {
    String icon = stencil.getGraphIcon();
    cavity.setStyle("glass=1;rounded=1;shadow=1;imageWidth=32;imageHeight=32;arcSize=48;icon;image=/" + icon);
  }


  private static Point getCavityPosition(Point from, Point to)
  {
    int x = (from.x + to.x) / 2;
    int y = (from.y + to.y) / 2;
    return new Point(x, y);
  }


  @Override
  public String getToolTipForCell(Object cell)
  {
    return "";
  }


  @Override
  public boolean isCellSelectable(Object cell)
  {
    return !model.isEdge(cell);
  }


  @Override
  public void cellsAdded(Object[] cells, Object parent, Integer index, Object source, Object target, boolean absolute) {
    if (cells != null) {
      for (Object x : cells) {
        if (x instanceof mxCell) {
          mxCell cell = (mxCell) x;
          GraphHook.cellAdded(cell, vertexCounter, this);
        }
      }
    }
    super.cellsAdded(cells, parent, index, source, target, absolute);
  }


  @Override
  public Object createEdge(Object parent, String id, Object value, Object source, Object target, String style)
  {
    final mxCell edge = (mxCell) super.createEdge(parent, id, value, source, target, style);
    /* remove vertexes then remove connected edges respectively */
    edge.getGeometry().setRelative(false);
    return edge;
  }


  @Override
  public @NotNull Collection<IVertex> getVertexes()
  {
    return vertexes;
  }


  @Override
  public void retrieveCalcOutputs(@NotNull IGraph graph) {
    // TODO: update outputs for graph
  }


  public void addVertex(@NotNull IVertex vertex) {
    vertexes.add(vertex);
  }
}
