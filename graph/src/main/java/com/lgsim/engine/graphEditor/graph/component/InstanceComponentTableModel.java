package com.lgsim.engine.graphEditor.graph.component;

import com.lgsim.engine.graphEditor.graph.util.MessageBundleUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.table.AbstractTableModel;

public class InstanceComponentTableModel extends AbstractTableModel
{
  private static final int VAL_COLUMN = 1;
  public static final int UNIT_COLUMN = 2;
  private static final long serialVersionUID = -6601761714146088118L;
  private final String[] columnNames = new String[]{
      MessageBundleUtil.get("propertyEditor.name"),
      MessageBundleUtil.get("propertyEditor.value"),
      MessageBundleUtil.get("propertyEditor.unit"),
      MessageBundleUtil.get("propertyEditor.description")
  };
  private transient Object[][] data;


  InstanceComponentTableModel()
  {
    this.data = new Object[][]{};
  }


  @Override
  public int getRowCount()
  {
    return data.length;
  }


  @Override
  public int getColumnCount()
  {
    return columnNames.length;
  }


  @Override
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    return data[rowIndex][columnIndex];
  }


  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex)
  {
    data[rowIndex][columnIndex] = aValue;
    fireTableCellUpdated(rowIndex, columnIndex);
  }


  @Override
  public String getColumnName(int column)
  {
    return columnNames[column];
  }


  @Override
  public Class<?> getColumnClass(int columnIndex)
  {
    return getValueAt(0, columnIndex).getClass();
  }


  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex)
  {
    return (columnIndex == VAL_COLUMN) || (columnIndex == UNIT_COLUMN);
  }


  public Object[][] getData()
  {
    return data;
  }


  public void setData(@NotNull Object[][] data)
  {
    this.data = data;
    fireTableDataChanged();
  }
}
