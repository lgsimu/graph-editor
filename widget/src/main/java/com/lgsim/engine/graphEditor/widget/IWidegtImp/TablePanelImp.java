package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.widget.Component.TablePanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class TablePanelImp implements IVertexTable
{
  private TablePanel tablePanel = new TablePanel();


  public TablePanelImp() {}

  @Override
  public JComponent getSwingComponent()
  {
    return tablePanel;
  }


  @Override
  public void render(@NotNull IVertex vertex)
  {
    tablePanel.showTable(vertex);
  }
}
