package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ApplicationActionImpl implements IApplicationAction {
  private static final Action defaultAction = ActionSupport.defaultAction();

  @Override
  public @NotNull Action getVertexCellCopyAction() {
    return defaultAction;
  }
  @Override
  public @NotNull Action getVertexCellPasteAction() {
    return defaultAction;
  }
  @Override
  public @NotNull Action getVertexCellDeleteAction() {
    return defaultAction;
  }
  @Override
  public @NotNull Action getVertexCellCutAction() {
    return defaultAction;
  }
  @Override
  public @NotNull Action getDocumentNewAction() {
    return defaultAction;
  }
  @Override
  public @NotNull Action getDocumentOpenAction() {
    return defaultAction;
  }
  @Override
  public @NotNull Action getDocumentCloseAction() {
    return defaultAction;
  }
  @Override
  public @NotNull Action getDocumentSaveAction() {
    return defaultAction;
  }
  @Override
  public @NotNull Action getApplicationExitAction() {
    return defaultAction;
  }
}
