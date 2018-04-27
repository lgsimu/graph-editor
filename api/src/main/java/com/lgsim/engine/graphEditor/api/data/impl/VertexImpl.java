package com.lgsim.engine.graphEditor.api.data.impl;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.util.CollectionUtil;
import com.lgsim.engine.graphEditor.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VertexImpl implements IVertex {

  private String ID;
  private String typeID;
  private List<IVertexArgument> arguments;
  private List<IVertexOutput> outputs;
  private List<IVertex> inputPorts;
  private List<IVertex> outputPorts;
  private boolean cavity;
  private boolean global;
  private String displayName;


  public VertexImpl()
  {
    ID = StringUtil.emptyString();
    typeID = StringUtil.emptyString();
    arguments = CollectionUtil.emptyList();
    outputs = CollectionUtil.emptyList();
    inputPorts = CollectionUtil.emptyList();
    outputPorts = CollectionUtil.emptyList();
  }


  public VertexImpl(@NotNull String ID, @NotNull String typeID, @NotNull List<IVertexArgument> arguments, @NotNull List<IVertexOutput> outputs, @NotNull List<IVertex> inputPorts, @NotNull List<IVertex> outputPorts, boolean cavity, @NotNull String displayName)
  {
    this.ID = ID;
    this.typeID = typeID;
    this.arguments = arguments;
    this.outputs = outputs;
    this.inputPorts = inputPorts;
    this.outputPorts = outputPorts;
    this.cavity = cavity;
    this.displayName = displayName;
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


  @Override
  public boolean isGlobal() {
    return global;
  }


  public void setGlobal(boolean global) {
    this.global = global;
  }


  @NotNull
  @Override
  public String getDisplayName() {
    if (displayName != null) {
      return displayName;
    }
    else {
      if (ID != null) {
        return ID;
      }
      else {
        return StringUtil.emptyString();
      }
    }
  }


  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }


  @Override
  public String toString()
  {
    return getDisplayName();
  }
}
