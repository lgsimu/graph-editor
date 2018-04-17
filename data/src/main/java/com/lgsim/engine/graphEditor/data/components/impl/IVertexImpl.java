package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * IVertexImpl实现类
 */
public class IVertexImpl implements IVertex {

    private String ID;//节点id
    private String typeID;//节点id类型
    private ArrayList<IVertexArgument> arguments = new ArrayList<IVertexArgument>();//输入参数
    private ArrayList<IVertexOutput> outputs = new ArrayList<IVertexOutput>();//计算后的输出
    private List<IVertex> inputPorts = new ArrayList<IVertex>();//输入到该节点的节点
    private List<IVertex> outputPorts = new ArrayList<IVertex>();//该接口输出的节点
    private boolean isCavity;//是否是腔节点

    public IVertexImpl() {
    }

    public IVertexImpl(String iD, String typeID, ArrayList<IVertexArgument> arguments, ArrayList<IVertexOutput> outputs, List<IVertex> inputPorts, List<IVertex> onputPorts, boolean isCavity) {
        this.ID = iD;
        this.typeID = typeID;
        this.arguments = arguments;
        this.outputs = outputs;
        this.inputPorts = inputPorts;
        this.outputPorts = onputPorts;
        this.isCavity = isCavity;
    }

    public String getID() {
        return ID;
    }

    public void setiD(String ID) {
        this.ID = ID;
    }


    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public ArrayList<IVertexArgument> getArguments() {
        return arguments;
    }

    public void setArguments(ArrayList<IVertexArgument> arguments) {
        this.arguments = arguments;
    }

    public ArrayList<IVertexOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(ArrayList<IVertexOutput> outputs) {
        this.outputs = outputs;
    }

    public List<IVertex> getInputPorts() {
        return inputPorts;
    }

    public void setInputPorts(List<IVertex> inputPorts) {
        this.inputPorts = inputPorts;
    }

    public List<IVertex> getOutputPorts() {
        return outputPorts;
    }

    public void setOutputPorts(List<IVertex> outputPorts) {
        this.outputPorts = outputPorts;
    }

    public boolean isCavity() {
        return isCavity;
    }

    public void setCavity(boolean cavity) {
        isCavity = cavity;
    }
}
