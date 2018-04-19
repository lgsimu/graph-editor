package com.lgsim.engine.graphEditor.app;

import com.lgsim.engine.graphEditor.api.IRegistry;
import com.mxgraph.util.mxResources;

public class ResourceRegistry implements IRegistry
{
  static final IRegistry INSTANCE = new ResourceRegistry();


  @Override
  public void registerAll()
  {
    mxResources.add("com/lgsim/engine/graphEditor/graph/messages");
  }
}
