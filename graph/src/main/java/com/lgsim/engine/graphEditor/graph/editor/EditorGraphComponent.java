package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.util.ResourceUtil;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import java.awt.*;
import java.net.URI;

public class EditorGraphComponent extends mxGraphComponent
{
  private static final Logger log = LoggerFactory.getLogger(EditorGraphComponent.class);


  EditorGraphComponent(mxGraph graph)
  {
    super(graph);
    setPageVisible(true);
    setGridVisible(true);
    setToolTips(true);
    getConnectionHandler().setCreateTarget(true);
    mxCodec codec = new mxCodec();
    URI defaultStyle = ResourceUtil.lookup("com/lgsim/engine/graphEditor/graph/default-style.xml");
    if (defaultStyle != null)
    {
      Document doc = mxUtils.loadDocument(defaultStyle.toString());
      codec.decode(doc.getDocumentElement(), graph.getStylesheet());
    }
    else
    {
      log.debug("load default style has failed");
    }
    getViewport().setOpaque(true);
    getViewport().setBackground(Color.WHITE);
  }


  @Override
  public Object[] importCells(Object[] cells, double dx, double dy, Object target, Point location)
  {
    /* 覆盖时替换元件 */
    if (target == null && cells.length == 1 && location != null)
    {
      target = getCellAt(location.x, location.y);
      if (target instanceof mxICell && cells[0] instanceof mxICell)
      {
        mxICell targetCell = (mxICell) target;
        mxICell dropCell = (mxICell) cells[0];
        if (targetCell.isVertex() == dropCell.isVertex()
            || targetCell.isEdge() == dropCell.isEdge())
        {
          mxIGraphModel model = graph.getModel();
          model.setStyle(target, model.getStyle(cells[0]));
          graph.setSelectionCell(target);
          return null;
        }
      }
    }
    return super.importCells(cells, dx, dy, target, location);
  }
}
