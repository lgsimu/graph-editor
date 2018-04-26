package com.lgsim.engine.graphEditor.api.widget;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.function.Supplier;

public interface IToolbar extends IWidget {
  @NotNull JToolBar getToolBar();


  void setActionSupplier(@NotNull Supplier<IApplicationAction> supplier);


  void paint();
}
