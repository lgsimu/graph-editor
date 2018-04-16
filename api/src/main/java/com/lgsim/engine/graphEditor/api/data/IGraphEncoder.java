package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface IGraphEncoder
{
  /**
   * 从图中读取信息，生成数据流
   */
  Serializable encode(@NotNull IGraph graph);
}
