package com.lgsim.engine.graphEditor.graph.component;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class InstanceComponentParameterEditor extends JPanel
{
  private static final long serialVersionUID = -3804451495949197587L;
  private final InstanceComponentTable table;


  public InstanceComponentParameterEditor()
  {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setMinimumSize(new Dimension(320, 320));
    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane);
    table = createInstanceComponentTable();
    scrollPane.add(table.getTableHeader());
    scrollPane.add(table);
    scrollPane.setViewportView(table);
  }


  @NotNull
  private static InstanceComponentTable createInstanceComponentTable()
  {
    return new InstanceComponentTable();
  }


  public InstanceComponentTable getTable()
  {
    return table;
  }
}
