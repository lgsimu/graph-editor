package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.graph.util.MessageBundleUtil;
import com.mxgraph.swing.mxGraphComponent;

import java.io.File;

public class GraphDocument
{
  private File file;
  private boolean modified;
  private mxGraphComponent graphComponent;


  public GraphDocument(File file, mxGraphComponent graphComponent)
  {
    this.file = file;
    this.graphComponent = graphComponent;
  }


  public File getFile()
  {
    return file;
  }


  public void setFile(File file)
  {
    this.file = file;
  }


  public boolean isModified()
  {
    return modified;
  }


  public void setModified(boolean modified)
  {
    this.modified = modified;
  }


  public mxGraphComponent getGraphComponent()
  {
    return graphComponent;
  }


  public void setGraphComponent(mxGraphComponent graphComponent)
  {
    this.graphComponent = graphComponent;
  }


  public String getTitle()
  {
    if (file == null)
    {
      return MessageBundleUtil.get("newDiagram") + (modified ? "*" : "");
    }
    else
    {
      return file.getAbsolutePath();
    }
  }
}
