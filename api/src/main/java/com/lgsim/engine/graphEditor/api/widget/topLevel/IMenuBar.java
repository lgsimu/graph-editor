package com.lgsim.engine.graphEditor.api.widget.topLevel;

import com.lgsim.engine.graphEditor.api.widget.IWidget;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings("unused")
public interface IMenuBar extends IWidget {
  @NotNull JMenuBar getMenuBar();
}
