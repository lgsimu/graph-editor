package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.IToolbar;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.widget.Component.ToolBox;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.function.Supplier;

public class IToolBarImp implements IToolbar {

    private ToolBox box = new ToolBox();
    @Override
    public @NotNull JToolBar getToolBar() {
        return box;
    }

    @Override
    public @NotNull JComponent getSwingComponent() {
        return box;
    }

    public IToolBarImp(){}
    @Override
    public void setActionSupplier(@NotNull Supplier<IApplicationAction> supplier) {
        box.setActionSupplier(supplier);
    }

    @Override
    public void paint() {
        box.createTools();
    }
}
