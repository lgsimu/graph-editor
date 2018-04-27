package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.Document;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public abstract class DocumentAction extends AbstractAction {
  protected Document document;

  public DocumentAction(@NotNull Document document) {
    this.document = document;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
  }


  public @NotNull Document getDocument() {
    return document;
  }
}
