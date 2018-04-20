package com.lgsim.engine.graphEditor.api.data;

import com.lgsim.engine.graphEditor.api.exception.DecodeException;
import com.lgsim.engine.graphEditor.api.exception.EncodeException;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface IGraphCodec
{
  /**
   * 从图中读取信息，生成数据流
   *
   * @param graph 图数据模型
   * @return 序列化对象
   */
  @NotNull Serializable encode(@NotNull IGraph graph) throws EncodeException;


  @NotNull IGraph decode(@NotNull Serializable serializable) throws DecodeException;
}
