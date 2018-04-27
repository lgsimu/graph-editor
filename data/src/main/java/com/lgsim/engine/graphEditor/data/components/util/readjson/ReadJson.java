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

                if(res.containsKey("AA1")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("AA1"));
                    parameter.setParameterName("AA1");
                    parameter.setParameterDescription("端口1面积");
                    parameters.add(parameter);
                }
                if(res.containsKey("AA2")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("AA2"));
                    parameter.setParameterName("AA2");
                    parameter.setParameterDescription("端口2面积");
                    parameters.add(parameter);
                }
                if(res.containsKey("M1")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("M1"));
                    parameter.setParameterName("M1");
                    parameter.setParameterDescription("端口1质量流量");
                    parameters.add(parameter);
                }
                if(res.containsKey("M2")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("M2"));
                    parameter.setParameterName("M2");
                    parameter.setParameterDescription("端口2质量流量");
                    parameters.add(parameter);
                }
                if(res.containsKey("TP1")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("TP1"));
                    parameter.setParameterName("TP1");
                    parameter.setParameterDescription("端口1总压");
                    parameters.add(parameter);
                }
                if(res.containsKey("TP2")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("TP2"));
                    parameter.setParameterName("TP2");
                    parameter.setParameterDescription("端口2总压");
                    parameters.add(parameter);
                }
                if(res.containsKey("TT1")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("TT1"));
                    parameter.setParameterName("TT1");
                    parameter.setParameterDescription("端口1总温");
                    parameters.add(parameter);
                }
                if(res.containsKey("TT2")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("TT2"));
                    parameter.setParameterName("TT2");
                    parameter.setParameterDescription("端口2总温");
                    parameters.add(parameter);
                }
                if(res.containsKey("SP1")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("SP1"));
                    parameter.setParameterName("SP1");
                    parameter.setParameterDescription("端口1静压");
                    parameters.add(parameter);
                }
                if(res.containsKey("SP2")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("SP2"));
                    parameter.setParameterName("SP2");
                    parameter.setParameterDescription("端口2静压");
                    parameters.add(parameter);
                }
                if(res.containsKey("ST1")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("ST1"));
                    parameter.setParameterName("ST1");
                    parameter.setParameterDescription("端口1静温");
                    parameters.add(parameter);
                }
                if(res.containsKey("ST2")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("ST2"));
                    parameter.setParameterName("ST2");
                    parameter.setParameterDescription("端口2静温");
                    parameters.add(parameter);
                }
                if(res.containsKey("MA1")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("MA1"));
                    parameter.setParameterName("MA1");
                    parameter.setParameterDescription("端口1马赫数");
                    parameters.add(parameter);
                }
                if(res.containsKey("MA2")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("MA2"));
                    parameter.setParameterName("MA2");
                    parameter.setParameterDescription("端口2马赫数");
                    parameters.add(parameter);
                }
                if(res.containsKey("V1")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("V1"));
                    parameter.setParameterName("V1");
                    parameter.setParameterDescription("端口1速度");
                    parameters.add(parameter);
                }
                if(res.containsKey("V2")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("V2"));
                    parameter.setParameterName("V2");
                    parameter.setParameterDescription("端口2速度");
                    parameters.add(parameter);
                }
                if(res.containsKey("DEN1")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("DEN1"));
                    parameter.setParameterName("DEN1");
                    parameter.setParameterDescription("端口1密度");
                    parameters.add(parameter);
                }
                if(res.containsKey("DEN2")) {
                    Parameter parameter = new Parameter();
                    parameter.setParameterValue(res.getDouble("DEN2"));
                    parameter.setParameterName("DEN2");
                    parameter.setParameterDescription("端口2密度");
                    parameters.add(parameter);
                }

                component.setOutputs(parameters);
                component.setComponentName(com.getString("Name"));
                component.setComponentType(com.getString("Type"));

            }

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
