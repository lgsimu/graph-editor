package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IStencilContext
{
  List<IStencil> getPredefinedStencils();


  List<IStencil> getUserDefinedStencils();


  void saveUserDefinedStencil(@NotNull IStencil stencil);
}
