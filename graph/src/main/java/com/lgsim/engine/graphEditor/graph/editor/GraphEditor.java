package com.lgsim.engine.graphEditor.graph.editor;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.IconBundle;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocument;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocumentSpec;
import com.lgsim.engine.graphEditor.api.graph.IGraphEditor;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphStyleCodecImpl;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.lgsim.engine.graphEditor.util.JarUtil;
import com.lgsim.engine.graphEditor.util.StringUtil;
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class GraphEditor extends JPanel implements IGraphEditor, ISolverEnvironment {
  private static final Logger log = LoggerFactory.getLogger(GraphEditor.class);
  private final IGraphDocumentSpec spec;
  private final mxGraphOutline graphOutline = new mxGraphOutline(null);
  private final JTabbedPane libraryPane = new JTabbedPane();
  private final EditorStatusBar statusBar = new EditorStatusBar(IApplication.statusText) {
    {
      setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
    }
  };
  private final IVertexTable vertexTable = ImplementationContext.INSTANCE.getVertexTable();
  private final JTabbedPane docTabbedPane = new JTabbedPane();
  private final JSplitPane westPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, libraryPane, graphOutline);
  private final JSplitPane centerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPane, docTabbedPane);
  private final JSplitPane eastPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPane, vertexTable.getSwingComponent());
  private final StencilPalette predefinedPalette = Builder.createStencilPalette();
  private final StencilPalette userDefinedPalette = Builder.createStencilPalette();
  private List<GraphDocument> graphDocuments = new Vector<>();
  private transient int currentDocumentIndex;

  public GraphEditor(@NotNull IGraphDocumentSpec spec)
  {
    this.spec = spec;
    initUIComponents();
    loadStencils();
    loadDocuments();
  }

  public IGraphDocumentSpec getSpec()
  {
    return spec;
  }

  private void initUIComponents()
  {
    /* install toolbar */
    add(new EditorToolBar(this, JToolBar.HORIZONTAL), BorderLayout.NORTH);

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

    libraryPane.setMinimumSize(new Dimension(0, 0));
    graphOutline.setMinimumSize(new Dimension(0, 0));

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
    // TODO: uncomment
//    addStencils.accept(userDefinedPalette, userDefinedStencils);
  }

  private void loadDocuments()
  {
    final List<GraphDocument> documents = getLastDocuments();
    if (documents == null) {
      GraphDocument document = openNewDocument();
      currentDocumentIndex = graphDocuments.size();
      graphDocuments.add(currentDocumentIndex, document);
    } else {
      openLastDocuments();
    }
  }

  @Contract(pure = true)
  private GraphDocument openNewDocument()
  {
    mxGraphComponent comp = new EditorGraphComponent(new EditorGraph());
    final GraphDocument document = new GraphDocument(comp);
    comp.setColumnHeaderView(new GraphDocumentRuler(comp, GraphDocumentRuler.ORIENTATION_HORIZONTAL));
    comp.setRowHeaderView(new GraphDocumentRuler(comp, GraphDocumentRuler.ORIENTATION_VERTICAL));
    installOutlineListeners(comp);
    installGraphDocumentListeners(document);
    return document;
  }

  private void installOutlineListeners(@NotNull mxGraphComponent comp)
  {
    graphOutline.setGraphComponent(comp);
    graphOutline.addMouseWheelListener(e -> {
      if (e.getSource() instanceof mxGraphOutline || e.isControlDown()) {
        if (e.getWheelRotation() < 0) {
          comp.zoomIn();
        } else {
          comp.zoomOut();
        }
        int scale = (int) (100 * comp.getGraph().getView().getScale());
        String msg = MessageBundle.get("scale", scale);
        status(msg);
      }
    });
  }

  private void installGraphDocumentListeners(@NotNull GraphDocument document)
  {
    mxGraphComponent comp = document.getGraphComponent();

    new mxRubberband(comp);

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
  private List<GraphDocument> getLastDocuments()
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
      return false;
    };
    if (isValid.apply(vertex)) {
      vertexTable.render(vertex);
    }
  }

  private void status(String msg)
  {
    statusBar.setText(msg);
  }

  Action bind(@NotNull String bundle, @NotNull Action action)
  {
    String name = MessageBundle.get(bundle);
    Icon icon = IconBundle.get(bundle);
    final mxGraphComponent graphComp = getCurrentGraphDocument().getGraphComponent();
    AbstractAction newAction = new AbstractAction(name, icon) {
      public void actionPerformed(ActionEvent e)
      {
        action.actionPerformed(new ActionEvent(graphComp, e.getID(), e.getActionCommand()));
      }
    };

    newAction.putValue(Action.SHORT_DESCRIPTION, action.getValue(Action.SHORT_DESCRIPTION));

    return newAction;
  }

  @Override
  public @Nullable GraphDocument getCurrentGraphDocument()
  {
    if (graphDocuments.size() > 0) {
      return graphDocuments.get(currentDocumentIndex);
    } else {
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
    final File docFile = document.getGraphDocumentFile().getEntryFile();
    if (docFile.exists()) {
      updateDocumentJarFile(document);
    } else {
      File workDir = Files.createTempDir();
      File jarFile = createDocumentJarFile(document, workDir);
      Files.copy(jarFile, docFile);
      if (workDir.delete()) {
        log.debug("delete temp work dir {}", workDir);
      }
    }
  }

  private @NotNull File createDocumentJarFile(@NotNull IGraphDocument document, @NotNull File workDir) throws IOException
  {
    log.debug("create document jar file");
    File temp = new File(workDir, "tmp");
    File jarFile = new File(workDir, document.getTitle() + ".jar");
    createDocumentModelFile(document, temp);
    createDocumentStyleFile(document, temp);
    Manifest manifestFile = createManifest();
    JarUtil.pack(temp, jarFile, manifestFile);
    return jarFile;
  }

  private void createDocumentModelFile(@NotNull IGraphDocument document, @NotNull File workDir) throws IOException
  {
    byte[] data = ImplementationContext.INSTANCE.getGraphCodec().encode(document.getGraph());
    File file = new File(workDir, "model");
    writeToFile(data, file);
  }

  private void createDocumentStyleFile(@NotNull IGraphDocument document, @NotNull File workDir) throws IOException
  {
    GraphStyleCodecImpl codec = new GraphStyleCodecImpl();
    byte[] data = codec.encode(document.getGraphStyle());
    File file = new File(workDir, "style");
    writeToFile(data, file);
  }

  private void writeToFile(byte[] data, @NotNull File file) throws IOException
  {
    Files.write(data, file);
  }

  private @NotNull Manifest createManifest()
  {
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_TITLE, spec.getImplementationTitle());
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VERSION, spec.getImplementationVersion());
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VENDOR, spec.getImplementationVendor());
    return manifest;
  }

  private void updateDocumentJarFile(@NotNull IGraphDocument document)
  {
    log.debug("update document jar file, {}", document);
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
    } else {
      return document.getGraph();
    }
  }

  @Override
  public @NotNull String getCaseName()
  {
    final IGraphDocument document = getCurrentGraphDocument();
    if (document == null) {
      return StringUtil.emptyString();
    } else {
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
