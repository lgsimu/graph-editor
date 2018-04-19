package com.lgsim.engine.graphEditor.api.data.impl;

import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VertexRestrictionImpl implements IVertexRestriction
{
  private int minInputPortCount;

  private int maxInputPortCount;

  private int minOutputPortCount;

  private int maxOutputPortCount;

  @NotNull
  private List<String> inputPortTypes;

  @NotNull
  private List<String> outputPortTypes;


  public VertexRestrictionImpl(int minInputPortCount, int maxInputPortCount, int minOutputPortCount, int maxOutputPortCount, @NotNull List<String> inputPortTypes, @NotNull List<String> outputPortTypes)
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
  @NotNull
  public List<String> getInputPortTypes()
  {
    return inputPortTypes;
  }


  public void setInputPortTypes(@NotNull List<String> inputPortTypes)
  {
    this.inputPortTypes = inputPortTypes;
  }


  @Override
  @NotNull
  public List<String> getOutputPortTypes()
  {
    return outputPortTypes;
  }


  public void setOutputPortTypes(@NotNull List<String> outputPortTypes)
  {
    this.outputPortTypes = outputPortTypes;
  }
}
