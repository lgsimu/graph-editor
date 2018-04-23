package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.data.*;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.lgsim.engine.graphEditor.util.CollectionUtil;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxGraph;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class EditorGraph extends mxGraph implements IGraph {
  private static final Logger log = LoggerFactory.getLogger(EditorGraph.class);
  private static final IStencilContext stencilContext = ImplementationContext.INSTANCE.getStencilContext();
  private final mxIEventListener cellConnectedListener;
  private final IntCounter vertexCounter = new IntCounter(1);
  private mxCell fromNode;
  private mxCell toNode;
  private mxCell autogenEdge;

  EditorGraph()
  {
    setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
    setCellsResizable(false);
    setCellsEditable(true);
    setKeepEdgesInForeground(true);
    cellConnectedListener = (sender, evt) -> {
      log.debug("cell connected, event {}", evt.getName());
      Map<String, Object> properties = evt.getProperties();
      mxCell terminal = (mxCell) properties.get("terminal");
      mxCell edge = (mxCell) properties.get("edge");
      boolean source = (boolean) properties.get("source");
      if (source) {
        fromNode = terminal;
        autogenEdge = edge;
      } else {
        toNode = terminal;
        paintCavityNodeBetween();
      }
    };
    addListener(mxEvent.CELL_CONNECTED, cellConnectedListener);
    addListener(mxEvent.CELLS_MOVED, ((sender, evt) -> {
      log.debug("cells moved");
      Object[] xs = (Object[]) evt.getProperties().get("cells");
      if (xs != null) {
        for (Object x : xs) {
          if (x instanceof mxCell) {
            mxCell cell = (mxCell) x;
            if (cell.isVertex()) {
              Object value = cell.getValue();
              if (value instanceof IVertex) {
                IVertex vertex = (IVertex) value;
                if (vertex.isCavity()) {
                  log.debug("move cavity {}", cell);
                  repaintCavityEdges();
                }
              }
            }
          }
        }
      }
    }));
  }

  private void repaintCavityEdges() {
    log.debug("repaint cavity edges");
    refresh();
  }

  private void paintCavityNodeBetween()
  {
    Object toNodeValue = toNode.getValue();
    if (toNodeValue instanceof IVertex) {
      boolean notCavity = !((IVertex) toNodeValue).isCavity();
      if (notCavity) {
        log.debug("paint cavity node");
        getModel().beginUpdate();
        try {
          final Point position = getCavityPosition(fromNode.getGeometry().getPoint(), toNode.getGeometry().getPoint());
          final Object p = getDefaultParent();
          mxCell cavityCell = createCavityCell(position, p);
          autogenEdge.setTerminal(cavityCell, false);
          removeListener(cellConnectedListener);
          insertEdge(getDefaultParent(), null, null, cavityCell, toNode);
          addListener(mxEvent.CELL_CONNECTED, cellConnectedListener);
        } finally {
          getModel().endUpdate();
        }
      }
    }
  }

  private @NotNull mxCell createCavityCell(@NotNull Point position, @NotNull Object p) {
    final IVertexStencil stencil = stencilContext.getCavityStencil();
    VertexImpl value = Builder.createVertex(stencil, true);
    mxCell cell = (mxCell) insertVertex(p, null, value, position.x, position.y, 48, 48);
    GraphSupport.modifyCell(cell, vertexCounter);
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
          GraphHook.cellAdded(cell, vertexCounter);
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
  public Collection<IVertex> getAllVertexes()
  {
    final Object defaultParent = getDefaultParent();
    final Object[] vertices = getChildVertices(defaultParent);
    final Object[] edges = getChildEdges(defaultParent);
    final List<IVertex> output = new Vector<>();
    for (Object vertex : vertices) {
      if (vertex instanceof IVertex) {
        mxCell in = (mxCell) vertex;
        IVertex v = (IVertex) in.getValue();
        VertexImpl out = new VertexImpl();
        cloneIfPossible(v, out);
        List<IVertex> inputVertexes = lookupInputPorts(vertex, edges);
        List<IVertex> outputVertexes = lookupOutputPorts(vertex, edges);
        out.setInputPorts(inputVertexes);
        out.setOutputPorts(outputVertexes);
        output.add(out);
      }
    }
    return output;
  }

  // TODO: deep clone?
  private void cloneIfPossible(@NotNull IVertex source, @NotNull VertexImpl target)
  {
    target.setID(source.getID());
    target.setTypeID(source.getTypeID());
    List<IVertexArgument> arguments = CollectionUtil.cloneList(source.getArguments());
    target.setArguments(arguments);
    List<IVertexOutput> outputs = CollectionUtil.cloneList(source.getOutputs());
    target.setOutputs(outputs);
    target.setCavity(source.isCavity());
  }

  private List<IVertex> lookupInputPorts(@NotNull Object vertex, @NotNull Object[] edges)
  {
    List<IVertex> output = new Vector<>();
    for (Object o : edges) {
      mxCell v = (mxCell) vertex;
      mxCell edge = (mxCell) o;
      if (notOrphanEdge(edge)) {
        mxCell target = (mxCell) edge.getTarget();
        if (cellEquals(v, target)) {
          mxCell source = (mxCell) edge.getSource();
          IVertex out = (IVertex) source.getValue();
          output.add(out);
        }
      }
    }
    return output;
  }

  private List<IVertex> lookupOutputPorts(@NotNull Object vertex, @NotNull Object[] edges)
  {
    List<IVertex> output = new Vector<>();
    for (Object o : edges) {
      mxCell v = (mxCell) vertex;
      mxCell edge = (mxCell) o;
      if (notOrphanEdge(edge)) {
        mxCell source = (mxCell) edge.getSource();
        if (cellEquals(v, source)) {
          mxCell target = (mxCell) edge.getTarget();
          IVertex out = (IVertex) target.getValue();
          output.add(out);
        }
      }
    }
    return output;
  }

  @Contract(pure = true)
  private boolean cellEquals(@NotNull mxCell x, @NotNull mxCell y)
  {
    return x.equals(y);
  }

  private boolean notOrphanEdge(@NotNull mxCell cell)
  {
    return (cell.getSource() != null) && (cell.getTarget() != null);
  }

  public IntCounter getVertexCounter() {
    return vertexCounter;
  }
}
