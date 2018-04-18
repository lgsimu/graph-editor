package com.lgsim.engine.graphEditor.widget.PoJo;

import java.math.BigDecimal;

public class Unit {

    private String unitName;//单位名称
    private double unitRate;//单位比率

    public Unit(String unitName,double unitRate){
        this.unitName = unitName;
        this.unitRate = unitRate;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(double unitRate) {
        this.unitRate = unitRate;
    }

    @Override
    public String toString(){
        return this.getUnitName();
    }
}
