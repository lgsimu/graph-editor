package com.lgsim.engine.graphEditor.data.components.template;


import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Template implements IStencilContext {

    private List<IVertexStencil> componentsTemplates;//元件库模板
    private List<IVertexStencil> allComponents;//使用的所有元件
    private Map<String,Unit> units;


    @Override
    public @NotNull List<IVertexStencil> getPredefinedStencils() {

        //应该从文件中获取元件模板，生成元件库，按照类展示
        componentsTemplates = new ArrayList<IVertexStencil>();//初始化

        /**
         * 'PTLOS'类型
         */
        Component component1 = new Component();
        Parameter parameter11 = new Parameter();
        Parameter parameter12 = new Parameter();
        Parameter parameter13 = new Parameter();
        List<IVertexArgument> parameters1 = new ArrayList<IVertexArgument>();

        parameter11.setParameterName("AA");
        parameter11.setParameterUnitID("2");
        parameter11.setParameterDescription("进口面积");
        parameter12.setParameterName("GE01");
        parameter12.setParameterUnitID("2");
        parameter12.setParameterDescription("出口面积");
        parameter13.setParameterName("GEO2");
        parameter13.setParameterUnitID("0");
        parameter13.setParameterDescription("总压损失系数");

        parameters1.add(parameter11);
        parameters1.add(parameter12);
        parameters1.add(parameter13);

        component1.setComponentTemplateName("PTLOS");
        //component1.setComponentType("111");
        component1.setImageSource("com/lgsim/engine/graphEditor/data/testjpg/PTLOS.png");
        component1.setArguments(parameters1);

        /**
         * PSOURCE类型
         */
        Component component2 = new Component();
        Parameter parameter21 = new Parameter();
        Parameter parameter22 = new Parameter();
        Parameter parameter23 = new Parameter();
        Parameter parameter24 = new Parameter();
        List<IVertexArgument> parameters2 = new ArrayList<IVertexArgument>();

        parameter21.setParameterName("GEO1");
        parameter21.setParameterUnitID("4");
        parameter21.setParameterDescription("总压");
        parameter22.setParameterName("GE02");
        parameter22.setParameterUnitID("24");
        parameter22.setParameterDescription("总温");
        parameter23.setParameterName("GEO3");
        parameter23.setParameterUnitID("2");
        parameter23.setParameterDescription("流通面积");
        parameter24.setParameterName("GEO4");
        parameter24.setParameterUnitID("30");
        parameter24.setParameterDescription("涡量");

        parameters2.add(parameter21);
        parameters2.add(parameter22);
        parameters2.add(parameter23);
        parameters2.add(parameter24);

        component2.setComponentTemplateName("PSOURCE");
        component2.setComponentType("1");
        component2.setImageSource("com/lgsim/engine/graphEditor/data/testjpg/PSOURCE.png");
        component2.setImageSource("com/lgsim/engine/graphEditor/data/testjpg/TUBE.png");
        component2.setArguments(parameters2);

        componentsTemplates.add(component1);
        componentsTemplates.add(component2);
        //System.out.println(componentsTemplates.size());
        return componentsTemplates;
    }

    @Override
    public @NotNull List<IVertexStencil> getUserDefinedStencils() {

        allComponents = new ArrayList<IVertexStencil>();


        //读取文件
        String path = "com/lgsim/engine/graphEditor/data/simpleCase.out";
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        //将json文件转化为字符串
        String jsonStr = null;

        //System.out.println(jsonStr);
        try {
            jsonStr = IOUtils.toString(in);
            //jsonStr = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(jsonStr);
        //解析jsonStr
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JSONObject components = JSONObject.fromObject(jsonObject.get("Components"));
        JSONArray coms = JSONArray.fromObject(components.get("Component"));


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

            allComponents.add(component);
        }

        return allComponents;
    }

    @Override
    public void saveUserDefinedStencil(@NotNull IVertexStencil stencil) {

    }

    @Override
    public @NotNull IVertexStencil getCavityStencil() {
        return new Component();
    }

    @Override
    public @NotNull IVertexStencil getGlobalStencil() {
        return new Component();
    }
}
