package com.lgsim.engine.graphEditor.graph.editor;

import com.mxgraph.model.mxCell;
import com.mxgraph.shape.mxITextShape;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Map;

public class GraphCanvas extends mxInteractiveCanvas
{
  private static final Logger log = LoggerFactory.getLogger(GraphCanvas.class);


  @Override
  public Object drawCell(mxCellState state)
  {
    log.debug("GraphCanvas draw cell");
    return super.drawCell(state);
  }


  @Override
  public Object drawLabel(String text, mxCellState state, boolean html)
  {
    log.debug("GraphCanvas draw label");
    if (state.getCell() instanceof mxCell)
    {
      mxCell cell = (mxCell) state.getCell();
      if (cell.isEdge())
      {
        log.debug("GraphCanvas draw edge label");
        Map<String, Object> style = state.getStyle();
        mxITextShape shape = getTextShape(style, html);
        if (g != null && shape != null && drawLabels)
        {
          float opacity = mxUtils.getFloat(style, mxConstants.STYLE_TEXT_OPACITY, 100);
          Graphics2D previousGraphics = g;
          g = createTemporaryGraphics(style, opacity, null);
          Color bg = mxUtils.getColor(style, mxConstants.STYLE_LABEL_BACKGROUNDCOLOR);
          Color border = mxUtils.getColor(style, mxConstants.STYLE_LABEL_BORDERCOLOR);
          paintCircle(state.getLabelBounds().getRectangle(), bg, border);
          shape.paintShape(this, "42", state, style);
          g.dispose();
          g = previousGraphics;
        }
        return shape;
      }
    }
    return super.drawLabel(text, state, html);
  }


  private void paintCircle(Rectangle bounds, Color background, Color border)
  {
    if (background != null)
    {
      g.setColor(background);
      fillShape(bounds);
    }

    if (border != null)
    {
      g.setColor(border);
      g.draw(bounds);
    }
  }
}
