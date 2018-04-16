package com.lgsim.engine.graphEditor.api.data;

import java.io.InputStream;

public interface IGraphDecoder
{
  /**
   * 从输入数据流中解码出图
   */
  IGraph decode(InputStream inputStream);
}
