package com.lgsim.engine.graphEditor.api.data.impl;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;

public class VertexArgumentImpl implements IVertexArgument
{
  private String ID;
  private String unit;
  private double minValue;
  private double maxValue;
  private double value;
  private String description;


  public VertexArgumentImpl()
  {
  }


  public VertexArgumentImpl(String ID, String unit, double minValue, double maxValue, double value, String description)
  {
    this.ID = ID;
    this.unit = unit;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.value = value;
    this.description = description;
  }


  @Override
  public String getID()
  {
    return ID;
  }


  public void setID(String ID)
  {
    this.ID = ID;
  }


  @Override
  public String getUnit()
  {
    return unit;
  }


  public void setUnit(String unit)
  {
    this.unit = unit;
  }


  @Override
  public double getMinValue()
  {
    return minValue;
  }


  public void setMinValue(double minValue)
  {
    this.minValue = minValue;
  }


  @Override
  public double getMaxValue()
  {
    return maxValue;
  }


  public void setMaxValue(double maxValue)
  {
    this.maxValue = maxValue;
  }


  @Override
  public double getValue()
  {
    return value;
  }


  @Override
  public void setValue(double value)
  {
    this.value = value;
  }


  @Override
  public String getDescription()
  {
    return description;
  }


  public void setDescription(String description)
  {
    this.description = description;
  }
}
