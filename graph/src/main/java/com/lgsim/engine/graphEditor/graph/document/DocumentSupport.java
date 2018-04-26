package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.graph.action.ApplicationActionImpl;
import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.graph.graph.GraphComponent;
import com.mxgraph.swing.mxGraphComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@SuppressWarnings({"WeakerAccess"})
public class DocumentSupport {

  @Contract(pure = true)
  public static @NotNull Document createDocument(@NotNull DocumentContext context, @NotNull IApplication application) {
    mxGraphComponent comp = new GraphComponent(new Graph());
    final Document document = new Document(context, comp);
    comp.setColumnHeaderView(new DocumentRuler(comp, DocumentRuler.ORIENTATION_HORIZONTAL));
    comp.setRowHeaderView(new DocumentRuler(comp, DocumentRuler.ORIENTATION_VERTICAL));
    document.getSwingComponent().setMinimumSize(new Dimension(320, 320));
    ApplicationActionImpl action = new ApplicationActionImpl(document);
    application.setApplicationAction(action);
    return document;
  }


  @Contract(pure = true)
  public static @NotNull DocumentAcceleratorConsumer createKeyPressedConsumer(@NotNull Document document) {
    return new DocumentAcceleratorKeyPressedConsumer(document);
  }
}
