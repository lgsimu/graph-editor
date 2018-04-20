package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

public interface IGraphEditor
{
  /**
   * 获取当前的图文档
   *
   * @return 当前显示的图文档，如果没有，返回{@code null}
   */
  @Nullable IGraphDocument getCurrentGraphDocument();


  /**
   * 获取打开的图文档
   *
   * @return 打开的图文档列表，如果没有，返回空列表
   */
  @NotNull List<IGraphDocument> getOpenedGraphDocuments();


  /**
   * 打开图文档
   *
   * @param file 要读取的文件
   * @return 从磁盘文件中读取图文档，如果有异常，返回{@code null}
   */
  @Nullable IGraphDocument openGraphDocument(@NotNull File file);


  /**
   * 保存打开的图文档
   *
   * @param documents 要保存的图文档
   */
  void saveOpenedGraphDocuments(@NotNull List<IGraphDocument> documents);
}
