package com.lgsim.engine.graphEditor.graph.editor;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.view.mxGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Map;

class EditorGraph extends mxGraph
{
  private static final Logger log = LoggerFactory.getLogger(EditorGraph.class);
  private mxCell fromNode;
  private mxCell toNode;
  private mxCell autogenEdge;


  EditorGraph()
  {
    setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
    setCellsResizable(false);
    setCellsEditable(true);
    setKeepEdgesInForeground(true);
    addListener(mxEvent.CELL_CONNECTED, (sender, evt) -> {
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
    });
  }


  private void paintCavityNodeBetween()
  {
    log.debug("paint cavity node");
    Point position = getCavityPosition(fromNode.getGeometry().getPoint(), toNode.getGeometry().getPoint());
    Object parent = getDefaultParent();
    getModel().beginUpdate();
    try
    {
      mxCell cavity = (mxCell) insertVertex(parent, null, "cavity", position.x, position.y, 64, 64);
      autogenEdge.setTerminal(cavity, false);
      insertEdge(parent, null, "", cavity, toNode);
    }
    finally
    {
      getModel().endUpdate();
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
  public Object createEdge(Object parent, String id, Object value, Object source, Object target, String style)
  {
    log.debug("create edge {}", style);
    mxCell edge = new mxCell(value, new mxGeometry(), style);

    edge.setId(id);
    edge.setEdge(true);
    edge.getGeometry().setRelative(true);

    return edge;
  }


  @Override
  public boolean isCellSelectable(Object cell)
  {
    return !model.isEdge(cell);
  }
}
