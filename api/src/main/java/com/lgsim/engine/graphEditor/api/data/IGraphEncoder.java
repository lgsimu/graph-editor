package com.lgsim.engine.graphEditor.api.data;

import java.io.OutputStream;

public interface IGraphEncoder
{
  /**
   * 从图中读取信息，生成数据流
   */
  OutputStream encode(IGraph graph);
}
