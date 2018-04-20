package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocumentFile;
import com.lgsim.engine.graphEditor.api.graph.IGraphStyle;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentImpl;
import com.lgsim.engine.graphEditor.graph.util.MessageBundleUtil;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

class GraphDocument extends GraphDocumentImpl
{
  private mxGraphComponent graphComponent;


  GraphDocument(@Nullable String title, @NotNull IGraphDocumentFile graphDocumentFile, @NotNull IGraph graph,
                @NotNull IGraphStyle graphStyle, boolean modified, @NotNull mxGraphComponent graphComponent)
  {
    super(title, graphDocumentFile, graph, graphStyle, modified);
    setGraphComponent(graphComponent);
  }


  @Override
  public @NotNull String getTitle()
  {
    final File file = getGraphDocumentFile().getEntryFile();
    if (file == null)
    {
      return MessageBundleUtil.get("newDiagram") + (isModified() ? "*" : "");
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
