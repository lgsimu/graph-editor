package com.lgsim.engine.graphEditor.api.widget.table;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.widget.IWidget;
import org.jetbrains.annotations.NotNull;

public interface IVertexTable extends IWidget
{
  void render(@NotNull IVertex vertex);
}
