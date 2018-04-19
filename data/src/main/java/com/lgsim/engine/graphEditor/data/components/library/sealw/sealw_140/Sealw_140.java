package com.lgsim.engine.graphEditor.data.components.library.sealw.sealw_140;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.sealw.sealw_base.SealwBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 140类型元件
 */
public class Sealw_140 extends SealwBase {

    private final String ID = "140";

    public Sealw_140() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {

        IVertexArgumentImpl aa = new IVertexArgumentImpl("AA","2",0,0,0,"流通面积");
        IVertexArgumentImpl geo1 = new IVertexArgumentImpl("GEO1","0",0,0,0,"流量系数");
        IVertexArgumentImpl geo2 = new IVertexArgumentImpl("GEO2","1",0,0,0,"封严间隙");
        IVertexArgumentImpl geo3 = new IVertexArgumentImpl("GEO3","1",0,0,0,"旋转盘外缘半径");
        IVertexArgumentImpl geo4 = new IVertexArgumentImpl("GEO4","0",0,0,0,"转速");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo1);
        arguments.add(geo2);
        arguments.add(geo3);
        arguments.add(geo4);

        return arguments;
    }
}
