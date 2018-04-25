package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.GraphDocument;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public abstract class DocumentAction extends AbstractAction {
  protected GraphDocument document;

  public DocumentAction(@NotNull GraphDocument document) {
    this.document = document;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
  }

  public GraphDocument getDocument() {
    return document;
  }
}
