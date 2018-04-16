package com.lgsim.engine.graphEditor.graph.component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class InstanceComponentTable extends JTable
{
  private static final long serialVersionUID = -5759644641889445534L;


  InstanceComponentTable()
  {
    super(new InstanceComponentTableModel());
    setAutoResizeMode(AUTO_RESIZE_OFF);
    DefaultTableCellRenderer defaultRenderer = (DefaultTableCellRenderer) getTableHeader().getDefaultRenderer();
    defaultRenderer.setHorizontalAlignment(SwingConstants.LEADING);
    setUpUnitColumn(getColumnModel().getColumn(InstanceComponentTableModel.UNIT_COLUMN));
  }


  @Override
  public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
  {
    Component comp = super.prepareRenderer(renderer, row, column);
    int rendererWidth = comp.getPreferredSize().width;
    TableColumn tableColumn = getColumnModel().getColumn(column);
    tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width,
                                           tableColumn.getPreferredWidth()));
    return comp;
  }


  private void setUpUnitColumn(TableColumn column)
  {
    JComboBox<String> comboBox = new JComboBox<>();
    comboBox.addItem("mm");
    comboBox.addItem("cm");
    comboBox.addItem("m");
    comboBox.addItem("km");
    column.setCellEditor(new DefaultCellEditor(comboBox));

    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    column.setCellRenderer(renderer);
  }


  @Override
  public TableCellRenderer getCellRenderer(int row, int column)
  {
    return new DefaultTableCellRenderer()
    {
      {
        setHorizontalAlignment(SwingConstants.LEADING);
      }
    };
  }
}
