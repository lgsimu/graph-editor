package com.lgsim.engine.graphEditor.api.calc;

import org.jetbrains.annotations.NotNull;

public interface ISolver
{
  int calc(@NotNull ISolverEnvironment environment);
}
