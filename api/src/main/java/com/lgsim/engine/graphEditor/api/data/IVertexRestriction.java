package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public interface IVertexRestriction extends Serializable
{
  /**
   * 最小连入端口数
   */
  int getMinInputPortCount();


  /**
   * 最大连入端口数
   */
  int getMaxInputPortCount();


  /**
   * 最小连出端口数
   */
  int getMinOutputPortCount();


  /**
   * 最大连出端口数
   */
  int getMaxOutputPortCount();


  /**
   * 获取可以连入的端口的类型
   */
  @NotNull
  List<String> getInputPortTypes();


  /**
   * 获取可以连出的端口的类型
   */
  @NotNull
  List<String> getOutputPortTypes();
}
