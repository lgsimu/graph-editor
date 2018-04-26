package com.lgsim.engine.graphEditor.api.widget.topLevel;

import com.lgsim.engine.graphEditor.api.widget.IApplicationWidget;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings("unused")
public interface ITopLevelMenuBar extends IApplicationWidget {

  @Override
  @NotNull JMenuBar getSwingComponent();
}
