package com.lgsim.engine.graphEditor.widget.PoJo;

/**
 * 基础单位类
 */
public class Unit {

    private String description;
    private double multiplier;
    private int index;

    public Unit(String description,double multiplier){
        this.description = description;
        this.multiplier = multiplier;
    }

    public Unit(){}

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString(){
        return this.getDescription();
    }
}
