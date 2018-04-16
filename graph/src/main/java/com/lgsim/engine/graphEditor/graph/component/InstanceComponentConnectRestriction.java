package com.lgsim.engine.graphEditor.graph.component;

import java.io.Serializable;
import java.util.List;

public class InstanceComponentConnectRestriction implements Serializable
{
  private static final long serialVersionUID = 7823214823735752607L;
  private int minInputPortCount;
  private int maxInputPortCount;
  private int minOutputPortCount;
  private int maxOutputPortCount;
  private List<String> inputTypeIds;
  private List<String> outputTypeIds;


  public InstanceComponentConnectRestriction(int minInputPortCount, int maxInputPortCount, int minOutputPortCount, int maxOutputPortCount, List<String> inputTypeIds, List<String> outputTypeIds)
  {
    this.minInputPortCount = minInputPortCount;
    this.maxInputPortCount = maxInputPortCount;
    this.minOutputPortCount = minOutputPortCount;
    this.maxOutputPortCount = maxOutputPortCount;
    this.inputTypeIds = inputTypeIds;
    this.outputTypeIds = outputTypeIds;
  }


  public int getMinInputPortCount()
  {
    return minInputPortCount;
  }


  public void setMinInputPortCount(int minInputPortCount)
  {
    this.minInputPortCount = minInputPortCount;
  }


  public int getMaxInputPortCount()
  {
    return maxInputPortCount;
  }


  public void setMaxInputPortCount(int maxInputPortCount)
  {
    this.maxInputPortCount = maxInputPortCount;
  }


  public int getMinOutputPortCount()
  {
    return minOutputPortCount;
  }


  public void setMinOutputPortCount(int minOutputPortCount)
  {
    this.minOutputPortCount = minOutputPortCount;
  }


  public int getMaxOutputPortCount()
  {
    return maxOutputPortCount;
  }


  public void setMaxOutputPortCount(int maxOutputPortCount)
  {
    this.maxOutputPortCount = maxOutputPortCount;
  }


  public List<String> getInputTypeIds()
  {
    return inputTypeIds;
  }


  public void setInputTypeIds(List<String> inputTypeIds)
  {
    this.inputTypeIds = inputTypeIds;
  }


  public List<String> getOutputTypeIds()
  {
    return outputTypeIds;
  }


  public void setOutputTypeIds(List<String> outputTypeIds)
  {
    this.outputTypeIds = outputTypeIds;
  }
}
