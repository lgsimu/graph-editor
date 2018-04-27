package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.Document;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("WeakerAccess")
public abstract class SolverAction extends DocumentAction {

  public SolverAction(@NotNull Document document) {
    super(document);
  }
}
