package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.graph.util.IOUtil;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import org.w3c.dom.Document;

import java.awt.*;

public class EditorGraphComponent extends mxGraphComponent
{
  public EditorGraphComponent(mxGraph graph)
  {
    super(graph);
    setPageVisible(true);
    setGridVisible(true);
    setToolTips(true);
    getConnectionHandler().setCreateTarget(true);
    mxCodec codec = new mxCodec();
    String style = IOUtil.getResourceURI("com/lgsim/engine/graphEditor/graph/default-style.xml");
    Document doc = mxUtils.loadDocument(style);
    codec.decode(doc.getDocumentElement(), graph.getStylesheet());
    getViewport().setOpaque(true);
    getViewport().setBackground(Color.WHITE);
  }


  @Override
  public Object[] importCells(Object[] cells, double dx, double dy, Object target, Point location)
  {
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
