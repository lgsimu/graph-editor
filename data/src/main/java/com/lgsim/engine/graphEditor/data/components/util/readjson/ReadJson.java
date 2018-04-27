package com.lgsim.engine.graphEditor.data.components.util.readjson;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.data.components.template.Component;
import com.lgsim.engine.graphEditor.data.components.template.Parameter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class ReadJson {
    /**
     * 读取json文件
     * @param path json文件路径
     */
    public Collection<IVertex> readJson(String path) {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        String jsonstr = null;
        try {
            jsonstr = IOUtils.toString(is);
        }catch (IOException e) {
            e.getStackTrace();
        }
        //String jstr = jsonstr.toLowerCase();

        JSONObject jsonObject = JSONObject.fromObject(jsonstr);
        JSONObject components = JSONObject.fromObject(jsonObject.get("Components"));
        JSONArray coms = JSONArray.fromObject(components.get("Component"));

        Collection<IVertex> list = new ArrayList<>();

        for (int i = 0;i < coms.size();i++) {
            Component component = new Component();

            JSONObject com = JSONObject.fromObject(coms.getJSONObject(i));
            JSONArray results = JSONArray.fromObject(com.get("Result"));
            for (int j = 0;j < results.size();j++) {
                JSONObject res = JSONObject.fromObject(results.getJSONObject(j));

                ArrayList<IVertexOutput> parameters = new ArrayList<>();
                Parameter parameter = new Parameter();

                if(res.containsKey("AA1")) {
                    parameter.setParameterValue(res.getDouble("AA1"));
                    parameters.add(parameter);
                }
                if(res.containsKey("AA2")) {
                    parameter.setParameterValue(res.getDouble("AA2"));
                    parameters.add(parameter);
                }
                if(res.containsKey("M1")) {
                    parameter.setParameterValue(res.getDouble("M1"));
                    parameters.add(parameter);
                }
                if(res.containsKey("M2")) {
                    parameter.setParameterValue(res.getDouble("M2"));
                    parameters.add(parameter);
                }
                if(res.containsKey("TP1")) {
                    parameter.setParameterValue(res.getDouble("TP1"));
                    parameters.add(parameter);
                }
                if(res.containsKey("TP2")) {
                    parameter.setParameterValue(res.getDouble("TP2"));
                    parameters.add(parameter);
                }
                if(res.containsKey("TT1")) {
                    parameter.setParameterValue(res.getDouble("TT1"));
                    parameters.add(parameter);
                }
                if(res.containsKey("TT2")) {
                    parameter.setParameterValue(res.getDouble("TT2"));
                    parameters.add(parameter);
                }
                if(res.containsKey("SP1")) {
                    parameter.setParameterValue(res.getDouble("SP1"));
                    parameters.add(parameter);
                }
                if(res.containsKey("SP2")) {
                    parameter.setParameterValue(res.getDouble("SP2"));
                    parameters.add(parameter);
                }
                if(res.containsKey("ST1")) {
                    parameter.setParameterValue(res.getDouble("ST1"));
                    parameters.add(parameter);
                }
                if(res.containsKey("ST2")) {
                    parameter.setParameterValue(res.getDouble("ST2"));
                    parameters.add(parameter);
                }
                if(res.containsKey("MA1")) {
                    parameter.setParameterValue(res.getDouble("MA1"));
                    parameters.add(parameter);
                }
                if(res.containsKey("MA2")) {
                    parameter.setParameterValue(res.getDouble("MA2"));
                    parameters.add(parameter);
                }
                if(res.containsKey("V1")) {
                    parameter.setParameterValue(res.getDouble("V1"));
                    parameters.add(parameter);
                }
                if(res.containsKey("V2")) {
                    parameter.setParameterValue(res.getDouble("V2"));
                    parameters.add(parameter);
                }
                if(res.containsKey("DEN1")) {
                    parameter.setParameterValue(res.getDouble("DEN1"));
                    parameters.add(parameter);
                }
                if(res.containsKey("DEN2")) {
                    parameter.setParameterValue(res.getDouble("DEN2"));
                    parameters.add(parameter);
                }

                component.setOutputs(parameters);
            }

            component.setComponentName(com.getString("Name"));
            component.setComponentType(com.getString("Type"));

            list.add(component);
        }

        return list;
/*        if(jsonObject.containsKey("Components")) {

            JSONObject com = JSONObject.fromObject(jsonObject.get("Components"));
            JSONArray coms = JSONArray.fromObject(com.get("Component"));

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
                    component.setFeature(feature);
                }

                list.add(component);
            }

            //list = (List<Component>) JSONArray.toCollection(component,Component.class);
            //System.out.println(list);
        }*/
    }
}
