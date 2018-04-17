package com.lgsim.engine.graphEditor.data;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.impl.ComponentImpl;
import com.lgsim.engine.graphEditor.data.components.impl.Encode;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class YhyTest {
    public static void main(String[] args) throws Exception {

        /*IGraph iGraph = new IGraph() {
            @Override
            public Collection<IVertex> getAllVertexes() {
                return null;
            }
        };
        Encode encode = new Encode();
        Serializable os = encode.encode(iGraph);*/
        ComponentImpl component = new ComponentImpl();

        List<IVertexStencil> list = component.getPredefinedStencils();
        System.out.print(list);
    }
}

