package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public interface IVertex extends Serializable {

  /**
   * 节点id
   */
  @NotNull
  String getID();


  /**
   * 节点类型id
   */
  @NotNull
  String getTypeID();


  /**
   * 输入参数
   */
  @NotNull
  List<IVertexArgument> getArguments();


  /**
   * 计算后的输出
   */
  @NotNull
  List<IVertexOutput> getOutputs();


  void setOutputs(@NotNull List<IVertexOutput> outputs);


  /**
   * 输入到该节点的节点
   */
  @NotNull
  List<IVertex> getInputPorts();


  /**
   * 该接口输出的节点
   */
  @NotNull
  List<IVertex> getOutputPorts();


  /**
   * 是否是腔节点
   */
  boolean isCavity();


  /**
   * 是否是全局节点
   *
   * @return 如果是，返回{@code true}，否则返回{@code false}
   */
  boolean isGlobal();


  /**
   * 获取显示名
   *
   * @return 显示名
   */
  @NotNull String getDisplayName();
}
