package com.lgsim.engine.graphEditor.graph.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;

public class VertexCellCopyAction extends VertexCellAction {
  private static final Logger log = LoggerFactory.getLogger(VertexCellCopyAction.class);

  @Override
  public void actionPerformed(ActionEvent e) {
    log.debug("vertex cell copy action performed");
  }
}
