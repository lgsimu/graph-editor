package com.lgsim.engine.graphEditor.api.graph;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import org.jetbrains.annotations.NotNull;

public interface IGraphDocument
{
  /**
   * 获取标题
   *
   * @return 图文档标题，不能含英文点
   */
  @NotNull String getTitle();


  /**
   * 获取图文档文件
   *
   * @return 图文档文件
   */
  @NotNull IGraphDocumentFile getGraphDocumentFile();


  /**
   * 获取图的数据模型
   *
   * @return 数据模型
   */
  @NotNull IGraph getGraph();


  /**
   * 获取图的样式
   *
   * @return 图的样式
   */
  @NotNull IGraphStyle getGraphStyle();


  /**
   * 文档是否更改过
   */
  boolean isModified();


  /**
   * 设置文档是否更改过
   */
  void setModified(boolean b);
}
