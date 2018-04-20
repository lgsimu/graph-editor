package com.lgsim.engine.graphEditor.data.components.template;

/**
 * 单位列表
 */
public class UnitItem {

    private String symbol;//参数名称
    private double factor;//系数
    private double offset;//偏移量

    public UnitItem() {
        this.symbol = "";
        this.factor = 0.0;
        this.offset = 0.0;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }
}
