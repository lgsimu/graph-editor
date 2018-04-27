package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphCodec;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.data.components.util.writejson.WriteJson;
import com.lgsim.engine.graphEditor.util.exception.DecodeException;
import com.lgsim.engine.graphEditor.util.exception.EncodeException;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;

public class GraphCodecImpl implements IGraphCodec
{
  @Override
  public @NotNull byte[] encode(@NotNull IGraph graph) throws EncodeException {

      Collection<IVertex> list = new ArrayList<>();

      list = graph.getAllVertexes();
      String path = "com/lgsim/engine/graphEditor/data/test/graph.out";

      WriteJson wj = new WriteJson();
      byte[] bjson = null;
      try {
          bjson = wj.writeJson(list,path);
      }catch (Exception e){
          throw new EncodeException();
      }


     /* JsonFormatTool jsonFormatTool = new JsonFormatTool();
      try {
          FileOutputStream fos = new FileOutputStream("com/lgsim/engine/graphEditor/data/test/graph.out");
          ObjectOutputStream oos = new ObjectOutputStream(fos);

          //String str = jsonFormatTool.formatJson(graph.toString());
          String str = com.alibaba.fastjson.JSONObject.toJSONString(graph);
          byte[] bjson = str.getBytes();

          oos.writeObject(bjson);
          oos.close();*/

      return bjson;
    }
    /*catch (Exception e)
    {
      throw new EncodeException();
    }*/



  @Override
  public @NotNull IGraph decode(@NotNull byte[] bytes) throws DecodeException {

      //IGraphImpl iGraph1 = new IGraphImpl();
      try {
          FileInputStream fis = new FileInputStream("com/lgsim/engine/graphEditor/data/test/graph.out");
          ObjectInputStream ois = new ObjectInputStream(fis);

          //String str = new String(bytes);
          IGraph iGraph = com.alibaba.fastjson.JSON.parseObject(bytes,IGraph.class);
          //bytes = (byte[]) ois.readObject();

          //JSONObject jsonObject = JSONObject.fromObject(bytes);

          ois.close();

          return iGraph;
    }
    catch (Exception e)
    {
      throw new DecodeException();
    }
  }
}
