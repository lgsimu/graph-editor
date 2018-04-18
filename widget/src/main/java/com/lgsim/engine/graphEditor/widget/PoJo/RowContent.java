package com.lgsim.engine.graphEditor.widget.PoJo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 行内容
 */
public class RowContent {

    private String id;
    private String property;
    private double value;
    private String unit;
    private String description;
    private BigDecimal max;
    private BigDecimal min;

    public BigDecimal getMax() {
        return new BigDecimal(100000);
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return new BigDecimal(100);
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public RowContent(String id, String property, double value, String unit, String description){
        this.id = id;
        this.property = property;
        this.value = value;
        this.unit = unit;
        this.description = description;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
