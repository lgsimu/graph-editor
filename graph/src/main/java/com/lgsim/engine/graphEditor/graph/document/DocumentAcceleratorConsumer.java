package com.lgsim.engine.graphEditor.graph.document;

import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;

@SuppressWarnings("WeakerAccess")
public abstract class DocumentAcceleratorConsumer {

  protected Document document;

  public DocumentAcceleratorConsumer(Document document) {
    this.document = document;
  }

  public abstract void consume(@NotNull KeyEvent event);
}
