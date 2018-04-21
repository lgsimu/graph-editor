package com.lgsim.engine.graphEditor.api.data;

import com.lgsim.engine.graphEditor.util.exception.DecodeException;
import com.lgsim.engine.graphEditor.util.exception.EncodeException;
import org.jetbrains.annotations.NotNull;

public interface IGraphCodec
{
  /**
   * 将图数据模型编码为字节数组
   *
   * @param graph 图数据模型
   * @return 字节数组
   * @throws EncodeException 如果编码过程中发生任何其它异常
   */
  @NotNull byte[] encode(@NotNull IGraph graph) throws EncodeException;


  /**
   * 从字节数组中解码图数据模型
   *
   * @param bytes 字节数组
   * @return 图数据模型
   * @throws DecodeException 如果解码过程中发生任何其它异常
   */
  @NotNull IGraph decode(@NotNull byte[] bytes) throws DecodeException;
}
