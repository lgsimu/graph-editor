package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.graph.Editor;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxGraphSelectionModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.function.Function;

@SuppressWarnings({"WeakerAccess"})
public class DocumentSupport {

  private static final Logger log = LoggerFactory.getLogger("graph.DocumentSupport");


  @Contract(pure = true)
  public static @NotNull DocumentAcceleratorConsumer createKeyPressedConsumer(@NotNull Document document) {
    return new DocumentAcceleratorKeyPressedConsumer(document);
  }


  public static void installOutlineListeners(@NotNull Document document)
  {
    final mxGraphOutline graphOutline = document.getEditor().getGraphOutline();
    final mxGraphComponent comp = document.getSwingComponent();
    graphOutline.setGraphComponent(comp);
    graphOutline.addMouseWheelListener(e -> {
      if (e.getSource() instanceof mxGraphOutline || e.isControlDown()) {
        if (e.getWheelRotation() < 0) {
          comp.zoomIn();
        }
        else {
          comp.zoomOut();
        }
        int scale = (int) (100 * comp.getGraph().getView().getScale());
        String msg = MessageBundle.get("graphDocument.graph.scale", scale);
        log.info(msg);
        // TODO notify application
      }
    });
  }


  @Contract(pure = true)
  public static void installGraphDocumentListeners(@NotNull Document document)
  {
    final Editor editor = document.getEditor();
    mxGraphComponent comp = document.getSwingComponent();

    new mxRubberband(comp);
    new DocumentAccelerator(document);

    comp.getGraph().getSelectionModel().addListener(mxEvent.CHANGE, (sender, evt) -> {
      if ((sender instanceof mxGraphSelectionModel)) {
        mxGraphSelectionModel selectionModel = (mxGraphSelectionModel) sender;
        Object[] cells = selectionModel.getCells();
        if (cells != null) {
          if (cells.length == 1) {
            mxCell cell = (mxCell) cells[0];
            log.debug("select cell {} value is {}", cell, cell.getValue());
            if (cell.getValue() instanceof IVertex) {
              IVertex vertex = (IVertex) cell.getValue();
              final IGraph graph = document.getGraph();
              IVertex peer = lookupPeerVertex(vertex, graph);
              if (peer != null) {
                vertex.setOutputs(peer.getOutputs());
              }
              renderVertexTable(vertex, editor.getVertexTable());
            }
            log.debug("load cell data to table");
          }
        }
      }
    });

    comp.getGraph().getModel().addListener(mxEvent.CHANGE, (sender, evt) -> {
      document.setModified(true);
      editor.getDocTabbedPane().setTitleAt(editor.getCurrentDocumentIndex(), document.getTitle());
      log.debug("document {} changed", editor.getCurrentDocumentIndex());
    });
  }


  private static @Nullable IVertex lookupPeerVertex(@NotNull IVertex vertex, @NotNull IGraph graph)
  {
    Collection<IVertex> vertexes = graph.getAllVertexes();
    for (IVertex v : vertexes) {
      if (v.getID().equals(vertex.getID())) {
        return v;
      }
    }
    return null;
  }


  private static void renderVertexTable(@NotNull IVertex vertex, @NotNull IVertexTable table)
  {
    log.debug("render vertex {}", vertex);
    Function<IVertex, Boolean> isValid = (v) -> {
      // TODO: to be complete
      return true;
    };
    if (isValid.apply(vertex)) {
      table.render(vertex);
    }
  }
}
