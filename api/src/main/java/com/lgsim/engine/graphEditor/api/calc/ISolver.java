package com.lgsim.engine.graphEditor.api.calc;

import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ISolver
{
  List<IVertexOutput> calc(@NotNull ISolverEnvironment environment) throws CalcException;


  InvokeCalcExecutableResult invokeCalcExecutable(@NotNull ISolverEnvironment environment);
}
