package com.lgsim.engine.graphEditor.api.widget;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * 应用的工具栏
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface IApplicationToolbar extends IApplicationWidget {

  @Override
  @NotNull JToolBar getSwingComponent();
}
