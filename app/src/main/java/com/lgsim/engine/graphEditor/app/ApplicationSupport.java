package com.lgsim.engine.graphEditor.app;

import com.lgsim.engine.graphEditor.api.widget.topLevel.IMenuBar;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings("WeakerAccess")
public class ApplicationSupport {
  public static @NotNull JMenuBar createApplicationMenuBar() throws InstantiationException {
    IMenuBar menuBar = ImplementationUtil.getInstanceOf(IMenuBar.class);
    return menuBar.getMenuBar();
  }
}
