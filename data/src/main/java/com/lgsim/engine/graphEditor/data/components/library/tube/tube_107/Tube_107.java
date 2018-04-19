package com.lgsim.engine.graphEditor.data.components.library.tube.tube_107;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.tube.tube_base.TubeBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 107类型元件
 */
public class Tube_107 extends TubeBase {

    private final String ID = "107";

    public Tube_107() {
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
        IVertexArgumentImpl geo8 = new IVertexArgumentImpl("GEO8","1",0,0,0,"R1");
        IVertexArgumentImpl geo9 = new IVertexArgumentImpl("GEO9","1",0,0,0,"R2");
        IVertexArgumentImpl geo10 = new IVertexArgumentImpl("GEO10","0",0,0,0,"转速");
        IVertexArgumentImpl geo11 = new IVertexArgumentImpl("GEO11","14",0,0,0,"吸力面燃气换热系数");
        IVertexArgumentImpl geo12 = new IVertexArgumentImpl("GEO12","24",0,0,0,"吸力面燃气总温");
        IVertexArgumentImpl geo13 = new IVertexArgumentImpl("GEO13","29",0,0,0,"吸力面壁厚与壁导热系数比");
        IVertexArgumentImpl geo14 = new IVertexArgumentImpl("GEO14","0",0,0,0,"吸力面壁导热与冷气换热面积比");
        IVertexArgumentImpl geo15 = new IVertexArgumentImpl("GEO15","1",0,0,0,"吸力面内弧长");
        IVertexArgumentImpl geo17 = new IVertexArgumentImpl("GEO17","14",0,0,0,"压力面燃气换热系数");
        IVertexArgumentImpl geo18 = new IVertexArgumentImpl("GEO18","24",0,0,0,"压力面燃气总温");
        IVertexArgumentImpl geo19 = new IVertexArgumentImpl("GEO19","29",0,0,0,"压力面壁厚与壁导热系数比");
        IVertexArgumentImpl geo20 = new IVertexArgumentImpl("GEO20","0",0,0,0,"压力面壁导热与冷气换热面积比");
        IVertexArgumentImpl geo21 = new IVertexArgumentImpl("GEO21","1",0,0,0,"压力面内弧长");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo1);
        arguments.add(geo2);
        arguments.add(geo3);
        arguments.add(geo4);
        arguments.add(geo8);
        arguments.add(geo9);
        arguments.add(geo10);
        arguments.add(geo11);
        arguments.add(geo12);
        arguments.add(geo13);
        arguments.add(geo14);
        arguments.add(geo15);
        arguments.add(geo17);
        arguments.add(geo18);
        arguments.add(geo19);
        arguments.add(geo20);
        arguments.add(geo21);

        return arguments;
    }
}
