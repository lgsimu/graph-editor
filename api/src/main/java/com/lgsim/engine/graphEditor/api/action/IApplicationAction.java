package com.lgsim.engine.graphEditor.api.action;

import javax.swing.*;

@SuppressWarnings("unused")
public interface IApplicationAction {

  /**
   * 获取打开文档的动作
   *
   * @return 动作
   */
  Action getDocumentOpenAction();
}
