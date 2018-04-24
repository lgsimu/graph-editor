package com.lgsim.engine.graphEditor.graph.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public abstract class VertexCellAction extends AbstractAction {
  private static final Logger log = LoggerFactory.getLogger(VertexCellAction.class);

  public VertexCellAction() {
    log.debug("cell action performed");
  }
}
