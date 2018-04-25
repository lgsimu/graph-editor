package com.lgsim.engine.graphEditor.graph.action;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ActionSupport {
  private static final Logger log = LoggerFactory.getLogger(ActionSupport.class);

  @Contract(pure = true)
  public static Action defaultAction() {
    return new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        log.debug("default action performed");
      }
    };
  }

  @Contract(pure = true)
  public static @NotNull ActionEvent createActionEvent(@NotNull JComponent comp, @NotNull ActionEvent evt) {
    return new ActionEvent(comp, evt.getID(), evt.getActionCommand());
  }
}
