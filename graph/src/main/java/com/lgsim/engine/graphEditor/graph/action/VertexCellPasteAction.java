package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.Document;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public class VertexCellPasteAction extends VertexCellAction {
  private static final Logger log = LoggerFactory.getLogger(VertexCellPasteAction.class);

  public VertexCellPasteAction(@NotNull Document document) {
    super(document);
  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    log.debug("vertex cell paste action performed");
    Action pasteAction = TransferHandler.getPasteAction();
    pasteAction.actionPerformed(ActionSupport.createActionEvent(graphComponent, evt));
  }
}
