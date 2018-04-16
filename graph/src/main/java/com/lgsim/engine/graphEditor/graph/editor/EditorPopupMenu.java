package com.lgsim.engine.graphEditor.graph.editor;

import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxResources;

import javax.swing.*;

import static com.lgsim.engine.graphEditor.graph.editor.EditorActions.HistoryAction;

class EditorPopupMenu extends JPopupMenu
{
  /**
   *
   */
  private static final long serialVersionUID = -3132749140550242191L;


  public EditorPopupMenu(GraphEditor editor)
  {
    boolean selected = !editor.getGraphComponent().getGraph()
                              .isSelectionEmpty();

    add(editor.bind(mxResources.get("undo"), new HistoryAction(true),
                    "com/lgsim/engine/graphEditor/graph/images/undo.gif"));

    addSeparator();

    add(
        editor.bind(mxResources.get("cut"), TransferHandler
                        .getCutAction(),
                    "com/lgsim/engine/graphEditor/graph/images/cut.gif"))
        .setEnabled(selected);
    add(
        editor.bind(mxResources.get("copy"), TransferHandler
                        .getCopyAction(),
                    "com/lgsim/engine/graphEditor/graph/images/copy.gif"))
        .setEnabled(selected);
    add(editor.bind(mxResources.get("paste"), TransferHandler
                        .getPasteAction(),
                    "com/lgsim/engine/graphEditor/graph/images/paste.gif"));

    addSeparator();

    add(
        editor.bind(mxResources.get("delete"), mxGraphActions
                        .getDeleteAction(),
                    "com/lgsim/engine/graphEditor/graph/images/delete.gif"))
        .setEnabled(selected);

    addSeparator();

    // Creates the format menu
    JMenu menu = (JMenu) add(new JMenu(mxResources.get("format")));

    EditorMenuBar.populateFormatMenu(menu, editor);

    // Creates the shape menu
    menu = (JMenu) add(new JMenu(mxResources.get("shape")));

    EditorMenuBar.populateShapeMenu(menu, editor);

    addSeparator();

    add(
        editor.bind(mxResources.get("edit"), mxGraphActions
            .getEditAction())).setEnabled(selected);

    addSeparator();

    add(editor.bind(mxResources.get("selectVertices"), mxGraphActions
        .getSelectVerticesAction()));
    add(editor.bind(mxResources.get("selectEdges"), mxGraphActions
        .getSelectEdgesAction()));

    addSeparator();

    add(editor.bind(mxResources.get("selectAll"), mxGraphActions
        .getSelectAllAction()));
  }
}
