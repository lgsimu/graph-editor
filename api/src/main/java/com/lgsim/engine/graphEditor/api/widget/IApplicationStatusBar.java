package com.lgsim.engine.graphEditor.api.widget;

import org.jetbrains.annotations.NotNull;

/**
 * 应用状态栏
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface IApplicationStatusBar extends IApplicationWidget {

  /**
   * 在状态栏中输出当前应用简略状态
   *
   * @param msg 要输出的消息
   */
  void render(@NotNull String msg);
}
