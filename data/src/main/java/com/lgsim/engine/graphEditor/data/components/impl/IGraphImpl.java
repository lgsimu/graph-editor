package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class IGraphImpl implements IGraph {

    private Collection<IVertex> allVertexes;

    @Override
    public @NotNull Collection<IVertex> getVertexes() {
        return allVertexes;
    }

    public void setAllVertexes(Collection<IVertex> allVertexes) {
        this.allVertexes = allVertexes;
    }


    @Override
    public void retrieveCalcOutputs(@NotNull IGraph graph) {

    }
}
