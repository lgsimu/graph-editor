package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IVertex;

import java.util.ArrayList;
import java.util.Collection;

public class IGraphImpl implements IGraph {
    @Override
    public Collection<IVertex> getAllVertexes() {
        IVertexImpl iVertexImpl = new IVertexImpl();
        Collection<IVertex> iVertices = new ArrayList<IVertex>();
        return iVertices;
    }
}
