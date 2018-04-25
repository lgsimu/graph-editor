package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.graph.graph.GraphComponent;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.function.Supplier;

@SuppressWarnings({"WeakerAccess"})
public class DocumentSupport {
  @Contract(pure = true)
  public static @NotNull Document createDocument(@NotNull DocumentContext context, @NotNull Supplier<IApplicationAction> actionSupplier) {
    mxGraphComponent comp = new GraphComponent(new Graph());
    final Document document = new Document(context, comp, actionSupplier);
    comp.setColumnHeaderView(new DocumentRuler(comp, DocumentRuler.ORIENTATION_HORIZONTAL));
    comp.setRowHeaderView(new DocumentRuler(comp, DocumentRuler.ORIENTATION_VERTICAL));
    document.getGraphComponent().setMinimumSize(new Dimension(320, 320));
    return document;
  }

  @Contract(pure = true)
  public static @NotNull DocumentAcceleratorConsumer createKeyPressedConsumer(@NotNull Document document) {
    return new DocumentAcceleratorKeyPressedConsumer(document);
  }
}
