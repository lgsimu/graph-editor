package com.lgsim.engine.graphEditor.api.data.impl;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VertexImpl implements IVertex
{
  @NotNull
  private String ID;

  @NotNull
  private String typeID;

  @NotNull
  private List<IVertexArgument> arguments;

  @NotNull
  private List<IVertexOutput> outputs;

  @NotNull
  private List<IVertex> inputPorts;

  @NotNull
  private List<IVertex> outputPorts;

  private boolean cavity;


  public VertexImpl(@NotNull String ID, @NotNull String typeID, @NotNull List<IVertexArgument> arguments, @NotNull List<IVertexOutput> outputs, @NotNull List<IVertex> inputPorts, @NotNull List<IVertex> outputPorts, boolean cavity)
  {
    this.ID = ID;
    this.typeID = typeID;
    this.arguments = arguments;
    this.outputs = outputs;
    this.inputPorts = inputPorts;
    this.outputPorts = outputPorts;
    this.cavity = cavity;
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
  public String getTypeID()
  {
    return typeID;
  }


  public void setTypeID(@NotNull String typeID)
  {
    this.typeID = typeID;
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
  public List<IVertex> getInputPorts()
  {
    return inputPorts;
  }


  public void setInputPorts(@NotNull List<IVertex> inputPorts)
  {
    this.inputPorts = inputPorts;
  }


  @Override
  @NotNull
  public List<IVertex> getOutputPorts()
  {
    return outputPorts;
  }


  public void setOutputPorts(@NotNull List<IVertex> outputPorts)
  {
    this.outputPorts = outputPorts;
  }


  @Override
  public boolean isCavity()
  {
    return cavity;
  }


  public void setCavity(boolean cavity)
  {
    this.cavity = cavity;
  }
}
