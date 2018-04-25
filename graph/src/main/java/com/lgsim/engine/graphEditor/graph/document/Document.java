package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentFileImpl;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentImpl;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphStyleImpl;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public class Document extends GraphDocumentImpl {
  private mxGraphComponent graphComponent;
  private final Supplier<IApplicationAction> actionSupplier;

  public Document(@NotNull mxGraphComponent comp, @NotNull Supplier<IApplicationAction> actionSupplier)
  {
    super(null, new GraphDocumentFileImpl(), (IGraph) comp.getGraph(), new GraphStyleImpl(), false);
    this.actionSupplier = actionSupplier;
    setGraphComponent(comp);
  }

  @Override
  public @NotNull String getTitle()
  {
    return MessageBundle.get("graphDocument.newDocumentTitle") + (isModified() ? "*" : "");
  }

  public @NotNull mxGraphComponent getGraphComponent()
  {
    return graphComponent;
  }

  private void setGraphComponent(@NotNull mxGraphComponent graphComponent)
  {
    this.graphComponent = graphComponent;
  }

  public IApplicationAction getApplicationAction() {
    return actionSupplier.get();
  }

  public void save() {

  }
}
