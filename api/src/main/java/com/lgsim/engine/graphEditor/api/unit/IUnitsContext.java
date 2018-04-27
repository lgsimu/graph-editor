package com.lgsim.engine.graphEditor.api.unit;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * 单位上下文
 */
public interface IUnitsContext {

  /**
   * 获取当前上下文提供(支持)的单位集合
   *
   * @return 单位集合
   */
  @NotNull Collection<IUnitBundle> getSupportUnitBundles();
}
