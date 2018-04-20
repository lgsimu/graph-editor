package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphDecoder;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;

/**
 * 解码实现类
 */
public class IGraphDecoderImpl implements IGraphDecoder {

    /**
     * 从输入数据流中解码出图
     */
    @Override
    public IGraph decode(@NotNull Serializable serializable) throws IOException, ClassNotFoundException
    {


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
