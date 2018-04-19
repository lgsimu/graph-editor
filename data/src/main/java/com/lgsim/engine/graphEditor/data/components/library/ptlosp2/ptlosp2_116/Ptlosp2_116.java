package com.lgsim.engine.graphEditor.data.components.library.ptlosp2.ptlosp2_116;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.ptlosp2.ptlosp2_base.Ptlosp2Base;

import java.util.ArrayList;
import java.util.List;

/**
 * 116类型元件
 */
public class Ptlosp2_116 extends Ptlosp2Base {

    private final String ID = "116";

    public Ptlosp2_116() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {

        IVertexArgumentImpl aa = new IVertexArgumentImpl("AA","2",0,0,0,"进口面积");
        IVertexArgumentImpl geo1 = new IVertexArgumentImpl("GEO1","2",0,0,0,"出口面积");
        IVertexArgumentImpl geo2 = new IVertexArgumentImpl("GEO2","0",0,0,0,"总压损失系数");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo1);
        arguments.add(geo2);

        return arguments;
    }
}
