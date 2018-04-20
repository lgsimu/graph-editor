package com.lgsim.engine.graphEditor.data.components.template;


import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template implements IStencilContext {

    private List<IVertexStencil> componentsTemplates;//元件库模板
    private Map<String,Component> allComponents;//使用的所有元件
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
        List<Parameter> parameters1 = new ArrayList<Parameter>();

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
        List<Parameter> parameters2 = new ArrayList<Parameter>();

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
        //component2.setComponentName("1");
        component2.setComponentType("1");
        component2.setImageSource("com/lgsim/engine/graphEditor/data/testjpg/PSOURCE.png");
        component2.setArguments(parameters2);

        componentsTemplates.add(component1);
        componentsTemplates.add(component2);
        return componentsTemplates;
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
