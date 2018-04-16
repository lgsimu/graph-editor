package com.lgsim.engine.graphEditor.graph.component;

import java.io.Serializable;
import java.math.BigDecimal;

public class InstanceComponentArgument implements Serializable
{
  private static final long serialVersionUID = 2575041556457884803L;
  private String id;
  private String type;
  private String name;
  private String description;
  private BigDecimal numericalValue;


  public InstanceComponentArgument(String id, String type, String name, String description, BigDecimal numericalValue)
  {
    this.id = id;
    this.type = type;
    this.name = name;
    this.description = description;
    this.numericalValue = numericalValue;
  }


  public String getId()
  {
    return id;
  }


  public void setId(String id)
  {
    this.id = id;
  }


  public String getType()
  {
    return type;
  }


  public void setType(String type)
  {
    this.type = type;
  }


  public String getName()
  {
    return name;
  }


  public void setName(String name)
  {
    this.name = name;
  }


  public String getDescription()
  {
    return description;
  }


  public void setDescription(String description)
  {
    this.description = description;
  }


  public BigDecimal getNumericalValue()
  {
    return numericalValue;
  }


  public void setNumericalValue(BigDecimal numericalValue)
  {
    this.numericalValue = numericalValue;
  }
}
