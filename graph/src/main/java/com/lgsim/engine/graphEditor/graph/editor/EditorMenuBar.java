package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.graph.editor.action.DocumentOpenAction;
import com.lgsim.engine.graphEditor.graph.editor.action.DocumentSaveAction;
import com.lgsim.engine.graphEditor.graph.editor.action.SolverCalcAction;
import com.lgsim.engine.graphEditor.graph.editor.action.SolverSettingAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class EditorMenuBar extends JMenuBar {
  private final GraphEditor editor;

  public EditorMenuBar(@NotNull GraphEditor editor) {
    this.editor = editor;
    addFileMenu();
    addSolverMenu();
    addVertexCellEditMenu();
  }

  private void addFileMenu() {
    JMenu doc = new JMenu(MessageBundle.get("graphDocument.name"));
    Action save = Builder.createAction("graphDocument.save", new DocumentSaveAction(), editor);
    Action open = Builder.createAction("graphDocument.open", new DocumentOpenAction(), editor);

    doc.add(open);
    doc.add(save);

    add(doc);
  }

  private void addSolverMenu() {
    JMenu solver = new JMenu(MessageBundle.get("solver.name"));
    Action calc = Builder.createAction("solver.calc", new SolverCalcAction(editor, editor), editor);
    Action settings = Builder.createAction("solver.settings", new SolverSettingAction(), editor);
    solver.add(calc);
    solver.add(settings);
    add(solver);
  }

  private void addVertexCellEditMenu() {

  }
}