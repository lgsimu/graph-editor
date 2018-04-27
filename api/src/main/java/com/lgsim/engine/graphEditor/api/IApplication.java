package com.lgsim.engine.graphEditor.api;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.util.Configuration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"WeakerAccess", "unused"})
public interface IApplication {

  /**
   * 获取应用名字
   *
   * @return 应用名字
   */
  @NotNull String getImplementationTitle();


  /**
   * 获取应用版本
   *
   * @return 应用版本
   */
  @NotNull String getImplementationVersion();


  /**
   * 获取应用厂商
   *
   * @return 厂商
   */
  @NotNull String getImplementationVendor();


  /**
   * 获取应用配置
   *
   * @return 配置
   */
  @NotNull Configuration getConfiguration();


  /**
   * 获取应用动作
   *
   * @return 应用动作
   */
  @NotNull IApplicationAction getApplicationAction();


  /**
   * 设置应用动作
   *
   * @param action 动作
   */
  void setApplicationAction(@NotNull IApplicationAction action);


  /**
   * 获取求解器执行环境
   *
   * @return 求解器执行环境
   */
  @Nullable ISolverEnvironment getSolverEnvironment();


  /**
   * 设置求解器执行环境
   *
   * @param environment 求解器执行环境
   */
  void setSolverEnvironment(@NotNull ISolverEnvironment environment);
}
