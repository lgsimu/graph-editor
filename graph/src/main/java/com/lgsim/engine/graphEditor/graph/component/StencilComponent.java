package com.lgsim.engine.graphEditor.graph.component;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class StencilComponent extends EngineComponent implements Serializable
{
  private static final long serialVersionUID = -4144867156149641703L;
  private final OriginType originType;
  private String id;
  private String name;
  private String modelIcon;
  private String canvasIcon;
  private List<InstanceComponentArgument> arguments;
  private List<InstanceComponentOutput> outputs;
  private InstanceComponentConnectRestriction restriction;


  public StencilComponent(@NotNull OriginType originType)
  {
    this.originType = originType;
    this.arguments = new Vector<>();
    this.outputs = new Vector<>();
  }


  StencilComponent(OriginType originType, String id, String name, String modelIcon, String canvasIcon,
                   List<InstanceComponentArgument> arguments, List<InstanceComponentOutput> outputs,
                   InstanceComponentConnectRestriction restriction)
  {
    this.originType = originType;
    this.id = id;
    this.name = name;
    this.modelIcon = modelIcon;
    this.canvasIcon = canvasIcon;
    this.arguments = arguments;
    this.outputs = outputs;
    this.restriction = restriction;
  }


  @Override
  public boolean isSelected()
  {
    return false;
  }


  public boolean isPredefined()
  {
    return originType == OriginType.PREDEFINED;
  }


  public String getId()
  {
    return id;
  }


  public void setId(String id)
  {
    this.id = id;
  }


  public String getName()
  {
    return name;
  }


  public void setName(String name)
  {
    this.name = name;
  }


  public String getModelIcon()
  {
    return modelIcon;
  }


  public void setModelIcon(String modelIcon)
  {
    this.modelIcon = modelIcon;
  }


  public String getCanvasIcon()
  {
    return canvasIcon;
  }


  public void setCanvasIcon(String canvasIcon)
  {
    this.canvasIcon = canvasIcon;
  }


  public List<InstanceComponentArgument> getArguments()
  {
    return arguments;
  }


  public void setArguments(List<InstanceComponentArgument> arguments)
  {
    this.arguments = arguments;
  }


  public void addArgument(@NotNull InstanceComponentArgument argument)
  {
    arguments.add(argument);
  }


  public List<InstanceComponentOutput> getOutputs()
  {
    return outputs;
  }


  public void setOutputs(List<InstanceComponentOutput> outputs)
  {
    this.outputs = outputs;
  }


  public void addOutput(@NotNull InstanceComponentOutput output)
  {
    outputs.add(output);
  }


  public InstanceComponentConnectRestriction getRestriction()
  {
    return restriction;
  }


  public void setRestriction(InstanceComponentConnectRestriction restriction)
  {
    this.restriction = restriction;
  }


  @Override
  public String toString()
  {
    return "";
  }


  @Override
  public boolean equals(Object obj)
  {
    return (obj instanceof StencilComponent) && (obj == this);
  }


  @Override
  public int hashCode()
  {
    return Objects.hash(originType, id, name, modelIcon, canvasIcon, arguments, outputs, restriction);
  }
}
