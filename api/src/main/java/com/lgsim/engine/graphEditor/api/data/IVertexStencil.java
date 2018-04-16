package com.lgsim.engine.graphEditor.api.data;

import java.util.List;

/**
 * 元件模板
 */
public interface IVertexStencil
{
  /**
   * 是否是预定义的
   */
  boolean isPredefined();


  /**
   * id
   */
  String getID();


  /**
   * 名字
   */
  String getName();


  /**
   * 获取元件模板图标
   */
  String getStencilIcon();


  /**
   * 获取元件模板在图中的图标
   */
  String getGraphIcon();


  /**
   * 输入参数
   */
  List<IVertexArgument> getArguments();


  /**
   * 计算后的输出
   */
  List<IVertexOutput> getOutputs();


  /**
   * 连接限制
   */
  IVertexRestriction getRestriction();
}
