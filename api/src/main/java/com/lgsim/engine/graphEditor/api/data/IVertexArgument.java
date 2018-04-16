package com.lgsim.engine.graphEditor.api.data;

import java.math.BigDecimal;

public interface IVertexArgument
{
  /**
   * 参数id
   */
  String getID();


  /**
   * 单位
   */
  String getUnit();


  /**
   * 最小值
   */
  BigDecimal getMinValue();


  /**
   * 最大值
   */
  BigDecimal getMaxValue();


  /**
   * 获取数值
   */
  BigDecimal getValue();


  /**
   * 设置数值
   */
  void setValue(BigDecimal value);


  /**
   * 描述
   */
  String getDescription();
}
