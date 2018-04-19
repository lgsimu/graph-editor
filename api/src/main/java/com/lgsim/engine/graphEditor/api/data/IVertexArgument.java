package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface IVertexArgument extends Serializable
{
  /**
   * 参数id
   */
  @NotNull
  String getID();


  /**
   * 单位
   */
  @NotNull
  String getUnit();


  /**
   * 最小值
   */
  double getMinValue();


  /**
   * 最大值
   */
  double getMaxValue();


  /**
   * 获取数值
   */
  double getValue();


  /**
   * 设置数值
   */
  void setValue(double value);


  /**
   * 描述
   */
  @NotNull
  String getDescription();
}
