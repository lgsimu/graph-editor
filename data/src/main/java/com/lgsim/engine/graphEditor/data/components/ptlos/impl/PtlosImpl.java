package com.lgsim.engine.graphEditor.data.components.ptlos.impl;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.ptlos.entity.Ptlos;

import java.util.ArrayList;
import java.util.List;

public class PtlosImpl implements IVertexStencil {

    //ReadPtlosJson readPtlosJson = new ReadPtlosJson();
    //ArrayList<Ptlos> ptlos = new ArrayList<Ptlos>();
    Ptlos ptlo = new Ptlos("","","","","",false,new ArrayList<Double>(),new ArrayList<Double>());

    /**
     * 是否是预定义的元件
     */
    @Override
    public boolean isPredefined() {
        return false;
    }

    /**
     * id
     */
    @Override
    public String getID() {
        return ptlo.getType();
    }

    /**
     * name
     */
    @Override
    public String getName() {
        return ptlo.getName();
    }

    /**
     * 获取元件模板图标
     */
    @Override
    public String getStencilIcon() {
        return ptlo.getStencilIcon();
    }

    /**
     * 获取元件模板图标中的图标
     */
    @Override
    public String getGraphIcon() {
        return ptlo.getGraphIcon();
    }

    /**
     * 输入参数
     */
    @Override
    public List<IVertexArgument> getArguments() {
        return null;
    }

    /**
     * 计算后的输出
     */
    @Override
    public List<IVertexOutput> getOutputs() {
        return null;
    }

    /**
     * 连接限制
     */
    @Override
    public IVertexRestriction getRestriction() {
        return null;
    }

    /**
     * 将数据存入对象中
     */
    public void setPtlos() {
        /*Ptlos ptlo = new Ptlos();
        return ptlo;*/
    }

    /**
     * 获取对象
     */
    public Ptlos getPtlo() {
        return ptlo;
    }
}
