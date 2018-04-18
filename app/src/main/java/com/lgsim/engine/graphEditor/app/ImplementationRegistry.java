package com.lgsim.engine.graphEditor.app;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.graph.editor.EditorGraph;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;

class ImplementationRegistry
{
  static void registerAll()
  {
    ImplementationUtil.put(IGraph.class, EditorGraph.class);
  }
}
