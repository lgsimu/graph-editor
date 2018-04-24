package com.lgsim.engine.graphEditor.graph.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VertexCellPasteAction extends VertexCellAction {
  @Override
  public void actionPerformed(ActionEvent e) {
    Action pasteAction = TransferHandler.getPasteAction();
    pasteAction.actionPerformed(e);
  }
}
