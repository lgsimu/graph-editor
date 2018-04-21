package com.lgsim.engine.graphEditor.api.calc;

import com.lgsim.engine.graphEditor.api.IconBundle;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public interface ISolver
{
  String nameText = MessageBundle.get("solver.name");

  String calcText = MessageBundle.get("solver.calc");

  String settingsText = MessageBundle.get("solver.settings");

  Icon calcIcon = IconBundle.get("solver.calc");

  Icon settingsIcon = IconBundle.get("solver.settings");


  int calc(@NotNull ISolverEnvironment environment);
}
