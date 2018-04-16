package com.lgsim.engine.graphEditor.graph.editor;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.shape.mxConnectorShape;
import com.mxgraph.view.mxCellState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberedConnectorShape extends mxConnectorShape
{
  private static final Logger log = LoggerFactory.getLogger(NumberedConnectorShape.class);


  @Override
  public void paintShape(mxGraphics2DCanvas canvas, mxCellState state)
  {
    log.debug("paint NumberedConnectorShape");
    super.paintShape(canvas, state);
  }
}
