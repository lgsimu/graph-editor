package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

public interface IGraphDecoder
{
  /**
   * 从输入数据流中解码出图
   */
  IGraph decode(@NotNull byte[] bytes);
}
