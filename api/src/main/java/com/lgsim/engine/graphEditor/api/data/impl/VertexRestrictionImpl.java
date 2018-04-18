package com.lgsim.engine.graphEditor.api.data.impl;

import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;

import java.util.List;

public class VertexRestrictionImpl implements IVertexRestriction
{
  private int minInputPortCount;
  private int maxInputPortCount;
  private int minOutputPortCount;
  private int maxOutputPortCount;
  private List<String> inputPortTypes;
  private List<String> outputPortTypes;


  public VertexRestrictionImpl()
  {
  }


  public VertexRestrictionImpl(int minInputPortCount, int maxInputPortCount,
                               int minOutputPortCount, int maxOutputPortCount,
                               List<String> inputPortTypes, List<String> outputPortTypes)
  {
    this.minInputPortCount = minInputPortCount;
    this.maxInputPortCount = maxInputPortCount;
    this.minOutputPortCount = minOutputPortCount;
    this.maxOutputPortCount = maxOutputPortCount;
    this.inputPortTypes = inputPortTypes;
    this.outputPortTypes = outputPortTypes;
  }


  @Override
  public int getMinInputPortCount()
  {
    return minInputPortCount;
  }


  public void setMinInputPortCount(int minInputPortCount)
  {
    this.minInputPortCount = minInputPortCount;
  }


  @Override
  public int getMaxInputPortCount()
  {
    return maxInputPortCount;
  }


  public void setMaxInputPortCount(int maxInputPortCount)
  {
    this.maxInputPortCount = maxInputPortCount;
  }


  @Override
  public int getMinOutputPortCount()
  {
    return minOutputPortCount;
  }


  public void setMinOutputPortCount(int minOutputPortCount)
  {
    this.minOutputPortCount = minOutputPortCount;
  }


  @Override
  public int getMaxOutputPortCount()
  {
    return maxOutputPortCount;
  }


  public void setMaxOutputPortCount(int maxOutputPortCount)
  {
    this.maxOutputPortCount = maxOutputPortCount;
  }


  @Override
  public List<String> getInputPortTypes()
  {
    return inputPortTypes;
  }


  public void setInputPortTypes(List<String> inputPortTypes)
  {
    this.inputPortTypes = inputPortTypes;
  }


  @Override
  public List<String> getOutputPortTypes()
  {
    return outputPortTypes;
  }


  public void setOutputPortTypes(List<String> outputPortTypes)
  {
    this.outputPortTypes = outputPortTypes;
  }
}
