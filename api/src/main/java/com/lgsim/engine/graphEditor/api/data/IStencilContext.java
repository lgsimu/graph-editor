package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IStencilContext
{
  /**
   * 获取预定义的元件模板
   */
  List<IVertexStencil> getPredefinedStencils();


  /**
   * 获取用户定义的元件模板
   */
  List<IVertexStencil> getUserDefinedStencils();


  /**
   * 保存用户定义的元件模板
   */
  void saveUserDefinedStencil(@NotNull IVertexStencil stencil);


  /**
   * 获取腔节点模板
   */
  IVertexStencil getCavityStencil();
}
