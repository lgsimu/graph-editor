package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.GraphDocument;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public class VertexCellDeleteAction extends VertexCellAction {
  private static final Logger log = LoggerFactory.getLogger(VertexCellDeleteAction.class);

  public VertexCellDeleteAction(@NotNull GraphDocument document) {
    super(document);
  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    log.debug("vertex cell copy action performed");
    VertexCellCutAction cutAction = new VertexCellCutAction(document);
    cutAction.actionPerformed(evt);
  }
}
