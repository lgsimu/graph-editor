package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.graph.PureCons;
import com.lgsim.engine.graphEditor.graph.action.DocumentOpenAction;
import com.lgsim.engine.graphEditor.graph.action.DocumentSaveAction;
import com.lgsim.engine.graphEditor.graph.action.SolverCalcAction;
import com.lgsim.engine.graphEditor.graph.action.SolverSettingAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class EditorMenuBar extends JMenuBar {
  private final Editor editor;

  public EditorMenuBar(@NotNull Editor editor) {
    this.editor = editor;
    addFileMenu();
    addSolverMenu();
    addVertexCellEditMenu();
  }

  private void addFileMenu() {
    JMenu doc = new JMenu(MessageBundle.get("graphDocument.name"));
    Action save = PureCons.createAction("graphDocument.save", new DocumentSaveAction(), editor);
    Action open = PureCons.createAction("graphDocument.open", new DocumentOpenAction(), editor);

    doc.add(open);
    doc.add(save);

    add(doc);
  }

  private void addSolverMenu() {
    JMenu solver = new JMenu(MessageBundle.get("solver.name"));
    Action calc = PureCons.createAction("solver.calc", new SolverCalcAction(editor, editor), editor);
    Action settings = PureCons.createAction("solver.settings", new SolverSettingAction(), editor);
    solver.add(calc);
    solver.add(settings);
    add(solver);
  }

  private void addVertexCellEditMenu() {

  }
}