package com.lgsim.engine.graphEditor.api.widget;

import com.lgsim.engine.graphEditor.api.IApplication;
import org.jetbrains.annotations.NotNull;

/**
 * 应用组件
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface IApplicationWidget extends IWidget {

  /**
   * 获取组件所属应用
   *
   * @return 应用
   */
  @NotNull IApplication getApplication();


  /**
   * 设置组件所属应用
   *
   * @param application 应用
   */
  void setApplication(@NotNull IApplication application);
}
