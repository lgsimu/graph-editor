package com.lgsim.engine.graphEditor.api.graph.impl;

import com.lgsim.engine.graphEditor.api.graph.IVertexStyle;

import java.awt.*;
import java.util.Map;

public class VertexStyleImpl implements IVertexStyle
{
  private Point position;
  private Dimension size;
  private Map<String, Object> otherStyles;


  public VertexStyleImpl(Point position, Dimension size, Map<String, Object> otherStyles)
  {
    this.position = position;
    this.size = size;
    this.otherStyles = otherStyles;
  }


  @Override
  public Point getPosition()
  {
    return position;
  }


  public void setPosition(Point position)
  {
    this.position = position;
  }


  @Override
  public Dimension getSize()
  {
    return size;
  }


  public void setSize(Dimension size)
  {
    this.size = size;
  }


  @Override
  public Map<String, Object> getOtherStyles()
  {
    return otherStyles;
  }


  public void setOtherStyles(Map<String, Object> otherStyles)
  {
    this.otherStyles = otherStyles;
  }
}
