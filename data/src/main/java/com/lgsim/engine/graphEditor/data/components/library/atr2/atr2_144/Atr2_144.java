package com.lgsim.engine.graphEditor.data.components.library.atr2.atr2_144;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.atr2.atr2_base.Atr2Base;

import java.util.ArrayList;
import java.util.List;

/**
 * 144类型元件
 */
public class Atr2_144 extends Atr2Base {

    private final String ID = "144";

    public Atr2_144() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {

        IVertexArgumentImpl aa = new IVertexArgumentImpl("AA","2",0,0,0,"流通面积");
        IVertexArgumentImpl geo1 = new IVertexArgumentImpl("GEO1","1",0,0,0,"计算半径");
        IVertexArgumentImpl geo2 = new IVertexArgumentImpl("GEO2","1",0,0,0,"预旋腔出口径向位置");
        IVertexArgumentImpl geo3 = new IVertexArgumentImpl("GEO3","1",0,0,0,"预旋腔内径");
        IVertexArgumentImpl geo4 = new IVertexArgumentImpl("GEO4","0",0,0,0,"转速");
        IVertexArgumentImpl geo5 = new IVertexArgumentImpl("GEO5","1",0,0,0,"预旋腔外径");
        IVertexArgumentImpl geo6 = new IVertexArgumentImpl("GEO6","1",0,0,0,"预旋腔宽度");
        IVertexArgumentImpl geo7 = new IVertexArgumentImpl("GEO7","0",0,0,0,"盘面摩阻系数修正系数");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo1);
        arguments.add(geo2);
        arguments.add(geo3);
        arguments.add(geo4);
        arguments.add(geo5);
        arguments.add(geo6);
        arguments.add(geo7);

        return arguments;
    }
}
