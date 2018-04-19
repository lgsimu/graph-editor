package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.widget.IWidget;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.widget.Component.TablePanel;
import com.lgsim.engine.graphEditor.widget.PoJo.RowContent;
import org.jetbrains.annotations.NotNull;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TablePanelImp implements IVertexTable {

    TablePanel tablePanel = new TablePanel();

    @Override
    public JComponent getSwingComponent() {
        return tablePanel;
    }

    @Override
    public void render(@NotNull IVertex vertex) {
        tablePanel.showTable(vertex);
    }
}
