package com.lgsim.engine.graphEditor.graph.document;

import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings({"WeakerAccess", "unused"})
public class DocumentAccelerator extends KeyAdapter {
  protected final Document document;
  protected final DocumentAcceleratorConsumer keyPressedConsumer;

  public DocumentAccelerator(@NotNull Document document) {
    this.document = document;
    this.keyPressedConsumer = DocumentSupport.createKeyPressedConsumer(document);
    document.getGraphComponent().addKeyListener(this);
  }

  @Override
  public void keyPressed(KeyEvent event) {
    keyPressedConsumer.consume(event);
  }

  public Document getDocument() {
    return document;
  }

  public DocumentAcceleratorConsumer getKeyPressedConsumer() {
    return keyPressedConsumer;
  }
}
