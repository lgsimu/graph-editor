package com.lgsim.engine.graphEditor.api.data;

import java.util.List;

public interface IVertex
{
  /**
   * 节点id
   */
  String getID();


  /**
   * 节点类型id
   */
  String getTypeID();


  /**
   * 输入参数
   */
  List<IVertexArgument> getArguments();


  /**
   * 计算后的输出
   */
  List<IVertexOutput> getOutputs();


  /**
   * 输入到该节点的节点
   */
  List<IVertex> getInputPorts();


  /**
   * 该接口输出的节点
   */
  List<IVertex> getOutputPorts();


  /**
   * 是否是腔节点
   */
  boolean isCavity();
}
