package com.lgsim.engine.graphEditor.api.graph;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collection;

public interface IGraphDocumentCodec
{
  /**
   * 从可序列化数据源
   *
   * @param serializable
   * @return
   */
  @NotNull Collection<IVertexStyle> encodeStyles(@NotNull Serializable serializable);
}
