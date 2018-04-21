package com.lgsim.engine.graphEditor.api.calc;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * 求解器执行环境
 */
public interface ISolverEnvironment
{
  /**
   * 获取求解器可执行文件
   *
   * @return 可执行文件
   */
  @NotNull File getExecutableFile();


  /**
   * 获取要计算的图
   *
   * @return 图，如果没有，返回{@code null}
   */
  @Nullable IGraph getGraph();


  /**
   * 获取案例名字
   *
   * @return 案例名字
   */
  @NotNull String getCaseName();


  /**
   * 获取命令行参数
   *
   * @return 命令行参数
   */
  @NotNull String getSolverCommandlineArguments();
}
