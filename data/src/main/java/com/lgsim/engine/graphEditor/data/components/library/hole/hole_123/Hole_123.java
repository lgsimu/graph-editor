package com.lgsim.engine.graphEditor.data.components.library.hole.hole_123;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.hole.hole_base.HoleBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 123类型元件
 */
public class Hole_123 extends HoleBase {

    private final String ID = "123";

    public Hole_123() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {

        IVertexArgumentImpl aa = new IVertexArgumentImpl("AA","2",0,0,0,"孔面积");
        IVertexArgumentImpl geo1 = new IVertexArgumentImpl("GEO1","0",0,0,0,"CD");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo1);

        return arguments;
    }
}
