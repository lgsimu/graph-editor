package com.lgsim.engine.graphEditor.api.widget;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * 应用菜单栏
 */
@SuppressWarnings("unused")
public interface IApplicationMenuBar extends IApplicationWidget {

  @Override
  @NotNull JMenuBar getSwingComponent();
}
