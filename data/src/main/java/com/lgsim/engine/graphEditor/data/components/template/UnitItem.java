package com.lgsim.engine.graphEditor.data.components.template;

import com.lgsim.engine.graphEditor.api.unit.IUnit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 单位列表
 */
public class UnitItem implements IUnit {

    private String symbol;//参数名称
    private double factor;//系数
    private int offset;//偏移量

    public UnitItem() {
        this.symbol = "";
        this.factor = 0.0;
        this.offset = 0;
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

    @Override
    public @NotNull String getName() {
        return symbol;
    }

    @Override
    public double getScale() {
        return factor;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public boolean isBaseUnit() {
        return true;
    }

    @Override
    public @NotNull Collection<IUnit> getUnitFamily() {
        return new ArrayList<IUnit>();
    }

    @Override
    public String toString() {
        return symbol;
    }
}
