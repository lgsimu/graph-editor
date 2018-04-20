package com.lgsim.engine.graphEditor.api.graph;

import com.lgsim.engine.graphEditor.util.exception.DecodeException;
import com.lgsim.engine.graphEditor.util.exception.EncodeException;
import org.jetbrains.annotations.NotNull;

public interface IGraphStyleCodec
{
  /**
   * 将图样式编码成可序列化对象
   *
   * @param style 图样式
   * @return 可序列化的对象
   */
  @NotNull byte[] encode(@NotNull IGraphStyle style) throws EncodeException;


  /**
   * 从可序列化对象中解码图样式
   *
   * @param bytes 可序列化对象
   * @return 图样式
   */
  @NotNull IGraphStyle decode(@NotNull byte[] bytes) throws DecodeException;
}
