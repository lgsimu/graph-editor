package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentFileImpl;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentImpl;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphStyleImpl;
import com.lgsim.engine.graphEditor.api.widget.IApplicationWidget;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class Document extends GraphDocumentImpl implements IApplicationWidget {

  private mxGraphComponent graphComponent;
  private final DocumentContext context;
  private transient IApplication application;


  public Document(@NotNull DocumentContext context, @NotNull mxGraphComponent graphComponent)
  {
    super(null, new GraphDocumentFileImpl(), (IGraph) graphComponent.getGraph(), new GraphStyleImpl(), false);
    this.context = context;
    this.graphComponent = graphComponent;
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
    context.put(this);
  }
}
