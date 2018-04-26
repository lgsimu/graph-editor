package com.lgsim.engine.graphEditor.api.widget;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("WeakerAccess")
public interface IApplicationWidget extends IWidget {

  /**
   * 获取应用动作
   *
   * @return 应用动作
   */
  @NotNull IApplicationAction getApplicationAction();


  /**
   * 设置应用动作
   *
   * @param action 应用动作
   */
  void setApplicationAction(@NotNull IApplicationAction action);
}
