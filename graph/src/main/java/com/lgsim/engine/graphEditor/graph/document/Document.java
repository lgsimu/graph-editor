package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentImpl;
import com.lgsim.engine.graphEditor.api.widget.IApplicationWidget;
import com.lgsim.engine.graphEditor.graph.Editor;
import com.lgsim.engine.graphEditor.graph.action.ApplicationActionImpl;
import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.graph.graph.GraphComponent;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;

@SuppressWarnings({"WeakerAccess"})
public class Document extends GraphDocumentImpl implements IApplicationWidget {

  private static final Logger log = LoggerFactory.getLogger("graph.document.Document");

  private Editor editor;
  private IApplication application;
  private GraphComponent swingComponent;


  public Document() {
  }


  public Document(@NotNull Editor editor)
  {
    this.editor = editor;
    this.application = editor.getApplication();
    this.swingComponent = new GraphComponent(new Graph());
    settingGraphComponent();
  }


  private void settingGraphComponent() {
    swingComponent.setMinimumSize(new Dimension(320, 320));
    swingComponent.setColumnHeaderView(new DocumentRuler(swingComponent, DocumentRuler.ORIENTATION_HORIZONTAL));
    swingComponent.setRowHeaderView(new DocumentRuler(swingComponent, DocumentRuler.ORIENTATION_VERTICAL));
    application.setApplicationAction(new ApplicationActionImpl(this));
    DocumentSupport.installOutlineListeners(this);
    DocumentSupport.installGraphDocumentListeners(this);
  }


  @Override
  public @NotNull String getTitle()
  {
    return MessageBundle.get("graphDocument.newDocumentTitle") + (isModified() ? "*" : "");
  }


  @Override
  public @NotNull mxGraphComponent getSwingComponent()
  {
    return swingComponent;
  }


  public void setSwingComponent(GraphComponent swingComponent) {
    this.swingComponent = swingComponent;
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


  public void output() throws IOException {
    editor.getDocumentContext().put(this);
  }


  public Editor getEditor() {
    return editor;
  }
}
