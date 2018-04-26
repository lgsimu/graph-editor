package com.lgsim.engine.graphEditor.app;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.widget.IApplicationMenuBar;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings("WeakerAccess")
public class ApplicationSupport {

  public static @NotNull JMenuBar createApplicationMenuBar(@NotNull IApplicationAction action)
      throws InstantiationException
  {
    IApplicationMenuBar topLevelMenuBar = ImplementationUtil.getInstanceOf(IApplicationMenuBar.class);
    topLevelMenuBar.setApplicationAction(action);
    return topLevelMenuBar.getSwingComponent();
  }
}
