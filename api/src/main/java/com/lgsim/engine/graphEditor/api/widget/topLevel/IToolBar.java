package com.lgsim.engine.graphEditor.api.widget.topLevel;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.widget.IWidget;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.function.Supplier;

public interface IToolBar extends IWidget {
  @NotNull JToolBar getToolBar();


  void setActionSupplier(@NotNull Supplier<IApplicationAction> supplier);
}
