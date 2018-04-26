package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;

import java.util.ArrayList;
import java.util.Collection;

public class IGraphImpl implements IGraph {
    @Override
    public Collection<IVertex> getAllVertexes() {

        VertexImpl iVertexImpl = new VertexImpl();
        Collection<IVertex> iVertices = new ArrayList<IVertex>();
        iVertices.add(iVertexImpl);

        return iVertices;
    }
}
