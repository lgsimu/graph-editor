package com.lgsim.engine.graphEditor.data.components.library.tubep2.tubep2_110;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.tubep2.tubep2_base.Tubep2Base;

import java.util.ArrayList;
import java.util.List;

/**
 * 110类型元件
 */
public class Tubep2_110 extends Tubep2Base {

    private final String ID = "110";

    public Tubep2_110() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {

        IVertexArgumentImpl aa = new IVertexArgumentImpl("AA","2",0,0,0,"进口面积");
        IVertexArgumentImpl geo1 = new IVertexArgumentImpl("GEO1","2",0,0,0,"出口面积");
        IVertexArgumentImpl geo2 = new IVertexArgumentImpl("GEO2","1",0,0,0,"水力直径");
        IVertexArgumentImpl geo3 = new IVertexArgumentImpl("GEO3","1",0,0,0,"长度");
        IVertexArgumentImpl geo4 = new IVertexArgumentImpl("GEO4","1",0,0,0,"周长");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo1);
        arguments.add(geo2);
        arguments.add(geo3);
        arguments.add(geo4);

        return arguments;
    }
}
