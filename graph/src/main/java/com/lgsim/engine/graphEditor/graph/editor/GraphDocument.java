package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentFileImpl;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentImpl;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphStyleImpl;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;

import java.io.File;

class GraphDocument extends GraphDocumentImpl
{
  private mxGraphComponent graphComponent;


  GraphDocument(@NotNull mxGraphComponent comp)
  {
    super(null, new GraphDocumentFileImpl(), (IGraph) comp.getGraph(), new GraphStyleImpl(), false);
    setGraphComponent(comp);
  }


  @Override
  public @NotNull String getTitle()
  {
    final File file = getGraphDocumentFile().getEntryFile();
    if (file == null)
    {
      return MessageBundle.get("newDiagram") + (isModified() ? "*" : "");
    }
    else
    {
      return file.getAbsolutePath();
    }
  }


  @NotNull mxGraphComponent getGraphComponent()
  {
    return graphComponent;
  }


  private void setGraphComponent(@NotNull mxGraphComponent graphComponent)
  {
    this.graphComponent = graphComponent;
  }
}
