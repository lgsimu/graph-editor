package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface IGraphStyleCodec
{
  /**
   * 将图样式编码成可序列化对象
   *
   * @param style 图样式
   * @return 可序列化的对象
   */
  @NotNull Serializable encode(@NotNull IGraphStyle style);


  /**
   * 从可序列化对象中解码图样式
   *
   * @param serializable 可序列化对象
   * @return 图样式
   */
  @NotNull IGraphStyle decode(@NotNull Serializable serializable);
}
