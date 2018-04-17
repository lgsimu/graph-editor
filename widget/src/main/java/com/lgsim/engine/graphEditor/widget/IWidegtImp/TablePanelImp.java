package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.widget.IWidget;
import com.lgsim.engine.graphEditor.widget.Component.TablePanel;
import com.lgsim.engine.graphEditor.widget.PoJo.RowContent;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TablePanelImp implements IWidget {

    List<IVertexArgument> rowContents = new ArrayList<>();

    @Override
    public JComponent getSwingComponent() {
        return new TablePanel(rowContents);
    }
}
