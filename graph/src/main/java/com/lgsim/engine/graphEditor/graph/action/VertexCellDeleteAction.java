package com.lgsim.engine.graphEditor.graph.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VertexCellDeleteAction extends VertexCellAction {
  @Override
  public void actionPerformed(ActionEvent e) {
    Action cutAction = TransferHandler.getCutAction();
    cutAction.actionPerformed(e);
  }
}
