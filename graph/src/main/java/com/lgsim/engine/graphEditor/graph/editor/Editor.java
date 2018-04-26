package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.MessageBundle;
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
import com.lgsim.engine.graphEditor.graph.document.Document;
import com.lgsim.engine.graphEditor.graph.document.DocumentButtonTab;
import com.lgsim.engine.graphEditor.graph.document.DocumentContext;
import com.lgsim.engine.graphEditor.util.StringUtil;
import com.mxgraph.swing.mxGraphOutline;
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
import java.util.List;
import java.util.Vector;
import java.util.function.BiConsumer;

@SuppressWarnings("WeakerAccess")
public class Editor extends JPanel implements IGraphEditor, ISolverEnvironment {

  private static final Logger log = LoggerFactory.getLogger(Editor.class);
  private final IApplication application;
  private final mxGraphOutline graphOutline = new mxGraphOutline(null);
  private final JTabbedPane libraryPane = new JTabbedPane();
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
//    add(statusBar, BorderLayout.SOUTH);

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
    Document document = new Document(this);
    this.addDocument(document);
  }


  private void addDocument(@NotNull Document document) {
    docTabbedPane.add(document.getTitle(), document.getSwingComponent());
    docTabbedPane.setTabComponentAt(currentDocumentIndex, new DocumentButtonTab(docTabbedPane));
    currentDocumentIndex = documents.size();
    documents.add(currentDocumentIndex, document);
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


  public @NotNull mxGraphOutline getGraphOutline() {
    return graphOutline;
  }


  public @NotNull IApplication getApplication() {
    return application;
  }


  public @NotNull JTabbedPane getDocTabbedPane() {
    return docTabbedPane;
  }


  public int getCurrentDocumentIndex() {
    return currentDocumentIndex;
  }


  public IVertexTable getVertexTable() {
    return vertexTable;
  }
}
