package com.lgsim.engine.graphEditor.data.components.template;


import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数类
 */
public class Parameter implements IVertexArgument,IVertexOutput {

    private String parameterName;//参数名称
    private ParameterIOType parameterIOType;//参数是输入或者输出类型
    private ParameterValueType parameterValueType;//参数值类型是整型或者浮点型
    //private String parameterValue;//参数值，后期需要支持表达式
    private double parameterValue;//参数值，后期需要支持表达式
    private String parameterUnitID;//参数单位ID，从单位中获取
    private String parameterDescription;//参数备注
    private List<Double> values = new ArrayList<Double>();//参数值集合

    public Parameter() {
        this.parameterName = "";
        this.parameterIOType = ParameterIOType.InputParameter;
        this.parameterValueType = ParameterValueType.Double;
        this.parameterValue = 0.0;
        this.parameterUnitID = "";
        this.parameterDescription = "";
        this.values.add(parameterValue);
    }

    public Parameter(String parameterName, String parameterUnitID, double parameterValue, String parameterDescription) {
        this.parameterName = parameterName;
        this.parameterUnitID = parameterUnitID;
        this.parameterValue = parameterValue;
        this.parameterDescription = parameterDescription;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public ParameterIOType getParameterIOType() {
        return parameterIOType;
    }

    public void setParameterIOType(ParameterIOType parameterIOType) {
        this.parameterIOType = parameterIOType;
    }

    public ParameterValueType getParameterValueType() {
        return parameterValueType;
    }

    public void setParameterValueType(ParameterValueType parameterValueType) {
        this.parameterValueType = parameterValueType;
    }

    public double getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(double parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterUnitID() {
        return parameterUnitID;
    }

    public void setParameterUnitID(String parameterUnitID) {
        this.parameterUnitID = parameterUnitID;
    }

    public String getParameterDescription() {
        return parameterDescription;
    }

    public void setParameterDescription(String parameterDescription) {
        this.parameterDescription = parameterDescription;
    }

    @Override
    public @NotNull String getID() {
        return parameterName;
    }

    @Override
    public @NotNull String getUnit() {
        return parameterUnitID;
    }

    @Override
    public double getMinValue() {
        return 0;
    }

    @Override
    public double getMaxValue() {
        return 0;
    }

    @Override
    public double getValue() {
        return parameterValue;
    }

    @Override
    public void setValue(double value) {
        this.parameterValue = value;
    }

    @Override
    public @NotNull String getDescription() {
        return parameterDescription;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "parameterName='" + parameterName + '\'' +
                ", parameterIOType=" + parameterIOType +
                ", parameterValueType=" + parameterValueType +
                ", parameterValue=" + parameterValue +
                ", parameterUnitID='" + parameterUnitID + '\'' +
                ", parameterDescription='" + parameterDescription + '\'' +
                ", values=" + values +
                '}';
    }
}
