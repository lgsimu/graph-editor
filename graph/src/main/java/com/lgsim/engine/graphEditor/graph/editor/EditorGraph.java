package com.lgsim.engine.graphEditor.graph.editor;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class EditorGraph extends mxGraph
{
  private static final Logger log = LoggerFactory.getLogger(EditorGraph.class);


  public EditorGraph()
  {
    setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
    setCellsResizable(false);
    setCellsEditable(true);
    setKeepEdgesInForeground(true);
    addListener(mxEvent.CELL_CONNECTED, (sender, evt) -> {
      log.debug("cell connected");
      Map<String, Object> properties = evt.getProperties();
      mxCell terminal = (mxCell) properties.get("terminal");
      mxCell edge = (mxCell) properties.get("edge");
      boolean source = (boolean) properties.get("source");
    });
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
