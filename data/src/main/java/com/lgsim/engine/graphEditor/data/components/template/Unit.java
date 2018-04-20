package com.lgsim.engine.graphEditor.data.components.template;

import java.util.ArrayList;
import java.util.List;

/**
 * 单位
 */
public class Unit {

    private String unitName;//单位名称
    private String unitID;//单位ID
    private List<UnitItem> unitItems;//包含的所有项

    public Unit() {
        this.unitName = "";
        this.unitID = "";
        this.unitItems = new ArrayList<UnitItem>();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public List<UnitItem> getUnitItems() {
        return unitItems;
    }

    public void setUnitItems(List<UnitItem> unitItems) {
        this.unitItems = unitItems;
    }
}
