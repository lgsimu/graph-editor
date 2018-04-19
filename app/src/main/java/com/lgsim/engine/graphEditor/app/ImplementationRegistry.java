package com.lgsim.engine.graphEditor.app;

import com.lgsim.engine.graphEditor.api.IRegistry;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.graph.editor.EditorGraph;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;

class ImplementationRegistry implements IRegistry
{
  static final IRegistry INSTANCE = new ImplementationRegistry();


  @Override
  public void registerAll()
  {
    ImplementationUtil.put(IGraph.class, EditorGraph.class);
  }
}
