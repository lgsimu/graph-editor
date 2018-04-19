package com.lgsim.engine.graphEditor.api.data.impl;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VertexStencilImpl implements IVertexStencil
{
  private boolean predefined;
  @NotNull
  private String ID;
  @NotNull
  private String name;
  @NotNull
  private String stencilIcon;
  @NotNull
  private String graphIcon;
  @NotNull
  private List<IVertexArgument> arguments;
  @NotNull
  private List<IVertexOutput> outputs;
  @NotNull
  private IVertexRestriction restriction;


  public VertexStencilImpl(boolean predefined, @NotNull String ID, @NotNull String name, @NotNull String stencilIcon, @NotNull String graphIcon, @NotNull List<IVertexArgument> arguments, @NotNull List<IVertexOutput> outputs, @NotNull IVertexRestriction restriction)
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
  public String getName()
  {
    return name;
  }


  public void setName(@NotNull String name)
  {
    this.name = name;
  }


  @Override
  @NotNull
  public String getStencilIcon()
  {
    return stencilIcon;
  }


  public void setStencilIcon(@NotNull String stencilIcon)
  {
    this.stencilIcon = stencilIcon;
  }


  @Override
  @NotNull
  public String getGraphIcon()
  {
    return graphIcon;
  }


  public void setGraphIcon(@NotNull String graphIcon)
  {
    this.graphIcon = graphIcon;
  }


  @Override
  @NotNull
  public List<IVertexArgument> getArguments()
  {
    return arguments;
  }


  public void setArguments(@NotNull List<IVertexArgument> arguments)
  {
    this.arguments = arguments;
  }


  @Override
  @NotNull
  public List<IVertexOutput> getOutputs()
  {
    return outputs;
  }


  public void setOutputs(@NotNull List<IVertexOutput> outputs)
  {
    this.outputs = outputs;
  }


  @Override
  @NotNull
  public IVertexRestriction getRestriction()
  {
    return restriction;
  }


  public void setRestriction(@NotNull IVertexRestriction restriction)
  {
    this.restriction = restriction;
  }
}
