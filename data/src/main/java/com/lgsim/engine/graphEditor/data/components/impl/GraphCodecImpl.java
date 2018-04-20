package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphCodec;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.util.exception.DecodeException;
import com.lgsim.engine.graphEditor.util.exception.EncodeException;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

public class GraphCodecImpl implements IGraphCodec
{
  @Override
  public @NotNull byte[] encode(@NotNull IGraph graph) throws EncodeException
  {
    try
    {
      FileOutputStream fos = new FileOutputStream("graph.out");
      ObjectOutputStream oos = new ObjectOutputStream(fos);

      oos.writeObject(graph);
      oos.close();
      return null;
    }
    catch (Exception e)
    {
      throw new EncodeException();
    }
  }


  @Override
  public @NotNull IGraph decode(@NotNull byte[] bytes) throws DecodeException
  {
    try
    {
      FileInputStream fis = new FileInputStream("graph.out");
      ObjectInputStream ois = new ObjectInputStream(fis);

      bytes = (byte[]) ois.readObject();
      ois.close();

      return new IGraph()
      {
        @Override
        public Collection<IVertex> getAllVertexes()
        {
          return null;
        }
      };
    }
    catch (Exception e)
    {
      throw new DecodeException();
    }
  }
}
