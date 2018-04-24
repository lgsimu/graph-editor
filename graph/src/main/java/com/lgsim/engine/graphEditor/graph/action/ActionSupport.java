package com.lgsim.engine.graphEditor.graph.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("WeakerAccess")
public class ActionSupport {
  private static final Logger log = LoggerFactory.getLogger(ActionSupport.class);

  public static Action defaultAction() {
    return new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        log.debug("default action performed");
      }
    };
  }
}
