package com.lgsim.engine.graphEditor.widget.Test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class Main extends JFrame {
  private JToolBar toolbar;
  private JLabel shapeLabel;
  private JComboBox shapeChooser;
  private JLabel colorLabel;
  private JComboBox colorChooser;

  private String colorNames[] = { "Black", "Blue", "Cyan", "Dark Gray", "Gray",
      "Green", "Light Gray", "Magenta", "Orange", "Pink", "Red", "White",
      "Yellow", "Custom" };

  private String shapeNames[] = { "Line", "Oval", "Rectangle", "3D Rectangle",
      "Paint Brush", "Rounded Rectangle" };

  public Main() {
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 200);
    add(createToolBar(), BorderLayout.PAGE_START);

    setVisible(true);
  }

  public void addToToolbar(Component component, int row, int column) {
    Dimension d = component.getPreferredSize();
    component.setMaximumSize(d);
    component.setMinimumSize(d);
    component.setPreferredSize(d);
    toolbar.add(component);

  }

  public final JToolBar createToolBar() {

    toolbar = new JToolBar();

    shapeLabel = new JLabel("Shapes: ");
    addToToolbar(shapeLabel, 0, 1);

    shapeChooser = new JComboBox(shapeNames);
    shapeChooser.setSelectedIndex(0);
    addToToolbar(shapeChooser, 0, 2);
    colorLabel = new JLabel("Colors: ");

    addToToolbar(colorLabel, 0, 3);

    colorChooser = new JComboBox(colorNames);
    addToToolbar(colorChooser, 0, 4);

    return toolbar;
  }

  public static void main(String args[]) {
    new Main();

  }
}
