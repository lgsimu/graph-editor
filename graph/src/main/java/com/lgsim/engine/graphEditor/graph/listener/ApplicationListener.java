package com.lgsim.engine.graphEditor.graph.listener;

import com.lgsim.engine.graphEditor.graph.ui.ConfirmExitDialog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ApplicationListener extends WindowAdapter
{
  @Override
  public void windowClosing(WindowEvent e)
  {
    ConfirmExitDialog dialog = new ConfirmExitDialog();
    dialog.pack();
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
  }
}
