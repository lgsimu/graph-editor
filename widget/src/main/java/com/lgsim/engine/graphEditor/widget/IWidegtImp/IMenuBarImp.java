package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.widget.IApplicationMenuBar;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class IMenuBarImp implements IApplicationMenuBar {

  private IApplication application;


  @Override
  public @NotNull JMenuBar getSwingComponent() {
    return new JMenuBar();
  }


  @Override
  public @NotNull IApplication getApplication() {
    return application;
  }


  @Override
  public void setApplication(@NotNull IApplication application) {
    this.application = application;
  }
}
