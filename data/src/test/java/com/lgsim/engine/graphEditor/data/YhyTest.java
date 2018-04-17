package com.lgsim.engine.graphEditor.data;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
//import com.lgsim.engine.graphEditor.data.components.impl.ComponentImpl;
import com.lgsim.engine.graphEditor.data.components.component.ComponentsTest;
import com.lgsim.engine.graphEditor.data.components.impl.Encode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class YhyTest {
    public static void main(String[] args) throws Exception {

        ComponentsTest com = new ComponentsTest();
        List<IVertexStencil> list = com.getPredefinedStencils();

        System.out.println(list);
        System.out.println(list.size());
    }
}

