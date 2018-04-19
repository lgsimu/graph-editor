package com.lgsim.engine.graphEditor.api.widget;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public interface IWidget
{
  /**
   * 获取小部件的swing组件
   */
  @NotNull
  JComponent getSwingComponent();
}
