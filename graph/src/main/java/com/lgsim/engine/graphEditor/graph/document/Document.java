package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphDocumentImpl;
import com.lgsim.engine.graphEditor.api.widget.IApplicationWidget;
import com.lgsim.engine.graphEditor.graph.Editor;
import com.lgsim.engine.graphEditor.graph.action.ApplicationActionImpl;
import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.graph.graph.GraphComponent;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxGraphSelectionModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.function.Function;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Document extends GraphDocumentImpl implements IApplicationWidget {

  private static final Logger log = LoggerFactory.getLogger("graph.document.Document");

  private Editor editor;
  private IApplication application;
  private GraphComponent swingComponent;
  private mxGraphOutline graphOutline;
  private JTabbedPane docTabbedPane;


  public Document() {
  }


  public Document(@NotNull Editor editor)
  {
    this.editor = editor;
    this.application = editor.getApplication();
    this.swingComponent = new GraphComponent(new Graph());
    this.graphOutline = editor.getGraphOutline();
    this.docTabbedPane = editor.getDocTabbedPane();
    settingGraphComponent();
  }


  private void settingGraphComponent() {
    swingComponent.setMinimumSize(new Dimension(320, 320));
    swingComponent.setColumnHeaderView(new DocumentRuler(swingComponent, DocumentRuler.ORIENTATION_HORIZONTAL));
    swingComponent.setRowHeaderView(new DocumentRuler(swingComponent, DocumentRuler.ORIENTATION_VERTICAL));
    application.setApplicationAction(new ApplicationActionImpl(this));
  }


  private void installOutlineListeners(@NotNull Document document)
  {
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


  private void installGraphDocumentListeners(@NotNull Document document)
  {
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
              final IGraph graph = getGraph();
              IVertex peer = lookupPeerVertex(vertex, graph);
              if (peer != null) {
                vertex.setOutputs(peer.getOutputs());
              }
              renderVertexTable(vertex);
            }
            log.debug("load cell data to table");
          }
        }
      }
    });

    comp.getGraph().getModel().addListener(mxEvent.CHANGE, (sender, evt) -> {
      document.setModified(true);
      docTabbedPane.setTitleAt(editor.getCurrentDocumentIndex(), document.getTitle());
      log.debug("document {} changed", editor.getCurrentDocumentIndex());
    });
  }


  private @Nullable IVertex lookupPeerVertex(@NotNull IVertex vertex, @NotNull IGraph graph)
  {
    Collection<IVertex> vertexes = graph.getAllVertexes();
    for (IVertex v : vertexes) {
      if (v.getID().equals(vertex.getID())) {
        return v;
      }
    }
    return null;
  }


  private void renderVertexTable(@NotNull IVertex vertex)
  {
    log.debug("render vertex {}", vertex);
    Function<IVertex, Boolean> isValid = (v) -> {
      // TODO: to be complete
      return true;
    };
    if (isValid.apply(vertex)) {
      editor.getVertexTable().render(vertex);
    }
  }


  @Override
  public @NotNull String getTitle()
  {
    return MessageBundle.get("graphDocument.newDocumentTitle") + (isModified() ? "*" : "");
  }


  @Override
  public @NotNull mxGraphComponent getSwingComponent()
  {
    return swingComponent;
  }


  public void setSwingComponent(GraphComponent swingComponent) {
    this.swingComponent = swingComponent;
  }


  public @NotNull IApplicationAction getApplicationAction() {
    return getApplication().getApplicationAction();
  }


  @Override
  public @NotNull IApplication getApplication() {
    return application;
  }


  @Override
  public void setApplication(@NotNull IApplication application) {
    this.application = application;
  }


  public void output() throws IOException {
    editor.getDocumentContext().put(this);
  }


  public Editor getEditor() {
    return editor;
  }


  public void setEditor(Editor editor) {
    this.editor = editor;
  }
}
