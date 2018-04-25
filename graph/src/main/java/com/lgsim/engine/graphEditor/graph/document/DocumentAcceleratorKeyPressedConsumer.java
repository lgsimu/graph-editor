package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.graph.action.ActionSupport;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@SuppressWarnings("WeakerAccess")
public class DocumentAcceleratorKeyPressedConsumer extends DocumentAcceleratorConsumer {

  public DocumentAcceleratorKeyPressedConsumer(@NotNull Document document) {
    super(document);
  }

  @Override
  public void consume(@NotNull KeyEvent event) {
    final int code = event.getKeyCode();
    if (event.isControlDown()) {
      switch (code) {
        case KeyEvent.VK_C: {
          copyVertexes(event);
          break;
        }
        case KeyEvent.VK_V: {
          pasteVertexes(event);
          break;
        }
        case KeyEvent.VK_X: {
          cutVertexes(event);
          break;
        }
      }
    } else {
      switch (code) {
        case KeyEvent.VK_DELETE: {
          deleteVertexes(event);
        }
      }
    }
  }

  private void copyVertexes(@NotNull KeyEvent event) {
    Action action = document.getApplicationAction().getVertexCellCopyAction();
    ActionEvent actionEvent = ActionSupport.createActionEvent(document.getGraphComponent(), event);
    action.actionPerformed(actionEvent);
  }

  private void pasteVertexes(@NotNull KeyEvent event) {
    Action action = document.getApplicationAction().getVertexCellPasteAction();
    ActionEvent actionEvent = ActionSupport.createActionEvent(document.getGraphComponent(), event);
    action.actionPerformed(actionEvent);
  }

  private void cutVertexes(KeyEvent event) {
    Action action = document.getApplicationAction().getVertexCellCutAction();
    ActionEvent actionEvent = ActionSupport.createActionEvent(document.getGraphComponent(), event);
    action.actionPerformed(actionEvent);
  }

  private void deleteVertexes(@NotNull KeyEvent event) {
    Action action = document.getApplicationAction().getVertexCellDeleteAction();
    ActionEvent actionEvent = ActionSupport.createActionEvent(document.getGraphComponent(), event);
    action.actionPerformed(actionEvent);
  }
}
