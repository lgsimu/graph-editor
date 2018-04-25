package com.lgsim.engine.graphEditor.graph.document;

import org.jetbrains.annotations.NotNull;

import java.io.File;

@SuppressWarnings("WeakerAccess")
public class DocumentContext {
  private final File workDir;

  public DocumentContext(@NotNull File workDir) {
    this.workDir = workDir;
  }

  public void put(@NotNull Document document) {
  }
}
