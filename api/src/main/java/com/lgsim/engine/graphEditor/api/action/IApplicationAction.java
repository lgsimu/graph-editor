package com.lgsim.engine.graphEditor.api.action;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings("unused")
public interface IApplicationAction {

  /**
   * 复制节点
   *
   * @return 动作
   */
  @NotNull Action getVertexCellCopyAction();


  /**
   * 粘贴节点
   *
   * @return 动作
   */
  @NotNull Action getVertexCellPasteAction();


  /**
   * 删除节点
   *
   * @return 动作
   */
  @NotNull Action getVertexCellDeleteAction();


  /**
   * 剪贴节点
   *
   * @return 动作
   */
  @NotNull Action getVertexCellCutAction();


  /**
   * 新建文档
   *
   * @return 动作
   */
  @NotNull Action getDocumentNewAction();


  /**
   * 打开文档
   *
   * @return 动作
   */
  @NotNull Action getDocumentOpenAction();


  /**
   * 关闭文档
   *
   * @return 动作
   */
  @NotNull Action getDocumentCloseAction();


  /**
   * 保存文档
   *
   * @return 动作
   */
  @NotNull Action getDocumentSaveAction();


  /**
   * 退出应用
   *
   * @return 动作
   */
  @NotNull Action getApplicationExitAction();


  /**
   * 计算配置
   *
   * @return 动作
   */
  @NotNull Action getSolverSettingsAction();
}
