package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.GraphDocument;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

@SuppressWarnings("WeakerAccess")
public abstract class DocumentAction extends AbstractAction {
  private static final Logger log = LoggerFactory.getLogger(DocumentAction.class);
  protected GraphDocument document;

  public DocumentAction(@NotNull GraphDocument document) {
    this.document = document;
  }
}
