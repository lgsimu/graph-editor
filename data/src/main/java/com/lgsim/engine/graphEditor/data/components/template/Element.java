package com.lgsim.engine.graphEditor.data.components.template;

import com.lgsim.engine.graphEditor.api.data.IStencilContext;
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
            Component component = new Component();

            JSONObject jObject = (JSONObject)object;
            JSONArray types = JSONArray.fromObject(jObject.get("ComponentType"));

            for (Object objectType : types) {
                JSONObject jObjectType = (JSONObject)objectType;
                JSONArray arguments = JSONArray.fromObject(jObjectType.get("arguments"));

                for (Object objectArgument : arguments) {
                    Parameter parameter = new Parameter();
                    List<Parameter> parameters = new ArrayList<Parameter>();

                    JSONObject jObjectArgument = (JSONObject) objectArgument;

                    parameter.setParameterName(jObjectArgument.getString("ParameterName"));
                    parameter.setParameterUnitID(jObjectArgument.getString("ParameterUnitID"));
                    parameter.setParameterDescription(jObjectArgument.getString("ParameterDescription"));

                    parameters.add(parameter);
                    component.setArguments(parameters);

                }

                component.setComponentType(jObjectType.getString("Type"));

            }

            component.setComponentTemplateName(jObject.getString("ComponentTemplateName"));
            component.setImageSource(jObject.getString("ImageSource"));

            elements.add(component);
        }

        return elements;
    }

    @Override
    public @NotNull List<IVertexStencil> getUserDefinedStencils() {
        return new ArrayList<IVertexStencil>();
    }

    @Override
    public void saveUserDefinedStencil(@NotNull IVertexStencil stencil) {

    }

    @Override
    public @NotNull IVertexStencil getCavityStencil() {
        return new Component();
    }
}
