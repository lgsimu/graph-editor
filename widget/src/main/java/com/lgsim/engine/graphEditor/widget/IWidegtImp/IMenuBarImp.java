package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.widget.IApplicationMenuBar;
import com.lgsim.engine.graphEditor.widget.Component.MenuBar;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class IMenuBarImp implements IApplicationMenuBar {

  private IApplication application;

  private MenuBar menuBar = new MenuBar();




  @Override
  public @NotNull IApplication getApplication() {
    return application;
  }

  @Override
  public void setApplication(@NotNull IApplication application) {

    this.application = application;

    IApplicationAction action = application.getApplicationAction();
    menuBar.bindAction(action);

    }

  @Override
  public @NotNull JMenuBar getSwingComponent() {
    return menuBar;
  }
}
