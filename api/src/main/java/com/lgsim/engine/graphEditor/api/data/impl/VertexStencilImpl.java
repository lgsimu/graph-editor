package com.lgsim.engine.graphEditor.api.data.impl;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;

import java.util.List;

public class VertexStencilImpl implements IVertexStencil
{
  private boolean predefined;
  private String ID;
  private String name;
  private String stencilIcon;
  private String graphIcon;
  private List<IVertexArgument> arguments;
  private List<IVertexOutput> outputs;
  private IVertexRestriction restriction;


  public VertexStencilImpl()
  {
  }


  public VertexStencilImpl(boolean predefined, String ID, String name, String stencilIcon, String graphIcon,
                           List<IVertexArgument> arguments, List<IVertexOutput> outputs, IVertexRestriction restriction)
  {
    this.predefined = predefined;
    this.ID = ID;
    this.name = name;
    this.stencilIcon = stencilIcon;
    this.graphIcon = graphIcon;
    this.arguments = arguments;
    this.outputs = outputs;
    this.restriction = restriction;
  }


  @Override
  public boolean isPredefined()
  {
    return predefined;
  }


  public void setPredefined(boolean predefined)
  {
    this.predefined = predefined;
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
  public String getName()
  {
    return name;
  }


  public void setName(String name)
  {
    this.name = name;
  }


  @Override
  public String getStencilIcon()
  {
    return stencilIcon;
  }


  public void setStencilIcon(String stencilIcon)
  {
    this.stencilIcon = stencilIcon;
  }


  @Override
  public String getGraphIcon()
  {
    return graphIcon;
  }


  public void setGraphIcon(String graphIcon)
  {
    this.graphIcon = graphIcon;
  }


  @Override
  public List<IVertexArgument> getArguments()
  {
    return arguments;
  }


  public void setArguments(List<IVertexArgument> arguments)
  {
    this.arguments = arguments;
  }


  @Override
  public List<IVertexOutput> getOutputs()
  {
    return outputs;
  }


  public void setOutputs(List<IVertexOutput> outputs)
  {
    this.outputs = outputs;
  }


  @Override
  public IVertexRestriction getRestriction()
  {
    return restriction;
  }


  public void setRestriction(IVertexRestriction restriction)
  {
    this.restriction = restriction;
  }
}
