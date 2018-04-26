package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.widget.topLevel.ITopLevelMenuBar;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class IMenuBarImp implements ITopLevelMenuBar {

    private IApplicationAction action;

    @Override
    public @NotNull JMenuBar getSwingComponent() {
        return null;
    }


    @Override
    public @NotNull IApplicationAction getApplicationAction() {
        return action;
    }


    @Override
    public void setApplicationAction(@NotNull IApplicationAction action) {
        this.action = action;
    }
}
