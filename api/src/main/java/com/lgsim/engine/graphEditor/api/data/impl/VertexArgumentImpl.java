package com.lgsim.engine.graphEditor.api.data.impl;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import org.jetbrains.annotations.NotNull;

public class VertexArgumentImpl implements IVertexArgument
{
  @NotNull
  private String ID;
  @NotNull
  private String unit;
  private double minValue;
  private double maxValue;
  private double value;
  @NotNull
  private String description;


  public VertexArgumentImpl(@NotNull String ID, @NotNull String unit, double minValue, double maxValue, double value, @NotNull String description)
  {
    this.ID = ID;
    this.unit = unit;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.value = value;
    this.description = description;
  }


  @Override
  @NotNull
  public String getID()
  {
    return ID;
  }


  public void setID(@NotNull String ID)
  {
    this.ID = ID;
  }


  @Override
  @NotNull
  public String getUnit()
  {
    return unit;
  }


  public void setUnit(@NotNull String unit)
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
  @NotNull
  public String getDescription()
  {
    return description;
  }


  public void setDescription(@NotNull String description)
  {
    this.description = description;
  }
}
