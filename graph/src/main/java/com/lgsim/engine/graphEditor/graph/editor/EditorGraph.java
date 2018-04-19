package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
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

public class EditorGraph extends mxGraph implements IGraph
{
  private static final Logger log = LoggerFactory.getLogger(EditorGraph.class);
  private mxCell fromNode;
  private mxCell toNode;
  private mxCell autogenEdge;
  private final mxIEventListener listener;
  private final Counter cavityCounter = new Counter();
  private final Counter edgeCounter = new Counter();


  EditorGraph()
  {
    setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
    setCellsResizable(false);
    setCellsEditable(true);
    setKeepEdgesInForeground(true);
    listener = (sender, evt) -> {
      log.debug("cell connected, event {}", evt.getName());
      Map<String, Object> properties = evt.getProperties();
      mxCell terminal = (mxCell) properties.get("terminal");
      mxCell edge = (mxCell) properties.get("edge");
      boolean source = (boolean) properties.get("source");
      if (source)
      {
        fromNode = terminal;
        autogenEdge = edge;
      }
      else
      {
        toNode = terminal;
        paintCavityNodeBetween();
      }
    };
    addListener(mxEvent.CELL_CONNECTED, listener);
  }


  private void paintCavityNodeBetween()
  {
    log.debug("paint cavity node");
    Point position = getCavityPosition(fromNode.getGeometry().getPoint(), toNode.getGeometry().getPoint());
    Object parent = getDefaultParent();
    getModel().beginUpdate();
    try
    {
      String id = cavityCounter.incInt() + "";
      // TODO: cavity value
      mxCell cavity = (mxCell) insertVertex(parent, id, id, position.x, position.y, 64, 64);
      autogenEdge.setTerminal(cavity, false);
      removeListener(listener);
      String edgeId = edgeCounter.incInt() + "";
      insertEdge(parent, edgeId, null, cavity, toNode);
    }
    finally
    {
      getModel().endUpdate();
      addListener(mxEvent.CELL_CONNECTED, listener);
    }
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
  public Collection<IVertex> getAllVertexes()
  {
    final Object defaultParent = getDefaultParent();
    final Object[] vertices = getChildVertices(defaultParent);
    final Object[] edges = getChildEdges(defaultParent);
    final List<IVertex> output = new Vector<>();
    for (Object vertex : vertices)
    {
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
    for (Object o : edges)
    {
      mxCell v = (mxCell) vertex;
      mxCell edge = (mxCell) o;
      if (notOrphanEdge(edge))
      {
        mxCell target = (mxCell) edge.getTarget();
        if (cellEquals(v, target))
        {
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
    for (Object o : edges)
    {
      mxCell v = (mxCell) vertex;
      mxCell edge = (mxCell) o;
      if (notOrphanEdge(edge))
      {
        mxCell source = (mxCell) edge.getSource();
        if (cellEquals(v, source))
        {
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
}
