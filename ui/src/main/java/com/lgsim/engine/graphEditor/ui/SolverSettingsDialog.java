package com.lgsim.engine.graphEditor.ui;

import com.lgsim.engine.graphEditor.ui.bean.SolverEnvBean;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

public class SolverSettingsDialog extends JDialog {

  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;
  private JTextField textFieldExecutable;
  private JTextField textFieldArguments;
  private JTextField textFieldCaseName;
  @SuppressWarnings("unused")
  private JButton buttonChooseExecutable;

  private Consumer<SolverEnvBean> userInputConsumer;


  public SolverSettingsDialog(@NotNull Consumer<SolverEnvBean> userInputConsumer) {
    this.userInputConsumer = userInputConsumer;

    setMinimumSize(new Dimension(400, 180));
    setResizable(false);
    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);

    buttonOK.addActionListener(e -> userInput());

    buttonCancel.addActionListener(e -> onCancel());

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

    contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                                       JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
  }


  private void userInput() {
    String executable = UISupport.getText(textFieldExecutable);
    String arguments = UISupport.getText(textFieldArguments);
    String caseName = UISupport.getText(textFieldCaseName);
    SolverEnvBean bean = new SolverEnvBean(caseName, executable, arguments);
    dispose();
    userInputConsumer.accept(bean);
  }


  public Consumer<SolverEnvBean> dialogInput() {
    return (bean) -> {
      textFieldExecutable.setText(bean.getExecutable());
      textFieldArguments.setText(bean.getArguments());
      textFieldCaseName.setText(bean.getCaseName());
    };
  }


  private void onCancel() {
    dispose();
  }


  public static void main(String[] args) {
    SolverSettingsDialog dialog = new SolverSettingsDialog((dia) -> {
    });
    dialog.pack();
    dialog.setVisible(true);
    System.exit(0);
  }
}
