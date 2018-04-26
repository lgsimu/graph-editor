package com.lgsim.engine.graphEditor.app;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.widget.IApplicationMenuBar;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings("WeakerAccess")
public class ApplicationSupport {

  public static @NotNull JMenuBar createApplicationMenuBar(@NotNull IApplication application)
      throws InstantiationException
  {
    IApplicationMenuBar applicationMenuBar = ImplementationUtil.getInstanceOf(IApplicationMenuBar.class);
    applicationMenuBar.setApplication(application);
    return applicationMenuBar.getSwingComponent();
  }
}
