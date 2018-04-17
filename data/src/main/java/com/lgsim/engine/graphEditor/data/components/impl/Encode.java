package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphEncoder;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class Encode implements IGraphEncoder {

    /**
     * 从图中读取信息，生成数据流
     */
    @Override
    public Serializable encode(@NotNull IGraph graph) throws Exception {

        FileOutputStream fos = new FileOutputStream("graph.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(graph);
        oos.close();

        return (Serializable) oos;

    }


}
