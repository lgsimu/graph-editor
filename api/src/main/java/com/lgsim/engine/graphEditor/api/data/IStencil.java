package com.lgsim.engine.graphEditor.api.data;

import com.lgsim.engine.graphEditor.api.graph.IVertexArgument;
import com.lgsim.engine.graphEditor.api.graph.IVertexOutput;
import com.lgsim.engine.graphEditor.api.graph.IVertexRestriction;

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
