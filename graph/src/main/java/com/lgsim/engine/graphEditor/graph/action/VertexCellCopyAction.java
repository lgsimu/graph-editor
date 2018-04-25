package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.Document;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public class VertexCellCopyAction extends VertexCellAction {
  private static final Logger log = LoggerFactory.getLogger(VertexCellCopyAction.class);

  public VertexCellCopyAction(@NotNull Document document) {
    super(document);
  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    log.debug("vertex cell copy action performed");
    Action copyAction = TransferHandler.getCopyAction();
    copyAction.actionPerformed(ActionSupport.createActionEvent(graphComponent, evt));
  }
}
