package com.lgsim.engine.graphEditor.data.components.library.hole.hole_122;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.library.hole.hole_base.HoleBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 122类型元件
 */
public class Hole_122 extends HoleBase {

    private final String ID = "122";

    public Hole_122() {
        super.setType(ID);
    }

    /**
     * 重写父类的获得参数方法
     */
    @Override
    public List<IVertexArgument> getArguments() {

        IVertexArgumentImpl aa = new IVertexArgumentImpl("AA","2",0,0,0,"孔面积");
        IVertexArgumentImpl geo1 = new IVertexArgumentImpl("GEO1","1",0,0,0,"孔径");
        IVertexArgumentImpl geo3 = new IVertexArgumentImpl("GEO3","1",0,0,0,"孔长");
        IVertexArgumentImpl geo4 = new IVertexArgumentImpl("GEO4","0",0,0,0,"转速");
        IVertexArgumentImpl geo5 = new IVertexArgumentImpl("GEO5","1",0,0,0,"回转半径");

        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        arguments.add(aa);
        arguments.add(geo1);
        arguments.add(geo3);
        arguments.add(geo4);
        arguments.add(geo5);

        return arguments;
    }
}
