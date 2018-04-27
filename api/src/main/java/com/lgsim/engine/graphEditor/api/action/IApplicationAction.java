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
   * 弹出菜单  标准  动作
   *
   * @return
   */
  @NotNull Action getStandardAction();


  /**
   * 弹出菜单 布局 动作
   *
   * @return
   */
  @NotNull Action getLayoutAction();


  /**
   * 弹出菜单 动作  动作
   *
   * @return
   */
  @NotNull Action getMoveAction();


  /**
   * 弹出菜单 格式 动作
   *
   * @return
   */
  @NotNull Action getFormatAction();


  /**
   * 弹出菜单 工具 动作
   *
   * @return
   */
  @NotNull Action getToolAction();


  /**
   * 弹出菜单 视图 动作
   *
   * @return
   */
  @NotNull Action getViewAction();


  /**
   * 弹出菜单 图元控件 动作
   *
   * @return
   */
  @NotNull Action getControlAction();


  /**
   * 弹出菜单 图元库 动作
   *
   * @return
   */
  @NotNull Action getBankAction();


  /**
   * 弹出菜单 自定义 动作
   *
   * @return
   */

  @NotNull Action getCustomAction();


  /**
   * 获取求解器计算动作
   *
   * @return 动作
   */
  @NotNull Action getSolverCalcAction();


  /**
   * 获取求解器设置动作
   *
   * @return 动作
   */
  @NotNull Action getSolverSettingAction();
}
