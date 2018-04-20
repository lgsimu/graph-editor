package com.lgsim.engine.graphEditor.api.data;

import java.io.Serializable;
import java.util.Collection;

public interface IGraph extends Serializable
{
  /**
   * 获取图中所有的节点
   */
  Collection<IVertex> getAllVertexes();
}
