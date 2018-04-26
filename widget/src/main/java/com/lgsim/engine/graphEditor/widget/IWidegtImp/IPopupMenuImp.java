package com.lgsim.engine.graphEditor.widget.IWidegtImp;


import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.widget.IApplicationContextMenu;
import com.lgsim.engine.graphEditor.widget.Component.ApplicationPopupMenu;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class IPopupMenuImp implements IApplicationContextMenu{

    private ApplicationPopupMenu popubMenu = new ApplicationPopupMenu();
    private IApplication application;

    public IPopupMenuImp(){}


    @Override
    public @NotNull IApplication getApplication() {
        return application;
    }

    @Override
    public void setApplication(@NotNull IApplication application) {

        this.application = application;

        IApplicationAction action = application.getApplicationAction();
        popubMenu.bindAction(action);


    }

    @Override
    public @NotNull JComponent getSwingComponent() {
        return popubMenu;
    }
}
