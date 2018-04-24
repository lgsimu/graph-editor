package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.graph.graph.GraphComponent;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@SuppressWarnings({"WeakerAccess"})
public class DocumentSupport {
  @Contract(pure = true)
  public static @NotNull GraphDocument createDocument() {
    mxGraphComponent comp = new GraphComponent(new Graph());
    final GraphDocument document = new GraphDocument(comp);
    comp.setColumnHeaderView(new GraphDocumentRuler(comp, GraphDocumentRuler.ORIENTATION_HORIZONTAL));
    comp.setRowHeaderView(new GraphDocumentRuler(comp, GraphDocumentRuler.ORIENTATION_VERTICAL));
    document.getGraphComponent().setMinimumSize(new Dimension(320, 320));
    return document;
  }
}
