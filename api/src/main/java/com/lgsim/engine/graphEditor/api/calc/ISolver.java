package com.lgsim.engine.graphEditor.api.calc;

import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
import com.lgsim.engine.graphEditor.util.exception.InvokeExecutableException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ISolver
{
  List<IVertexOutput> calc(@NotNull ISolverEnvironment environment) throws CalcException;


  /**
   * 调用用于计算的可执行程序
   *
   * @param environment 执行环境参数
   * @return 执行结果
   * @throws InvokeExecutableException 如果调用中出现异常
   */
  InvokeCalcExecutableResult invokeCalcExecutable(@NotNull ISolverEnvironment environment) throws InvokeExecutableException;
}
