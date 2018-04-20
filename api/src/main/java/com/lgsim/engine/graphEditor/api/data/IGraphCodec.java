package com.lgsim.engine.graphEditor.api.data;

import com.lgsim.engine.graphEditor.api.exception.DecodeException;
import com.lgsim.engine.graphEditor.api.exception.EncodeException;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface IGraphCodec
{
    /**
     * 从图中读取信息，生成数据流
     * @param graph 图数据模型
     * @return 序列化对象
     * @throws EncodeException 编码异常
     */
  @NotNull Serializable encode(@NotNull IGraph graph) throws EncodeException;

    /**
     * 解码后，生成输出流
     * @param serializable 序列化对象
     * @return os流
     * @throws DecodeException 解码异常
     */
  @NotNull IGraph decode(@NotNull Serializable serializable) throws DecodeException;
}
