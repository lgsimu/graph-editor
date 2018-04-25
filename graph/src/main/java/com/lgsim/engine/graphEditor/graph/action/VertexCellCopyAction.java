package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.GraphDocument;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public class VertexCellCopyAction extends VertexCellAction {
  private static final Logger log = LoggerFactory.getLogger(VertexCellCopyAction.class);

  public VertexCellCopyAction(@NotNull GraphDocument document) {
    super(document);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    log.debug("vertex cell copy action performed");
    Action copyAction = TransferHandler.getCopyAction();
    copyAction.actionPerformed(new ActionEvent(graphComponent, e.getID(), e.getActionCommand()));
  }
}
