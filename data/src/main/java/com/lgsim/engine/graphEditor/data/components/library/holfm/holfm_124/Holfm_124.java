package com.lgsim.engine.graphEditor.data.components.library.holfm.holfm_124;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.holfm.holfm_base.HolfmBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 124类型元件
 */
public class Holfm_124 extends HolfmBase {

    private final String ID = "124";

    public Holfm_124() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {

        IVertexArgumentImpl aa = new IVertexArgumentImpl("AA","2",0,0,0,"孔面积");
        IVertexArgumentImpl geo1 = new IVertexArgumentImpl("GEO1","1",0,0,0,"孔径");
        IVertexArgumentImpl geo2 = new IVertexArgumentImpl("GEO2","1",0,0,0,"孔长");
        IVertexArgumentImpl geo3 = new IVertexArgumentImpl("GEO3","6",0,0,0,"孔倾角");
        IVertexArgumentImpl geo5 = new IVertexArgumentImpl("GEO5","0",0,0,0,"燃气马赫数");
        IVertexArgumentImpl geo6 = new IVertexArgumentImpl("GEO6","24",0,0,0,"燃气温度");
        IVertexArgumentImpl geo7 = new IVertexArgumentImpl("GEO7","2",0,0,0,"孔上游通道截面积");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo1);
        arguments.add(geo2);
        arguments.add(geo3);
        arguments.add(geo5);
        arguments.add(geo6);
        arguments.add(geo7);

        return arguments;
    }
}
