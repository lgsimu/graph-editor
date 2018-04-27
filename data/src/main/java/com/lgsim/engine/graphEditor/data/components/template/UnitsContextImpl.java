package com.lgsim.engine.graphEditor.data.components.template;

import com.lgsim.engine.graphEditor.api.unit.IUnit;
import com.lgsim.engine.graphEditor.api.unit.IUnitBundle;
import com.lgsim.engine.graphEditor.api.unit.IUnitsContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class UnitsContextImpl implements IUnitsContext {
    @Override
    public @NotNull Collection<IUnitBundle> getSupportUnitBundles() {

        String path = "com/lgsim/engine/graphEditor/data/units/units.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        String jsonstr = null;
        try {
            jsonstr = IOUtils.toString(is);
        }catch (IOException e) {
            e.getStackTrace();
        }

        Collection<IUnitBundle> unitList = new ArrayList<>();
        JSONObject jsonObject = JSONObject.fromObject(jsonstr);
        JSONArray units = JSONArray.fromObject(jsonObject.get("units"));

        for (Object objectUnit : units) {
            Unit unit = new Unit();

            JSONObject jObjectUnit = (JSONObject)objectUnit;
            JSONArray unitItems = JSONArray.fromObject(jObjectUnit.get("unitItems"));
            Collection<IUnit> unitItemsList = new ArrayList<>();

            for (Object objectItem : unitItems) {
                UnitItem unitItem = new UnitItem();
                JSONObject jObjectItem = (JSONObject)objectItem;
                String factor = jObjectItem.getString("factor");
                double a = Double.parseDouble(factor);
                String name = jObjectItem.getString("name");
                int offset = jObjectItem.getInt("offset");

                unitItem.setSymbol(name);
                unitItem.setFactor(a);
                unitItem.setOffset(offset);

                unitItemsList.add(unitItem);
            }

            String unitId = jObjectUnit.getString("unitId");
            String unitName = jObjectUnit.getString("unitName");
            unit.setUnitID(unitId);
            unit.setUnitName(unitName);
            unit.setUnitItems(unitItemsList);

            unitList.add(unit);
        }

        return unitList;
    }
}
