package com.lgsim.engine.graphEditor.api.data;

import com.lgsim.engine.graphEditor.api.MessageBundle;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * 元件模板
 */
public interface IVertexStencil extends Serializable
{
  String predefinedText = MessageBundle.get("vertexStencil.predefined");

  String userDefinedText = MessageBundle.get("vertexStencil.userDefined");


  /**
   * 是否是预定义的
   */
  boolean isPredefined();


  /**
   * id
   */
  @NotNull
  String getID();


  /**
   * 名字
   */
  @NotNull
  String getName();


  /**
   * 获取元件模板图标
   */
  @NotNull
  String getStencilIcon();


  /**
   * 获取元件模板在图中的图标
   */
  @NotNull
  String getGraphIcon();


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


  /**
   * 连接限制
   */
  @NotNull
  IVertexRestriction getRestriction();
}
