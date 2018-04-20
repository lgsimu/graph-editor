package com.lgsim.engine.graphEditor.api.graph.impl;

import com.lgsim.engine.graphEditor.api.exception.DecodeException;
import com.lgsim.engine.graphEditor.api.exception.EncodeException;
import com.lgsim.engine.graphEditor.api.graph.IGraphStyle;
import com.lgsim.engine.graphEditor.api.graph.IGraphStyleCodec;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class GraphStyleCodecImpl implements IGraphStyleCodec
{
  @Override
  public @NotNull Serializable encode(@NotNull IGraphStyle style) throws EncodeException
  {
    return null;
  }


  @Override
  public @NotNull IGraphStyle decode(@NotNull Serializable serializable) throws DecodeException
  {
    return null;
  }
}
