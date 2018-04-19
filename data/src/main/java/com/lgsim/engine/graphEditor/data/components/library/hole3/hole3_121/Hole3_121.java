package com.lgsim.engine.graphEditor.data.components.library.hole3.hole3_121;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.hole3.hole3_base.Hole3Base;

import java.util.ArrayList;
import java.util.List;

/**
 * 121类型元件
 */
public class Hole3_121 extends Hole3Base {

    private final String ID = "121";

    public Hole3_121() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {

        IVertexArgumentImpl aa = new IVertexArgumentImpl("AA","2",0,0,0,"孔面积");
        IVertexArgumentImpl geo3 = new IVertexArgumentImpl("GEO3","1",0,0,0,"孔回转半径");
        IVertexArgumentImpl geo4 = new IVertexArgumentImpl("GEO4","0",0,0,0,"转速");
        IVertexArgumentImpl geo7 = new IVertexArgumentImpl("GEO7","0",0,0,0,"旋转比变化用系数");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo3);
        arguments.add(geo4);
        arguments.add(geo7);

        return arguments;
    }
}
