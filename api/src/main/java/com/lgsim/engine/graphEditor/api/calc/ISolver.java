package com.lgsim.engine.graphEditor.api.calc;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
import com.lgsim.engine.graphEditor.util.exception.InvokeExecutableException;
import org.jetbrains.annotations.NotNull;

public interface ISolver
{
  /**
   * 计算当前图
   *
   * @param environment 计算环境
   * @return 携带节点输出的图
   * @throws CalcException 如果计算中出现异常
   */
  @NotNull IGraph calc(@NotNull ISolverEnvironment environment) throws CalcException;


  /**
   * 调用用于计算的可执行程序
   *
   * @param environment 执行环境参数
   * @return 执行结果
   * @throws InvokeExecutableException 如果调用中出现异常
   */
  @NotNull InvokeCalcExecutableResult invokeCalcExecutable(@NotNull ISolverEnvironment environment) throws InvokeExecutableException;
}
