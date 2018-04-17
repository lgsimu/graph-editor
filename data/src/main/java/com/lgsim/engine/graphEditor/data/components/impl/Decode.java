package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphDecoder;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;

public class Decode implements IGraphDecoder {

    /**
     * 从输入数据流中解码出图
     */
    @Override
    public IGraph decode(@NotNull Serializable serializable) throws Exception {


        FileInputStream fis = new FileInputStream("graph.out");
        ObjectInputStream ois = new ObjectInputStream(fis);

        serializable = (Serializable) ois.readObject();
        ois.close();

        return new IGraph() {
            @Override
            public Collection<IVertex> getAllVertexes() {
                return null;
            }
        };
    }
}
