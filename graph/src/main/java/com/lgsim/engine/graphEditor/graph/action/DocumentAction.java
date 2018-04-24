package com.lgsim.engine.graphEditor.graph.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public abstract class DocumentAction extends AbstractAction {
  private static final Logger log = LoggerFactory.getLogger(DocumentAction.class);

  public DocumentAction() {
    log.debug("document action performed");
  }
}
