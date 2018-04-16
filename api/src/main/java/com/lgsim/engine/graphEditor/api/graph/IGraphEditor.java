package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public interface IGraphEditor
{
  /**
   * 获取当前的图文档
   */
  IGraphDocument getCurrentGraphDocument();


  /**
   * 获取打开的图文档
   */
  List<IGraphDocument> getOpenedGraphDocuments();


  /**
   * 打开图文档
   */
  IGraphDocument openGraphDocument(@NotNull File file);


  /**
   * 保存图文档
   */
  void saveGraphDocument(@NotNull IGraphDocument document, @NotNull File file);
}
