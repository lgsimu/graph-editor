package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.Document;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class DocumentSaveAction extends DocumentAction {
  private static final Logger log = LoggerFactory.getLogger(DocumentSaveAction.class);
  public DocumentSaveAction(@NotNull Document document) {
    super(document);
  }

  @Override
  public void actionPerformed(ActionEvent event)
  {
    log.debug("save document {}", document);
    try {
      document.save();
    } catch (IOException e) {
      log.debug("save document failed {}", e.getMessage());
    }
  }
}
