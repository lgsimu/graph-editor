package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.graph.document.Document;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ApplicationActionImpl implements IApplicationAction {

  private Document document;


  public ApplicationActionImpl(@NotNull Document document) {
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


  @Override
  public @NotNull Action getSolverSettingsAction() {
    return new SolverSettingAction();
  }


  @Override
  public @NotNull Action getStandardAction() {
    return null;
  }


  @Override
  public @NotNull Action getLayoutAction() {
    return null;
  }


  @Override
  public @NotNull Action getMoveAction() {
    return null;
  }


  @Override
  public @NotNull Action getFormatAction() {
    return null;
  }


  @Override
  public @NotNull Action getToolAction() {
    return null;
  }


  @Override
  public @NotNull Action getViewAction() {
    return null;
  }


  @Override
  public @NotNull Action getControlAction() {
    return null;
  }


  @Override
  public @NotNull Action getBankAction() {
    return null;
  }


  @Override
  public @NotNull Action getCustomAction() {
    return null;
  }


  public Document getDocument() {
    return document;
  }


  public void setDocument(Document document) {
    this.document = document;
  }


  @Override
  public @NotNull Action getSolverCalcAction() {
    return null;
  }


  @Override
  public @NotNull Action getSolverSettingAction() {
    return null;
  }
}
