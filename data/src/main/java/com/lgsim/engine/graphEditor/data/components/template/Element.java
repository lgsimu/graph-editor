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

/**
 * 元件库
 */
public class Element implements IStencilContext {

    private List<IVertexStencil> elements;//元件库模板


    @Override
    public @NotNull List<IVertexStencil> getPredefinedStencils() {

        //应该从文件中获取元件模板，生成元件库，按照类展示
        elements = new ArrayList<IVertexStencil>();//初始化

        //读取文件
        String path = "com/lgsim/engine/graphEditor/data/components/components.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        //将json文件转化为字符串
        String jsonStr = null;
        try {
            jsonStr = IOUtils.toString(is);
        }catch (IOException e) {
            e.getStackTrace();
        }
        //System.out.println(jsonStr);
        //解析jsonStr
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JSONArray coms = JSONArray.fromObject(jsonObject.get("Components"));

        for (Object object : coms) {

            JSONObject jObject = (JSONObject)object;
            JSONArray types = JSONArray.fromObject(jObject.get("ComponentType"));

            for (Object objectType : types) {
                Component component = new Component();

                JSONObject jObjectType = (JSONObject)objectType;
                JSONArray arguments = JSONArray.fromObject(jObjectType.get("arguments"));
                JSONArray results = JSONArray.fromObject(jsonObject.get("Result"));
                List<IVertexArgument> parameters = new ArrayList<IVertexArgument>();
                List<IVertexOutput> outParameters = new ArrayList<IVertexOutput>();

                for (Object objectArgument : arguments) {
                    Parameter parameter = new Parameter();

                    JSONObject jObjectArgument = (JSONObject)objectArgument;

                    parameter.setParameterName(jObjectArgument.getString("ParameterName"));
                    parameter.setParameterUnitID(jObjectArgument.getString("ParameterUnitID"));
                    parameter.setParameterDescription(jObjectArgument.getString("ParameterDescription"));

                    parameters.add(parameter);


                }

                for (Object objectResult : results) {
                    Parameter outParameter = new Parameter();


                    JSONObject jResult = (JSONObject)objectResult;

                    outParameter.setParameterName(jResult.getString("ParameterName"));
                    outParameter.setParameterUnitID(jResult.getString("ParameterUnitID"));
                    outParameter.setParameterDescription(jResult.getString("ParameterDescription"));

                    outParameters.add(outParameter);
                }

                component.setComponentType(jObjectType.getString("Type"));
                component.setArguments(parameters);
                component.setOutputs(outParameters);
                component.setComponentTemplateName(jObject.getString("ComponentTemplateName"));
                component.setImageSource(jObject.getString("ImageSource"));

                elements.add(component);
            }
        }
        return elements;
    }

    @Override
    public @NotNull List<IVertexStencil> getUserDefinedStencils() {

        /*IGraph iGraph = new Graph();
        Collection<IVertex> list = new ArrayList<>();
        list = iGraph.getAllVertexes();

        for (Object objectCom : list) {

            List<IVertexArgument> arguments = new ArrayList<>();

            for (Object objectPar : arguments) {
                Parameter parameter = new Parameter();

                parameter.setParameterName(objectCom);
                parameter.setParameterValue(objectCom);
                parameter.setParameterUnitID(objectCom);
                parameter.setParameterDescription(objectCom);
            }

            Component component = new Component();
            component.setComponentName(objectCom);
            component.setComponentTemplateName(objectCom);
            component.setTypeID(objectCom);
            component.setArguments(objectCom);
            component.setArmnodes(objectCom);
        }*/

        return new ArrayList<IVertexStencil>();
    }

    @Override
    public void saveUserDefinedStencil(@NotNull IVertexStencil stencil) {

    }

    /**
     * 获取腔节点
     * @return cavity对象
     */
    @Override
    public @NotNull IVertexStencil getCavityStencil() {
        Cavity cavity = new Cavity();
        return cavity;
    }

    @Override
    public @NotNull IVertexStencil getGlobalStencil() {

        //读取文件
        String path = "com/lgsim/engine/graphEditor/data/globalparameters/globalparameters.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        //将json文件转化为字符串
        String jsonStr = null;
        try {
            jsonStr = IOUtils.toString(is);
        }catch (IOException e) {
            e.getStackTrace();
        }
        //System.out.println(jsonStr);
        //解析jsonStr
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JSONArray globs = JSONArray.fromObject(jsonObject.get("GlobalParameters"));

        List<IVertexArgument> arguments = new ArrayList<>();

        for (Object globObject : globs) {
            JSONObject jGlobObject = (JSONObject)globObject;
            Parameter parameter = new Parameter();

            parameter.setParameterName(jGlobObject.getString("name"));
            parameter.setParameterUnitID(jGlobObject.getString("unitId"));
            parameter.setParameterDescription(jGlobObject.getString("description"));

            arguments.add(parameter);
        }
        Component component = new Component();

        component.setArguments(arguments);



        return component;
    }
}
