package com.lgsim.engine.graphEditor.graph.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfirmExitDialog extends JDialog
{
  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;


  public ConfirmExitDialog()
  {

    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);

    buttonOK.addActionListener(e -> onOK());

    buttonCancel.addActionListener(e -> onCancel());

    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        onCancel();
      }
    });

    contentPane.registerKeyboardAction(e -> onCancel(),
                                       KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                                       JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
  }


  private void onOK()
  {
    dispose();
    System.exit(0);
  }


  private void onCancel()
  {
    dispose();
  }
}
