package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collection;

public interface IGraph extends Serializable {

  /**
   * 获取图中所有的节点
   */
  Collection<IVertex> getAllVertexes();


  /**
   * 从图{@code graph}中取出计算后的输出
   *
   * @param graph 图
   */
  void retrieveCalcOutputs(@NotNull IGraph graph);
}
