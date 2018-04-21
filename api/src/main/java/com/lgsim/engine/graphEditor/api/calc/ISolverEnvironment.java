package com.lgsim.engine.graphEditor.api.calc;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

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
   * 获取求解器输入文件
   *
   * @return 输入文件
   */
  @NotNull File getSolverInputFile() throws IOException;


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
