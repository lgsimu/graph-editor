package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
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
   * 保存一个图文档
   *
   * @param document 要保存的图文档
   */
  void saveOpenedGraphDocument(@NotNull IGraphDocument document) throws IOException;


  /**
   * 保存打开的图文档
   *
   * @param documents 要保存的图文档
   */
  void saveOpenedGraphDocuments(@NotNull List<IGraphDocument> documents);


  /**
   * 测试文件是不是图文档文件
   *
   * @param file 被测试的文件
   * @return 如果被测试文件是图文档文件，返回{@code true}，否则返回{code false}
   */
  boolean isGraphDocumentFile(@NotNull File file);
}
