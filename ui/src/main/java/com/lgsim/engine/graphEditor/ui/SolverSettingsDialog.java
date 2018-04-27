package com.lgsim.engine.graphEditor.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SolverSettingsDialog extends JDialog {

  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;
  private JTextField executablePathComp;
  private JTextField argumentsComp;
  private JButton button1;


  public SolverSettingsDialog() {
    setMinimumSize(new Dimension(400, 180));
    setResizable(false);
    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);

    buttonOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onOK();
      }
    });

    buttonCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onCancel();
      }
    });

    // call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

    // call onCancel() on ESCAPE
    contentPane.registerKeyboardAction(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onCancel();
      }
    }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
  }


  private void onOK() {
    // add your code here
    dispose();
  }


  private void onCancel() {
    // add your code here if necessary
    dispose();
  }


  public static void main(String[] args) {
    SolverSettingsDialog dialog = new SolverSettingsDialog();
    dialog.pack();
    dialog.setVisible(true);
    System.exit(0);
  }
}
