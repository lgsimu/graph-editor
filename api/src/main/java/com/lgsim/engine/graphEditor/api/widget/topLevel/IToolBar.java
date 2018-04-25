package com.lgsim.engine.graphEditor.api.widget.topLevel;

import com.lgsim.engine.graphEditor.api.widget.IWidget;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public interface IToolBar extends IWidget {
    @NotNull JToolBar getToolBar();
}
