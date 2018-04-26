package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentImpl;
import com.lgsim.engine.graphEditor.api.widget.IApplicationWidget;
import com.lgsim.engine.graphEditor.graph.action.ApplicationActionImpl;
import com.lgsim.engine.graphEditor.graph.editor.Editor;
import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.graph.graph.GraphComponent;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Document extends GraphDocumentImpl implements IApplicationWidget {

  private Editor editor;
  private GraphComponent graphComponent;
  private IApplication application;


  public Document(@NotNull Editor editor)
  {
    this.editor = editor;
    this.graphComponent = new GraphComponent(new Graph());
    settingGraphComponent();
    editor.addDocument(this);
  }


  private void settingGraphComponent() {
    graphComponent.setMinimumSize(new Dimension(320, 320));
    graphComponent.setColumnHeaderView(new DocumentRuler(graphComponent, DocumentRuler.ORIENTATION_HORIZONTAL));
    graphComponent.setRowHeaderView(new DocumentRuler(graphComponent, DocumentRuler.ORIENTATION_VERTICAL));
    application.setApplicationAction(new ApplicationActionImpl(this));
  }


  @Override
  public @NotNull String getTitle()
  {
    return MessageBundle.get("graphDocument.newDocumentTitle") + (isModified() ? "*" : "");
  }


  @Override
  public @NotNull mxGraphComponent getSwingComponent()
  {
    return graphComponent;
  }


  public @NotNull IApplicationAction getApplicationAction() {
    return getApplication().getApplicationAction();
  }


  @Override
  public @NotNull IApplication getApplication() {
    return application;
  }


  @Override
  public void setApplication(@NotNull IApplication application) {
    this.application = application;
  }


  public void save() throws IOException {
  }


  public Editor getEditor() {
    return editor;
  }


  public void setEditor(Editor editor) {
    this.editor = editor;
  }
}
