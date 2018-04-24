package com.lgsim.engine.graphEditor.graph.event;

import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventCapture implements KeyListener {
  private static final Logger log = LoggerFactory.getLogger(KeyEventCapture.class);
  private final mxGraphComponent graphComponent;

  public KeyEventCapture(@NotNull mxGraphComponent graphComponent) {
    this.graphComponent = graphComponent;
    graphComponent.addKeyListener(this);
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    log.debug("key pressed");
    if ((e.getKeyCode() == KeyEvent.VK_C)) {
      log.debug("ctrl+c triggered");
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
