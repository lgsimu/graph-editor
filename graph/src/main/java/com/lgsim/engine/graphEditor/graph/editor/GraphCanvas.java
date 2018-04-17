package com.lgsim.engine.graphEditor.graph.editor;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.shape.mxITextShape;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class GraphCanvas extends mxInteractiveCanvas
{
  private static final Logger log = LoggerFactory.getLogger(GraphCanvas.class);
  private static final int CIRCLE_INSET = 10;


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
        final Map<String, Object> style = state.getStyle();
        mxITextShape shape = getTextShape(style, html);
        if (g != null && shape != null && drawLabels)
        {
          final double scale = getScale();
          text = "42424242";
          Font font = mxUtils.getFont(style, scale);
          g.setFont(font);
          Rectangle2D bounds = font.getStringBounds(text, g.getFontRenderContext());
          log.debug("bounds {}", bounds);
          float opacity = mxUtils.getFloat(style, mxConstants.STYLE_TEXT_OPACITY, 100);
          Graphics2D previousGraphics = g;
          g = createTemporaryGraphics(style, opacity, null);
          Color bg = mxUtils.getColor(style, mxConstants.STYLE_LABEL_BACKGROUNDCOLOR);
          Color border = mxUtils.getColor(style, mxConstants.STYLE_LABEL_BORDERCOLOR);
          Rectangle rect = paintShape(this, text, state, style);
          assert rect != null;
          rect.width = (int) (bounds.getWidth() * scale);
          rect.height=(int) (bounds.getHeight() * scale);
          paintCircle(rect, bg, border);
          g.dispose();
          g = previousGraphics;
          return shape;
        }
      }
    }
    return super.drawLabel(text, state, html);
  }


  private void paintCircle(Rectangle bounds, Color background, Color border)
  {
    int radius = Math.max(bounds.width, bounds.height) + CIRCLE_INSET;
    int x = bounds.x;
    int y = bounds.y;
//    if (background != null)
//    {
//      g.setColor(background);
//      int x = bounds.x - (bounds.width / 2);
//      int y = bounds.y - (bounds.height / 2);
//      g.fillOval(x, y, bounds.width, bounds.height);
//    }

    if (border != null)
    {
      g.setColor(border);
//      g.drawRect(x, y, radius, radius);
//      g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
  }


  private Rectangle paintShape(mxGraphics2DCanvas canvas, String text,
                          mxCellState state, Map<String, Object> style)
  {
    final int labelInset = 0;
    Rectangle rect = state.getLabelBounds().getRectangle();
    Graphics2D g = canvas.getGraphics();

    if (g.getClipBounds() == null || g.getClipBounds().intersects(rect))
    {
      boolean horizontal = mxUtils.isTrue(style,
                                          mxConstants.STYLE_HORIZONTAL, true);
      double scale = canvas.getScale();
      int x = rect.x;
      int y = rect.y;
      int w = rect.width;
      int h = rect.height;

      if (!horizontal)
      {
        g.rotate(-Math.PI / 2, x + w / 2, y + h / 2);
        g.translate(w / 2 - h / 2, h / 2 - w / 2);
      }

      Color fontColor = mxUtils.getColor(style,
                                         mxConstants.STYLE_FONTCOLOR, Color.black);
      g.setColor(fontColor);

      // Shifts the y-coordinate down by the ascent plus a workaround
      // for the line not starting at the exact vertical location
      Font scaledFont = mxUtils.getFont(style, scale);
      g.setFont(scaledFont);
      int fontSize = mxUtils.getInt(style, mxConstants.STYLE_FONTSIZE,
                                    mxConstants.DEFAULT_FONTSIZE);
      FontMetrics fm = g.getFontMetrics();
      int scaledFontSize = scaledFont.getSize();
      double fontScaleFactor = ((double) scaledFontSize)
                               / ((double) fontSize);
      // This factor is the amount by which the font is smaller/
      // larger than we expect for the given scale. 1 means it's
      // correct, 0.8 means the font is 0.8 the size we expected
      // when scaled, etc.
      double fontScaleRatio = fontScaleFactor / scale;
      // The y position has to be moved by (1 - ratio) * height / 2
      y += 2 * fm.getMaxAscent() - fm.getHeight()
           + labelInset * scale;

      Object vertAlign = mxUtils.getString(style,
                                           mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
      double vertAlignProportion = 0.5;

      if (vertAlign.equals(mxConstants.ALIGN_TOP))
      {
        vertAlignProportion = 0;
      }
      else if (vertAlign.equals(mxConstants.ALIGN_BOTTOM))
      {
        vertAlignProportion = 1.0;
      }

      y += (1.0 - fontScaleRatio) * h * vertAlignProportion;

      // Gets the alignment settings
      Object align = mxUtils.getString(style, mxConstants.STYLE_ALIGN,
                                       mxConstants.ALIGN_CENTER);

      if (align.equals(mxConstants.ALIGN_LEFT))
      {
        x += mxConstants.LABEL_INSET * scale;
      }
      else if (align.equals(mxConstants.ALIGN_RIGHT))
      {
        x -= mxConstants.LABEL_INSET * scale;
      }

      int dx = 0;

      if (align.equals(mxConstants.ALIGN_CENTER))
      {
        int sw = fm.stringWidth(text);

        if (horizontal)
        {
          dx = (w - sw) / 2;
        }
        else
        {
          dx = (h - sw) / 2;
        }
      }
      else if (align.equals(mxConstants.ALIGN_RIGHT))
      {
        int sw = fm.stringWidth(text);
        dx = ((horizontal) ? w : h) - sw;
      }

      g.drawString(text, x + dx, y);
      g.setColor(Color.black);
      g.drawOval(x+dx, y, 1, 1);
      return new Rectangle(x + dx, y, 0, 0);
    }
    return null;
  }
}
