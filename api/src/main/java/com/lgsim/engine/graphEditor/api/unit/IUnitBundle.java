package com.lgsim.engine.graphEditor.api.unit;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * 单位集合
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface IUnitBundle {

  /**
   * 获取单位集合ID
   *
   * @return 单位集合ID
   */
  @NotNull String getID();


  /**
   * 获取单位集合名字
   *
   * @return 单位集合名字
   */
  @NotNull String getName();


  /**
   * 获取单位家族
   *
   * @return 单位家族
   */
  @NotNull Collection<IUnit> getUnitFamily();
}
