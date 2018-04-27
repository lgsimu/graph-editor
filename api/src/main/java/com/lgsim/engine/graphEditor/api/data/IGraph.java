package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collection;

public interface IGraph extends Serializable {

  /**
   * 获取图中的节点
   * @return 图中的节点
   */
  @NotNull Collection<IVertex> getVertexes();


  /**
   * 从图{@code graph}中取出计算后的输出
   *
   * @param graph 图
   */
  void retrieveCalcOutputs(@NotNull IGraph graph);
}
