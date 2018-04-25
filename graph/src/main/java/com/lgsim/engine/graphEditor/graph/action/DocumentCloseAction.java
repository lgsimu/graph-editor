package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.Document;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public class DocumentCloseAction extends DocumentAction {
  public DocumentCloseAction(@NotNull Document document) {
    super(document);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
