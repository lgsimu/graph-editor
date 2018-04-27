package com.lgsim.engine.graphEditor.data.components.template;

import com.lgsim.engine.graphEditor.api.unit.IUnit;
import com.lgsim.engine.graphEditor.api.unit.IUnitBundle;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 单位
 */
public class Unit implements IUnitBundle {

    private String unitName;//单位名称
    private String unitID;//单位ID
    private Collection<IUnit> unitItems;//包含的所有项

    public Unit() {
        this.unitName = "";
        this.unitID = "";
        this.unitItems = new ArrayList<IUnit>();
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

    public Collection<IUnit> getUnitItems() {
        return unitItems;
    }

    public void setUnitItems(Collection<IUnit> unitItems) {
        this.unitItems = unitItems;
    }

    @Override
    public @NotNull String getID() {
        return unitID;
    }

    @Override
    public @NotNull String getName() {
        return unitName;
    }

    @Override
    public @NotNull Collection<IUnit> getUnitFamily() {
        return unitItems;
    }


}
