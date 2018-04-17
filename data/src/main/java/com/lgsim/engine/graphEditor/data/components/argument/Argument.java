package com.lgsim.engine.graphEditor.data.components.argument;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;

import java.math.BigDecimal;

public class Argument implements IVertexArgument {
    /**
     * 参数id
     */
    @Override
    public String getID() {
        return null;
    }
    /**
     * 参数单位
     */
    @Override
    public String getUnit() {
        return null;
    }
    /**
     * 最小值
     */
    @Override
    public double getMinValue() {
        return 0;
    }
    /**
     * 最大值
     */
    @Override
    public double getMaxValue() {
        return 0;
    }
    /**
     * 获取值
     */
    @Override
    public double getValue() {
        return 0;
    }
    /**
     * 保存值
     */
    @Override
    public void setValue(double value) {

    }
    /**
     * 信息描述
     */
    @Override
    public String getDescription() {
        return null;
    }
}
