package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

public interface IGraphEncoder
{
  /**
   * 从图中读取信息，生成数据流
   */
  byte[] encode(@NotNull IGraph graph);
}
