package com.lgsim.engine.graphEditor.data.components.library.seal.seal_136;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.seal.seal_base.SealBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 136类型元件
 */
public class Seal_136 extends SealBase {

    private final String ID = "136";

    public Seal_136() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {

        IVertexArgumentImpl aa = new IVertexArgumentImpl("AA","2",0,0,0,"流通面积");
        IVertexArgumentImpl geo1 = new IVertexArgumentImpl("GEO1","1",0,0,0,"齿顶间隙");
        IVertexArgumentImpl geo2 = new IVertexArgumentImpl("GEO2","1",0,0,0,"齿顶厚度");
        IVertexArgumentImpl geo3 = new IVertexArgumentImpl("GEO3","1",0,0,0,"齿间距");
        IVertexArgumentImpl geo4 = new IVertexArgumentImpl("GEO4","0",0,0,0,"齿数");
        IVertexArgumentImpl geo5 = new IVertexArgumentImpl("GEO5","1",0,0,0,"齿高");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo1);
        arguments.add(geo2);
        arguments.add(geo3);
        arguments.add(geo4);
        arguments.add(geo5);

        return arguments;
    }
}
