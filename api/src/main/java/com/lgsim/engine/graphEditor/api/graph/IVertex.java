package com.lgsim.engine.graphEditor.api.graph;

import java.util.List;

public interface IVertex
{
  List<IVertexArgument> getArguments();


  List<IVertexOutput> getOutputs();
}
