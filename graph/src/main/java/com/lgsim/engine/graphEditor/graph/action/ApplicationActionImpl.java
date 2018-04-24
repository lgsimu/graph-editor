package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ApplicationActionImpl implements IApplicationAction {
  @Override
  public @NotNull Action getVertexCellCopyAction() {
    return new VertexCellCopyAction();
  }

  @Override
  public @NotNull Action getVertexCellPasteAction() {
    return new VertexCellPasteAction();
  }

  @Override
  public @NotNull Action getVertexCellDeleteAction() {
    return new VertexCellDeleteAction();
  }

  @Override
  public @NotNull Action getVertexCellCutAction() {
    return new VertexCellCutAction();
  }

  @Override
  public @NotNull Action getDocumentNewAction() {
    return new DocumentNewAction();
  }

  @Override
  public @NotNull Action getDocumentOpenAction() {
    return new DocumentOpenAction();
  }

  @Override
  public @NotNull Action getDocumentCloseAction() {
    return new DocumentCloseAction();
  }
  @Override
  public @NotNull Action getDocumentSaveAction() {
    return new DocumentSaveAction();
  }

  @Override
  public @NotNull Action getApplicationExitAction() {
    return new ApplicationExitAction();
  }
}
