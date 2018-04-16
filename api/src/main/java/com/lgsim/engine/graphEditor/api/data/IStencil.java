package com.lgsim.engine.graphEditor.api.data;

import java.util.List;

public interface IStencil
{
  boolean isPredefined();


  String getId();


  String getName();


  String getStencilIcon();


  String getGraphIcon();


  List<IVertexArgument> getArguments();


  List<IVertexOutput> getOutputs();


  IVertexRestriction getRestriction();
}
