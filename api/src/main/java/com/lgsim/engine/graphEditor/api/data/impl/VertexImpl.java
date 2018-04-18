package com.lgsim.engine.graphEditor.api.data.impl;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;

import java.util.List;

public class VertexImpl implements IVertex
{
  private String ID;
  private String typeID;
  private List<IVertexArgument> arguments;
  private List<IVertexOutput> outputs;
  private List<IVertex> inputPorts;
  private List<IVertex> outputPorts;
  private boolean cavity;


  public VertexImpl()
  {
  }


  public VertexImpl(String ID, String typeID, List<IVertexArgument> arguments, List<IVertexOutput> outputs,
                    List<IVertex> inputPorts, List<IVertex> outputPorts, boolean cavity)
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
  public String getID()
  {
    return ID;
  }


  public void setID(String ID)
  {
    this.ID = ID;
  }


  @Override
  public String getTypeID()
  {
    return typeID;
  }


  public void setTypeID(String typeID)
  {
    this.typeID = typeID;
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
  public List<IVertex> getInputPorts()
  {
    return inputPorts;
  }


  public void setInputPorts(List<IVertex> inputPorts)
  {
    this.inputPorts = inputPorts;
  }


  @Override
  public List<IVertex> getOutputPorts()
  {
    return outputPorts;
  }


  public void setOutputPorts(List<IVertex> outputPorts)
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
