package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.IconBundle;
import com.lgsim.engine.graphEditor.api.MessageBundle;
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
    JMenu doc = new JMenu(MessageBundle.get("graphDocument.name"));
    Action save = editor.bind(MessageBundle.get("graphDocument.save"), new DocumentSaveAction(), IconBundle.get("graphDocument.save"));
    doc.add(save);
    add(doc);
  }


  private void addSolverMenu()
  {
    JMenu solver = new JMenu(MessageBundle.get("solver.name"));
    Action calc = editor.bind(MessageBundle.get("solver.calc"), new SolverCalcAction(editor, editor), IconBundle.get("solver.calc"));
    Action settings = editor.bind(MessageBundle.get("solver.settings"), new SolverSettingAction(), IconBundle.get("solver.settings"));
    solver.add(calc);
    solver.add(settings);
    add(solver);
  }
}