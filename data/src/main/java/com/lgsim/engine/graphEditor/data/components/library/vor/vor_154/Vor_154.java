package com.lgsim.engine.graphEditor.data.components.library.vor.vor_154;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.vor.vor_base.VorBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 154类型元件
 */
public class Vor_154 extends VorBase {

    private final String ID = "154";

    public Vor_154() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {

        IVertexArgumentImpl geo1 = new IVertexArgumentImpl("GEO1","1",0,0,0,"进口半径");
        IVertexArgumentImpl geo2 = new IVertexArgumentImpl("GEO2","1",0,0,0,"出口半径");
        IVertexArgumentImpl geo3 = new IVertexArgumentImpl("GEO3","0",0,0,0,"旋转比");
        IVertexArgumentImpl geo4 = new IVertexArgumentImpl("GEO4","0",0,0,0,"右盘转速");
        IVertexArgumentImpl geo5 = new IVertexArgumentImpl("GEO5","0",0,0,0,"左盘转速");
        IVertexArgumentImpl geo7 = new IVertexArgumentImpl("GEO7","1",0,0,0,"盘间距");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(geo1);
        arguments.add(geo2);
        arguments.add(geo3);
        arguments.add(geo4);
        arguments.add(geo5);
        arguments.add(geo7);

        return arguments;
    }
}
