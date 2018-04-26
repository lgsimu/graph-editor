package com.lgsim.engine.graphEditor.graph.document;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("WeakerAccess")
public class DocumentCodec {

  @Contract(pure = true)
  public static @NotNull byte[] encode(@NotNull Document document) {
    // TODO 编码文档
    return null;
  }


  @Contract(pure = true)
  public static @NotNull Document decode(@NotNull byte[] bytes) {
    // 解码 编码文档
    return null;
  }
}
