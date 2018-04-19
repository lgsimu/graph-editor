package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IStencilContext
{
  /**
   * 获取预定义的元件模板
   *
   * @return 如果没有，大小为空
   */
  @NotNull
  List<IVertexStencil> getPredefinedStencils();


  /**
   * 获取用户定义的元件模板
   *
   * @return 如果没有，大小为空
   */
  @NotNull
  List<IVertexStencil> getUserDefinedStencils();


  /**
   * 保存用户定义的元件模板
   */
  void saveUserDefinedStencil(@NotNull IVertexStencil stencil);


  /**
   * 获取腔节点模板
   */
  @NotNull
  IVertexStencil getCavityStencil();
}
