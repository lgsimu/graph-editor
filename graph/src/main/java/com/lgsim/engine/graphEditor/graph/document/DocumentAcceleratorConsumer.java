package com.lgsim.engine.graphEditor.graph.document;

import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;

@SuppressWarnings("WeakerAccess")
public abstract class DocumentAcceleratorConsumer {

  protected GraphDocument document;

  public DocumentAcceleratorConsumer(GraphDocument document) {
    this.document = document;
  }

  public abstract void consume(@NotNull KeyEvent event);
}
