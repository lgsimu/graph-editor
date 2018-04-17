package com.lgsim.engine.graphEditor.data.components.entity;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;

/**
 * 参数类
 */
public class IVertexArgumentImpl implements IVertexArgument {

    private String id;//id
    private String unit;//单位
    private double minValue;//最小值
    private double maxValue;//最大值
    private double value;//值
    private String description;//描述信息

    public IVertexArgumentImpl() {
    }

    public IVertexArgumentImpl(String id, String unit, double minValue, double maxValue, double value, String description) {
        this.id = id;
        this.unit = unit;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
        this.description = description;
    }

    /**
     * 参数id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getID() {
        return id;
    }

    /**
     * 参数单位
     */
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 最小值
     */
    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    /**
     * 最大值
     */
    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 参数值
     */
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * 参数详情描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
