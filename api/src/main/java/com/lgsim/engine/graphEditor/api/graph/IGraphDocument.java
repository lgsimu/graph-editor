package com.lgsim.engine.graphEditor.api.graph;

import com.lgsim.engine.graphEditor.api.data.IGraph;

import java.io.File;

public interface IGraphDocument
{
  /**
   * 获取标题
   */
  String getTitle();


  /**
   * 获取文件
   */
  File getFile();


  /**
   * 获取图
   */
  IGraph getGraph();


  /**
   * 文档是否更改过
   */
  boolean isModified();


  /**
   * 设置文档是否更改过
   */
  void setModified(boolean b);
}
