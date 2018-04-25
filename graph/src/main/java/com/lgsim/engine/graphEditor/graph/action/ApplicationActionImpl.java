package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.graph.document.GraphDocument;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ApplicationActionImpl implements IApplicationAction {
  private GraphDocument document;

  public ApplicationActionImpl(@NotNull GraphDocument document) {
    this.document = document;
  }

  @Override
  public @NotNull Action getVertexCellCopyAction() {
    return new VertexCellCopyAction(document);
  }

  @Override
  public @NotNull Action getVertexCellPasteAction() {
    return new VertexCellPasteAction(document);
  }

  @Override
  public @NotNull Action getVertexCellDeleteAction() {
    return new VertexCellDeleteAction(document);
  }

  @Override
  public @NotNull Action getVertexCellCutAction() {
    return new VertexCellCutAction(document);
  }

  @Override
  public @NotNull Action getDocumentNewAction() {
    return new DocumentNewAction(document);
  }

  @Override
  public @NotNull Action getDocumentOpenAction() {
    return new DocumentOpenAction(document);
  }

  @Override
  public @NotNull Action getDocumentCloseAction() {
    return new DocumentCloseAction(document);
  }
  @Override
  public @NotNull Action getDocumentSaveAction() {
    return new DocumentSaveAction(document);
  }

  @Override
  public @NotNull Action getApplicationExitAction() {
    return new ApplicationExitAction();
  }

  public GraphDocument getDocument() {
    return document;
  }

  public void setDocument(GraphDocument document) {
    this.document = document;
  }
}
