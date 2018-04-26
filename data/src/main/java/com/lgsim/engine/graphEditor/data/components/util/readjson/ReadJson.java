package com.lgsim.engine.graphEditor.data.components.util.readjson;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
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
        String jsonstr = null;
        try {
            jsonstr = IOUtils.toString(is);
        }catch (IOException e) {
            e.getStackTrace();
        }
        //String jstr = jsonstr.toLowerCase();

        JSONObject jsonObject = JSONObject.fromObject(jsonstr);

        if(jsonObject.containsKey("Components")) {

            JSONObject com = JSONObject.fromObject(jsonObject.get("Components"));
            JSONArray coms = JSONArray.fromObject(com.get("Component"));
            List<Component> list = new ArrayList<>();
            //List<Component> list = (List<Component>)JSONArray.toCollection(coms,Component.class);

            for (Object objectCom : coms) {


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
                    component.setArmnodes(arm);
                }
                if(jObjectCom.containsKey("Feature")) {
                    JSONArray feature = JSONArray.fromObject(jObjectCom.get("Feature"));

                    for(Object objectFea : feature) {
                        JSONObject jObjectFea = (JSONObject)objectFea;
                        if(jObjectFea.containsKey("Name")) {
                            Parameter parameter = new Parameter();
                            component.setFeatureName(jObjectFea.getString("Name"));
                        }
                        if(jObjectFea.containsKey("Value")) {
                            JSONArray value = JSONArray.fromObject(jObjectFea.get("Value"));
                            List<IVertexArgument> arguments = new ArrayList<>();

                            for(int i = 0;i < value.size();i++) {
                                Parameter parameter = new Parameter();
                                parameter.setParameterValue(value.getDouble(i));
                                arguments.add(parameter);
                            }
                            component.setArguments(arguments);
                        }
                    }

                }

                list.add(component);
            }

            //list = (List<Component>) JSONArray.toCollection(component,Component.class);
            System.out.println(list);
        }

    }
}
