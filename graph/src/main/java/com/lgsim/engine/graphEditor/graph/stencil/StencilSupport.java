package com.lgsim.engine.graphEditor.graph.stencil;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class StencilSupport {

  @Contract(pure = true)
  public static @NotNull StencilPalette createStencilPalette() {
    return new StencilPalette();
  }
}
