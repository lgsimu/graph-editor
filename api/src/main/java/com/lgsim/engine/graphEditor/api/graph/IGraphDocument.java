package com.lgsim.engine.graphEditor.api.graph;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public interface IGraphDocument
{
  /**
   * 获取标题
   *
   * @return 图文档标题
   */
  @NotNull
  String getTitle();

  /**
   * 获取文件
   */
  @Nullable
  File getFile();

  /**
   * 获取图
   */
  @NotNull
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
