package com.lgsim.engine.graphEditor.graph.editor;

import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.view.mxCellState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphCanvas extends mxInteractiveCanvas
{
  private static final Logger log = LoggerFactory.getLogger(GraphCanvas.class);


  @Override
  public Object drawCell(mxCellState state)
  {
    log.debug("GraphCanvas draw cell");
    return super.drawCell(state);
  }
}
