package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocument;
import com.lgsim.engine.graphEditor.graph.editor.action.DocumentSaveAction;
import com.lgsim.engine.graphEditor.graph.editor.action.SolverCalcAction;
import com.lgsim.engine.graphEditor.graph.editor.action.SolverSettingAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class EditorMenuBar extends JMenuBar
{
  private final GraphEditor editor;


  public EditorMenuBar(@NotNull GraphEditor editor)
  {
    this.editor = editor;
    addFileMenu();
    addSolverMenu();
  }


  private void addFileMenu()
  {
    JMenu doc = new JMenu(IGraphDocument.nameText);
    Action save = editor.bind(IGraphDocument.saveText, new DocumentSaveAction(), IGraphDocument.saveIcon);
    doc.add(save);
    add(doc);
  }


  private void addSolverMenu()
  {
    JMenu solver = new JMenu(ISolver.nameText);
    Action calc = editor.bind(ISolver.calcText, new SolverCalcAction(), ISolver.calcIcon);
    Action settings = editor.bind(ISolver.settingsText, new SolverSettingAction(), ISolver.settingsIcon);
    solver.add(calc);
    solver.add(settings);
    add(solver);
  }
}