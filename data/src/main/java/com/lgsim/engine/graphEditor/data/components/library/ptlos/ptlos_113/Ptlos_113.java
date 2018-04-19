package com.lgsim.engine.graphEditor.data.components.library.ptlos.ptlos_113;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.ptlos.ptlos_base.Ptlos;

import java.util.ArrayList;
import java.util.List;

/**
 * 113类型元件
 */
public class Ptlos_113 extends Ptlos {

    private final String ID = "113";//元件类型

    public Ptlos_113() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {
        IVertexArgumentImpl aa = new IVertexArgumentImpl("AA","2",0,0,0,"进口面积");
        IVertexArgumentImpl geo1 = new IVertexArgumentImpl("GEO1","2",0,0,0,"出口面积");
        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo1);
        return arguments;
    }
}
