package com.lgsim.engine.graphEditor.api.widget.console;

import com.lgsim.engine.graphEditor.api.widget.IWidget;
import org.jetbrains.annotations.NotNull;

public interface IConsole extends IWidget
{
  /**
   * 在控制台中打印一行文本
   *
   * @param line 文本
   */
  void println(@NotNull String line);
}
