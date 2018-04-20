package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface IGraphEncoder
{
  /**
   * 从图中读取信息，生成数据流
   *
   * @param graph 图数据模型
   * @return 序列化对象
   * @throws Exception 编码过程出现读写异常
   */
  @NotNull Serializable encode(@NotNull IGraph graph) throws Exception;
}
