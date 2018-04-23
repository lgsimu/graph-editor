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
  private final Counter vertexCounter = new Counter();
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
    log.debug("paint cavity node");
    getModel().beginUpdate();
    try {
      final Point position = getCavityPosition(fromNode.getGeometry().getPoint(), toNode.getGeometry().getPoint());
      final Object p = getDefaultParent();
      mxCell cavity = createCavityCell(position, p);
      autogenEdge.setTerminal(cavity, false);
      removeListener(cellConnectedListener);
      insertEdge(getDefaultParent(), null, null, cavity, toNode);
    } finally {
      getModel().endUpdate();
    }
  }

  private @NotNull mxCell createCavityCell(@NotNull Point position, @NotNull Object p) {
    final int square = 32;
    final IVertexStencil stencil = stencilContext.getCavityStencil();
    IVertex value = Builder.createVertex(stencil, vertexCounter, true);

    final String id = null; // auto-generate id when created vertex
    mxCell cell = (mxCell) insertVertex(p, id, value, position.x, position.y, square, square);
    settingCavityCellStyle(cell, stencil);
    return cell;
  }

  private void settingCavityCellStyle(@NotNull mxCell cavity, @NotNull IVertexStencil stencil) {
//    TODO
//    1.3 将腔节点cell的外观改成圆形(32x32)，高亮和其它的元件一致，背景设置为1中获取的图标，并将该图标缩放为(32x32)
//    1.3.1 找到其它元件是在哪里绘制的，样式是怎么获取的，怎么应用上去的
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
  public Object createVertex(Object parent, String id, Object value,
                             double x, double y, double width, double height,
                             String style, boolean relative)
  {
    id = "" + vertexCounter.incInt();
    return super.createVertex(parent, id, value, x, y, width, height, style, relative);
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

  public Counter getVertexCounter() {
    return vertexCounter;
  }
}
