package com.lgsim.engine.graphEditor.api.data;

import java.util.Collection;

public interface IGraph
{
  /**
   * 获取图中所有的节点
   */
  Collection<IVertex> getAllVertexes();
}
