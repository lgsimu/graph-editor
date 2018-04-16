package com.lgsim.engine.graphEditor.api.data;

import java.util.List;

public interface IVertexRestriction
{
  int getMinInputPortCount();


  int getMaxInputPortCount();


  int getMinOutputPortCount();


  int getMaxOutputPortCount();


  List<String> getInputPortTypes();


  List<String> getOutputPortTypes();
}
