package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.GraphDocument;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public class VertexCellCutAction extends VertexCellAction {
  public VertexCellCutAction(@NotNull GraphDocument document) {
    super(document);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Action cutAction = TransferHandler.getCutAction();
    cutAction.actionPerformed(e);
  }
}
