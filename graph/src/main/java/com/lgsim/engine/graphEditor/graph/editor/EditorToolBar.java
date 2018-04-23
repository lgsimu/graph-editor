package com.lgsim.engine.graphEditor.graph.editor;

import javax.swing.*;

class EditorToolBar extends JToolBar
{
  EditorToolBar(Editor editor, int orientation)
  {
    super(orientation);
    setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3),
                                                 getBorder()));
    setFloatable(false);
  }
}
