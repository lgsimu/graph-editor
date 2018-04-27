package com.lgsim.engine.graphEditor.ui;

import com.lgsim.engine.graphEditor.ui.bean.SolverEnvBean;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class SolverSettingsDialog extends JDialog {

  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;
  private JTextField textFieldExecutable;
  private JTextField textFieldArguments;
  private JButton buttonChooseExecutable;

  private String executable;
  private String arguments;
  private Consumer<SolverSettingsDialog> dialogConsumer;


  public SolverSettingsDialog(@NotNull Consumer<SolverSettingsDialog> dialogConsumer) {
    this.dialogConsumer = dialogConsumer;
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
    updateCalcEnv();
    dispose();
  }


  private void updateCalcEnv() {
    String executable = UISupport.getText(textFieldExecutable);
    String arguments = UISupport.getText(textFieldArguments);
    setExecutable(executable);
    setArguments(arguments);
    dialogConsumer.accept(this);
  }


  private void updateTextFields(@NotNull SolverEnvBean bean) {
    executable = bean.getExecutable();
    arguments = bean.getArguments();
    textFieldExecutable.setText(executable);
    textFieldArguments.setText(arguments);
  }


  public Consumer<SolverEnvBean> getBeanHook() {
    return this::updateTextFields;
  }


  private void onCancel() {
    // add your code here if necessary
    dispose();
  }


  public String getExecutable() {
    return executable;
  }


  public void setExecutable(String executable) {
    this.executable = executable;
  }


  public String getArguments() {
    return arguments;
  }


  public void setArguments(String arguments) {
    this.arguments = arguments;
  }


  public JTextField getTextFieldExecutable() {
    return textFieldExecutable;
  }


  public JTextField getTextFieldArguments() {
    return textFieldArguments;
  }


  public static void main(String[] args) {
    SolverSettingsDialog dialog = new SolverSettingsDialog((dia) -> {
    });
    dialog.pack();
    dialog.setVisible(true);
    System.exit(0);
  }
}
