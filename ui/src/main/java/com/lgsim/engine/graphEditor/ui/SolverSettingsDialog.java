package com.lgsim.engine.graphEditor.ui;

import com.lgsim.engine.graphEditor.ui.bean.SolverSettingsBean;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.BiConsumer;
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

  private BiConsumer<SolverSettingsDialog, SolverSettingsBean> consumeSettings;


  public SolverSettingsDialog(@NotNull BiConsumer<SolverSettingsDialog, SolverSettingsBean> consumeSettings) {
    this.consumeSettings = consumeSettings;

    setMinimumSize(new Dimension(400, 180));
    setResizable(false);
    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);

    buttonOK.addActionListener(e -> commitSettings());

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


  private void commitSettings() {
    String caseName = UISupport.getText(textFieldCaseName);
    String executable = UISupport.getText(textFieldExecutable);
    String arguments = UISupport.getText(textFieldArguments);
    SolverSettingsBean bean = new SolverSettingsBean(caseName, executable, arguments);
    dispose();
    consumeSettings.accept(this, bean);
  }


  public Consumer<SolverSettingsBean> updateUI() {
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
    SolverSettingsDialog dialog = new SolverSettingsDialog((p, q) -> {
    });
    dialog.pack();
    dialog.setVisible(true);
    System.exit(0);
  }
}
