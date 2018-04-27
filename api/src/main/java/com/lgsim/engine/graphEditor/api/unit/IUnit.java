package com.lgsim.engine.graphEditor.api.unit;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * 单位
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface IUnit {

  /**
   * 单位名字
   *
   * @return 单位名字
   */
  @NotNull String getName();


  /**
   * 获取缩放比例
   *
   * @return 缩放比例
   */
  double getScale();


  /**
   * 获取偏移量
   *
   * @return 偏移量
   */
  int getOffset();


  /**
   * 是否是基本单位
   *
   * @return 如果是，返回{@code true}，否则返回{@code false}
   */
  boolean isBaseUnit();


  /**
   * 获取单位家族
   *
   * @return 单位家族
   */
  @NotNull Collection<IUnit> getUnitFamily();
}
