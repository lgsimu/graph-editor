package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.Nullable;

public interface IEditor {

  /**
   * 获取编辑的文档
   *
   * @return 编辑的文档，如果没有，返回{@code null}
   */
  @Nullable IDocument getDocument();
}
