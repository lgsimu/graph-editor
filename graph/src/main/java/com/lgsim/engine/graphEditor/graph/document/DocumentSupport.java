package com.lgsim.engine.graphEditor.graph.document;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"WeakerAccess"})
public class DocumentSupport {

  @Contract(pure = true)
  public static @NotNull DocumentAcceleratorConsumer createKeyPressedConsumer(@NotNull Document document) {
    return new DocumentAcceleratorKeyPressedConsumer(document);
  }
}
