package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.widget.topLevel.ITopLevelMenuBar;
import com.lgsim.engine.graphEditor.widget.Component.MenuBar;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class IMenuBarImp implements ITopLevelMenuBar {

    private MenuBar menuBar = new MenuBar();

    @Override
    public @NotNull JComponent getSwingComponent() {
        return menuBar;
    }

    public IMenuBarImp(){}

    @Override
    public @NotNull JMenuBar getMenuBar() {
        return menuBar;
    }
}
