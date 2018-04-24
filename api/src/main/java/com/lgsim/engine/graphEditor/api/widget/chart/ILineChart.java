package com.lgsim.engine.graphEditor.api.widget.chart;

import com.lgsim.engine.graphEditor.api.widget.IWidget;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public interface ILineChart extends IWidget {
  void render(@NotNull Object x);
}
