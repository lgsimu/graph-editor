package com.lgsim.engine.graphEditor.data.components.util.readjson;

import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.data.components.template.Component;
import com.lgsim.engine.graphEditor.data.components.template.Parameter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadJson {
    /**
     * 读取json文件
     * @param path json文件路径
     */
    public void readJson(String path) {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        String jstr = null;
        try {
            jstr = IOUtils.toString(is);
        }catch (IOException e) {
            e.getStackTrace();
        }

        List<Component> list = new ArrayList<>();
        JSONObject jsonObject = JSONObject.fromObject(jstr);

        if(jsonObject.containsKey("Components")) {

            JSONArray coms = JSONArray.fromObject(jsonObject.get("Components"));

            for (Object object : coms) {

                JSONObject jObject = (JSONObject)object;
                JSONArray com = JSONArray.fromObject(jObject.get("Component"));

                for (Object objectCom : com) {
                    Component component = new Component();

                    JSONObject jObjectCom = (JSONObject)objectCom;

                    if(jObjectCom.containsKey("Name")) {
                        component.setComponentName(jObjectCom.getString("Name"));
                    }
                    if(jObjectCom.containsKey("Type")) {
                        component.setComponentType(jObjectCom.getString("Type"));
                    }
                    if(jObjectCom.containsKey("ArmNodes")) {
                        JSONArray arm = JSONArray.fromObject(jObjectCom.get("ArmNodes"));
                        //component.setArmNodes();
                    }



                    list.add(component);
                }
            }

            //list = (List<Component>) JSONArray.toCollection(component,Component.class);
            System.out.println(list);
        }

    }
}
