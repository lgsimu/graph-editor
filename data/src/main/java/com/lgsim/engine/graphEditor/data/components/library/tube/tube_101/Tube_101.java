package com.lgsim.engine.graphEditor.data.components.library.tube.tube_101;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.tube.tube_base.TubeBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 101类型元件
 */
public class Tube_101 extends TubeBase {

    private final String ID = "101";

    public Tube_101() {
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
