package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.widget.IApplicationToolbar;
import com.lgsim.engine.graphEditor.widget.Component.ApplicationToolBar;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ApplicationToolbarImpl implements IApplicationToolbar {

    private ApplicationToolBar applicationToolBar = new ApplicationToolBar();

    private IApplication application;

    public ApplicationToolbarImpl() {
    }

    @Override
    public @NotNull JToolBar getSwingComponent() {
        return applicationToolBar;
    }

    @Override
    public @NotNull IApplication getApplication() {
        return application;
    }

    @Override
    public void setApplication(@NotNull IApplication application) {

          this.application = application;

          IApplicationAction action = application.getApplicationAction();

          applicationToolBar.bindActions(action);
    }
}
