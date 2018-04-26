package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocument;
import com.lgsim.engine.graphEditor.api.graph.IGraphEditor;
import com.lgsim.engine.graphEditor.api.widget.IApplicationToolbar;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.lgsim.engine.graphEditor.graph.PureCons;
import com.lgsim.engine.graphEditor.graph.action.ApplicationActionImpl;
import com.lgsim.engine.graphEditor.graph.document.*;
import com.lgsim.engine.graphEditor.util.StringUtil;
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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.function.BiConsumer;
import java.util.function.Function;

@SuppressWarnings("WeakerAccess")
public class Editor extends JPanel implements IGraphEditor, ISolverEnvironment {

  private static final Logger log = LoggerFactory.getLogger(Editor.class);
  private final IApplication application;
  private final mxGraphOutline graphOutline = new mxGraphOutline(null);
  private final JTabbedPane libraryPane = new JTabbedPane();
  private final StatusBar statusBar = new StatusBar(MessageBundle.get("application.status.ready")) {
    {
      setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
    }
  };
  private final IVertexTable vertexTable = ImplementationContext.INSTANCE.getVertexTable();
  private final JTabbedPane docTabbedPane = new JTabbedPane();
  private final JSplitPane westPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, libraryPane, graphOutline);
  private final JSplitPane centerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPane, docTabbedPane);
  private final JSplitPane eastPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPane, vertexTable.getSwingComponent());
  private final StencilPalette predefinedPalette = PureCons.createStencilPalette();
  private final StencilPalette userDefinedPalette = PureCons.createStencilPalette();
  private List<Document> documents = new Vector<>();
  private transient int currentDocumentIndex;
  private transient DocumentContext documentContext;
  private IApplicationToolbar applicationToolbar = ImplementationContext.INSTANCE.getApplicationToolbar();


  public Editor(@NotNull IApplication application)
  {
    this.application = application;
    this.documentContext = new DocumentContext(application);
    initUIComponents();
    loadStencils();
    loadDocuments();
  }


  private void initUIComponents()
  {
    JToolBar toolbar = applicationToolbar.getSwingComponent();
    toolbar.setOrientation(JToolBar.HORIZONTAL);
    add(toolbar, BorderLayout.NORTH);

    setLayout(new BorderLayout());

    westPane.setContinuousLayout(true);
    centerPane.setContinuousLayout(true);
    eastPane.setContinuousLayout(true);

    final int defaultDividerSize = 1;
    westPane.setDividerSize(defaultDividerSize);
    centerPane.setDividerSize(defaultDividerSize);
    eastPane.setDividerSize(defaultDividerSize);

    westPane.setBorder(null);
    centerPane.setBorder(null);
    eastPane.setBorder(null);
    libraryPane.setBorder(null);

    westPane.setResizeWeight(1);
    centerPane.setResizeWeight(0);
    eastPane.setResizeWeight(1);

    libraryPane.setMinimumSize(new Dimension(320, 0));
    graphOutline.setMinimumSize(new Dimension(320, 320));
    vertexTable.getSwingComponent().setMinimumSize(new Dimension(320, 0));

    westPane.getRightComponent().setPreferredSize(
        new Dimension((int) vertexTable.getSwingComponent().getPreferredSize().getWidth(), 320)
    );
    eastPane.getRightComponent().setPreferredSize(vertexTable.getSwingComponent().getPreferredSize());

    libraryPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    docTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    /* eastPane is outer-most split pane */
    add(eastPane, BorderLayout.CENTER);
    add(statusBar, BorderLayout.SOUTH);

    loadStencilPalettes();
  }


  private void loadStencilPalettes() {
    BiConsumer<StencilPalette, String> loadStencilPalette = (palette, title) -> {
      final JScrollPane scrollPane = new JScrollPane(palette);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      libraryPane.add(title, scrollPane);
      libraryPane.addComponentListener(new ComponentAdapter() {
        public void componentResized(ComponentEvent e)
        {
          int w = scrollPane.getWidth() - scrollPane.getVerticalScrollBar().getWidth();
          palette.setPreferredWidth(w);
        }
      });
    };
    loadStencilPalette.accept(predefinedPalette, MessageBundle.get("vertexStencil.predefined"));
    loadStencilPalette.accept(userDefinedPalette, MessageBundle.get("vertexStencil.userDefined"));
  }


  private void loadStencils()
  {
    final IStencilContext context = ImplementationContext.INSTANCE.getStencilContext();
    final List<IVertexStencil> predefinedStencils = context.getPredefinedStencils();
    final List<IVertexStencil> userDefinedStencils = context.getUserDefinedStencils();
    BiConsumer<StencilPalette, List<IVertexStencil>> addStencils = (palette, stencils) -> {
      for (IVertexStencil stencil : stencils) {
        palette.addStencil(stencil);
      }
    };

    addStencils.accept(predefinedPalette, predefinedStencils);
    log.debug("load {} predefined stencils", predefinedPalette.getLoadStencilCount());
    addStencils.accept(userDefinedPalette, userDefinedStencils);
    log.debug("load {} user defined stencils", userDefinedPalette.getLoadStencilCount());
  }


  private void loadDocuments()
  {
    final List<Document> documents = getLastDocuments();
    if (documents == null) {
      openNewDocument();
    }
    else {
      openLastDocuments();
    }
  }


  public void openNewDocument()
  {
    Document document = DocumentSupport.createDocument(documentContext, this::getApplicationAction);
    ApplicationActionImpl action = new ApplicationActionImpl(document);
    application.setApplicationAction(action);
    mxGraphComponent comp = document.getGraphComponent();
    docTabbedPane.add(document.getTitle(), comp);
    docTabbedPane.setTabComponentAt(currentDocumentIndex, new DocumentButtonTab(docTabbedPane));
    currentDocumentIndex = documents.size();
    documents.add(currentDocumentIndex, document);
    installOutlineListeners(comp);
    installGraphDocumentListeners(document);
    applicationToolbar.setApplicationAction(getApplicationAction());
  }


  public IApplicationAction getApplicationAction() {
    return application.getApplicationAction();
  }


  private void installOutlineListeners(@NotNull mxGraphComponent comp)
  {
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
        status(msg);
      }
    });
  }


  private void installGraphDocumentListeners(@NotNull Document document)
  {
    mxGraphComponent comp = document.getGraphComponent();

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
              if (graph != null) {
                IVertex peer = lookupPeerVertex(vertex, graph);
                if (peer != null) {
                  vertex.setOutputs(peer.getOutputs());
                }
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
      docTabbedPane.setTitleAt(currentDocumentIndex, document.getTitle());
      log.debug("document {} changed", currentDocumentIndex);
    });
  }


  private void openLastDocuments()
  {
    log.debug("load last documents");
  }


  @Nullable
  private List<Document> getLastDocuments()
  {
    return null;
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
      vertexTable.render(vertex);
    }
  }


  private void status(String msg)
  {
    statusBar.setText(msg);
  }


  @Override
  public @Nullable Document getCurrentGraphDocument()
  {
    if (documents.size() > 0) {
      return documents.get(currentDocumentIndex);
    }
    else {
      return null;
    }
  }


  @Override
  public @NotNull List<IGraphDocument> getOpenedGraphDocuments()
  {
    return new Vector<>();
  }


  @Override
  public IGraphDocument openGraphDocument(@NotNull File file)
  {
    return null;
  }


  @Override
  public void saveOpenedGraphDocument(@NotNull IGraphDocument document) throws IOException
  {
    if (document instanceof Document) {
      documentContext.put((Document) document);
    }
    else {
      log.debug("un-support document type");
    }
  }


  @Override
  public void saveOpenedGraphDocuments(@NotNull List<IGraphDocument> documents) throws IOException
  {
    for (IGraphDocument document : documents) {
      saveOpenedGraphDocument(document);
    }
  }


  @Override
  public boolean isGraphDocumentFile(@NotNull File file)
  {
    return false;
  }


  @Override
  public void renderVertex(@NotNull IVertex vertex)
  {
    vertexTable.render(vertex);
  }


  @Override
  public @Nullable IVertex getCurrentVertex()
  {
    // TODO: 获取当前节点
    return null;
  }


  @Override
  public @NotNull File getExecutableFile()
  {
    // TODO: 从设置面板获取
    return new File("C:\\lgsas\\LGSAS.exe");
  }


  @Override
  public @Nullable IGraph getGraph()
  {
    IGraphDocument document = getCurrentGraphDocument();
    if (document == null) {
      return null;
    }
    else {
      return document.getGraph();
    }
  }


  @Override
  public @NotNull String getCaseName()
  {
    final IGraphDocument document = getCurrentGraphDocument();
    if (document == null) {
      return StringUtil.emptyString();
    }
    else {
      return document.getTitle();
    }
  }


  @Override
  public @NotNull String getSolverCommandlineArguments()
  {
    // TODO: 从设置面板获取
    return "";
  }
}
