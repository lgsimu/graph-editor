package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface IVertexOutput extends Serializable
{
    /**
     * 参数id
     */
    @NotNull
    String getID();


    /**
     * 单位
     */
    @NotNull
    String getUnit();


    /**
     * 获取数值
     */
    double getValue();



    /**
     * 描述
     */
    @NotNull
    String getDescription();
}
