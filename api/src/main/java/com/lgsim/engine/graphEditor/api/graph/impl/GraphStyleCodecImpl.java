package com.lgsim.engine.graphEditor.api.graph.impl;

import com.lgsim.engine.graphEditor.api.graph.IGraphStyle;
import com.lgsim.engine.graphEditor.api.graph.IGraphStyleCodec;
import com.lgsim.engine.graphEditor.util.exception.DecodeException;
import com.lgsim.engine.graphEditor.util.exception.EncodeException;
import org.jetbrains.annotations.NotNull;

public class GraphStyleCodecImpl implements IGraphStyleCodec
{
  @Override
  public @NotNull byte[] encode(@NotNull IGraphStyle style) throws EncodeException
  {
    return new byte[0];
  }


  @Override
  public @NotNull IGraphStyle decode(@NotNull byte[] bytes) throws DecodeException
  {
    return null;
  }
}
