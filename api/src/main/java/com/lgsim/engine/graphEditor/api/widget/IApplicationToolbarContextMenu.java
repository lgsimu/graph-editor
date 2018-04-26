package com.lgsim.engine.graphEditor.api.widget;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * 工具栏的右键菜单
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface IApplicationToolbarContextMenu extends IApplicationContextMenu {

  @Override
  @NotNull JPopupMenu getSwingComponent();
}
