package com.lgsim.engine.graphEditor.api;

import com.lgsim.engine.graphEditor.api.widget.IWidget;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public interface IPopupMenu extends IWidget {
  @Override
  @NotNull JPopupMenu getSwingComponent();
}
