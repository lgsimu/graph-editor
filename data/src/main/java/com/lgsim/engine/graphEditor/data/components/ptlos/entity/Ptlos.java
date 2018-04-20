package com.lgsim.engine.graphEditor.data.components.ptlos.entity;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.component.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/4/14.
 * 局部损失元件
 */
public class Ptlos extends Component implements IVertexStencil {

    private boolean isPredefined;//是否是预定义的元件
    private String type;//元件类型
    private String argumentType;//参数类型
    private String value;//值
    private String armNodes;//端口号
    private ArrayList<IVertexArgumentImpl> arguments = new ArrayList<IVertexArgumentImpl>();//元素参数
    private ArrayList<IVertexOutput> outputs = new ArrayList<IVertexOutput>();//计算后的参数

    public Ptlos() {
    }

    public Ptlos(String name, String type, String argumentType, String stencilIcon, String graphIcon, IVertexRestriction restriction, boolean isPredefined, ArrayList<IVertexArgumentImpl> arguments, ArrayList<IVertexOutput> outputs) {
        super(name, type, stencilIcon, graphIcon, restriction);
        this.argumentType = argumentType;
        this.isPredefined = isPredefined;
        this.arguments = arguments;
        this.outputs = outputs;
    }

    /**
     * 端口号
     */
    public String getArmNodes() {
        return armNodes;
    }

    public void setArmNodes(String armNodes) {
        this.armNodes = armNodes;
    }

    /**
     * 值
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 元件类型
     */
    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 参数类型
     */
    public String getArgumentType() {
        return argumentType;
    }

    public void setArgumentType(String argumentType) {
        this.argumentType = argumentType;
    }

    /**
     * 是否是预定义的元件
     */
    @Override
    public boolean isPredefined() {
        return isPredefined;
    }

    public boolean getIsPredefined() {
        return isPredefined;
    }

    public void setIsPredefined(boolean isPredefined) {
        this.isPredefined = isPredefined;
    }

    /**
     * id
     */
    @Override
    public @NotNull String getID() {
        return this.getType();
    }

    /**
     * 输入参数
     */
    @Override
    public @NotNull List<IVertexArgument> getArguments() {
        IVertexArgumentImpl argument = new IVertexArgumentImpl("","",0,0,0,"");
        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(argument);
        return arguments;
    }

    public void setArguments(ArrayList<IVertexArgumentImpl> arguments) {
        this.arguments = arguments;
    }

    /**
     * 计算后的输出
     */
    public @NotNull List<IVertexOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(ArrayList<IVertexOutput> outputs) {
        this.outputs = outputs;
    }

    @Override
    public String toString() {
        return getName();
    }
/*@Override
    public String toString() {
        return "Ptlos{" +
                "isPredefined=" + isPredefined +
                ", type='" + type + '\'' +
                ", argumentType='" + argumentType + '\'' +
                ", arguments=" + arguments +
                ", outputs=" + outputs +
                '}';
    }*/
    //@Override
    /*public String toString() {
        return "{" +
                "\"Name\":\"" + super.getName() + '\"' +
                ",\"Type\":\"" + type + '\"' +
                ",\"StencilIcon\":\"" + super.getStencilIcon() + '\"' +
                ",\"GraphIcon\":\"" + super.getGraphIcon() + '\"' +
                ",\"Restriction\":" + super.getRestriction() +
                ",\"IsPredefined\":\"" + isPredefined + '\"' +
                ",\"ArgumentType\":\"" + argumentType + '\"' +
                ",\"Arguments\":" + arguments +
                ",\"Outputs\":" + outputs +
                '}';
    }*/

    /*public String toString() {

        return  //"{\"Component\":" +
                "{" +
                        "\"Name\":\"" + super.getName() + '\"' +
                        ",\"Type\":\"" + type + '\"' +
                        ",\"ArmNodes\":" + armNodes +
                        ",\"Feature\":[{" + "\"Name\":\"" + argumentType + '\"' +
                        ",\"Value\":" + value + "}]" +
                        //'}' +
                        '}' ;
    }*/
    //@Override
    /*public String toString() {
        return "{" +
                "\"Name\":\"" + super.getName() + '\"' +
                ",\"Type\":\"" + super.getType() + '\"' +
                ",\"ArmNode1\":\"" + armNode1 + '\"' +
                ",\"ArmNode2\":\"" + armNode2 + '\"' +
                ",\"FeatureName\":\"" + featureName + '\"' +
                ",\"AA\":\"" + aa + '\"' +
                ",\"GEO1\":\"" + geo1 + '\"' +
                ",\"GEO2\":\"" + geo2 + '\"' +
                '}';
    }*/
}

