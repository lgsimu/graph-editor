package com.lgsim.engine.graphEditor.graph.editor;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.MessageBundle;
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
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class GraphEditor extends JPanel implements IGraphEditor
{
  private static final Logger log = LoggerFactory.getLogger(GraphEditor.class);
  private static final String modelFileName = "model";
  private static final String styleFileName = "style";
  private static final int defaultDividerSize = 1;
  private final IGraphDocumentSpec spec;
  private final mxGraphOutline graphOutline = new mxGraphOutline(null);
  private final JTabbedPane libraryPane = new JTabbedPane();
  private final EditorStatusBar statusBar = new EditorStatusBar(MessageBundle.get("ready"))
  {
    {
      setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
    }
  };
  private final IVertexTable vertexTable = ImplementationContext.INSTANCE.getVertexTable();
  private final JTabbedPane docTabbedPane = new JTabbedPane();
  private final JSplitPane westPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, libraryPane, graphOutline);
  private final JSplitPane centerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPane, docTabbedPane);
  private final JSplitPane eastPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPane, vertexTable.getSwingComponent());
  private final EditorPalette predefinedPalette = addPalette(MessageBundle.get("predefined"));
  private final EditorPalette userDefinedPalette = addPalette(MessageBundle.get("userDefined"));
  private List<GraphDocument> graphDocuments = new Vector<>();
  private transient int currentDocumentIndex;


  public GraphEditor(@NotNull IGraphDocumentSpec spec)
  {
    this.spec = spec;
    loadStencils();
    loadDocuments();
    initUIComponents();
    installToolBar();
    installPaletteListeners();
    installOutlineListeners();
    installGraphComponentListeners();
  }


  public IGraphDocumentSpec getSpec()
  {
    return spec;
  }


  @NotNull
  private EditorPalette addPalette(@NotNull String title)
  {
    final EditorPalette palette = new EditorPalette();
    final JScrollPane scrollPane = new JScrollPane(palette);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    libraryPane.add(title, scrollPane);
    libraryPane.addComponentListener(new ComponentAdapter()
    {
      public void componentResized(ComponentEvent e)
      {
        int w = scrollPane.getWidth() - scrollPane.getVerticalScrollBar().getWidth();
        palette.setPreferredWidth(w);
      }
    });
    return palette;
  }


  private void loadStencils()
  {
    final IStencilContext context = ImplementationContext.INSTANCE.getStencilContext();
    final List<IVertexStencil> predefinedStencils = context.getPredefinedStencils();
    final List<IVertexStencil> userDefinedStencils = context.getUserDefinedStencils();
    addStencils(predefinedPalette, predefinedStencils);
    // TODO: uncomment
//    addStencils(userDefinedPalette, userDefinedStencils);
  }


  private void addStencils(EditorPalette palette, List<IVertexStencil> stencils)
  {
    for (IVertexStencil stencil : stencils)
    {
      palette.addStencil(stencil);
    }
  }


  private void initUIComponents()
  {
    setLayout(new BorderLayout());

    westPane.setContinuousLayout(true);
    centerPane.setContinuousLayout(true);
    eastPane.setContinuousLayout(true);

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

    for (GraphDocument doc : graphDocuments)
    {
      docTabbedPane.add(doc.getTitle(), doc.getGraphComponent());
      docTabbedPane.setTabComponentAt(currentDocumentIndex, new ButtonTabComponent(docTabbedPane));
      doc.getGraphComponent().setMinimumSize(new Dimension(0, 0));
    }

    final mxGraphComponent comp = getGraphComponent();
    comp.setColumnHeaderView(new EditorRuler(comp, EditorRuler.ORIENTATION_HORIZONTAL));
    comp.setRowHeaderView(new EditorRuler(comp, EditorRuler.ORIENTATION_VERTICAL));

    graphOutline.setGraphComponent(getGraphComponent());

    /* eastPane is outer-most split pane */
    add(eastPane, BorderLayout.CENTER);
    add(statusBar, BorderLayout.SOUTH);
  }


  private void installToolBar()
  {
    add(new EditorToolBar(this, JToolBar.HORIZONTAL), BorderLayout.NORTH);
  }


  private void installPaletteListeners()
  {
    predefinedPalette.addListener(mxEvent.SELECT, (sender, evt) -> log.debug("palette select event fired"));
  }


  private void installOutlineListeners()
  {
    final mxGraphComponent comp = getGraphComponent();
    graphOutline.addMouseWheelListener(e -> {
      if (e.getSource() instanceof mxGraphOutline || e.isControlDown())
      {
        if (e.getWheelRotation() < 0)
        {
          comp.zoomIn();
        }
        else
        {
          comp.zoomOut();
        }
        int scale = (int) (100 * comp.getGraph().getView().getScale());
        String msg = MessageBundle.get("scale", scale);
        status(msg);
      }
    });
  }


  private void installGraphComponentListeners()
  {
    new mxRubberband(getGraphComponent());
    getGraphComponent().getGraph().getSelectionModel().addListener(mxEvent.CHANGE, (sender, evt) -> {
      if ((sender instanceof mxGraphSelectionModel))
      {
        mxGraphSelectionModel selectionModel = (mxGraphSelectionModel) sender;
        Object[] cells = selectionModel.getCells();
        if (cells != null)
        {
          if (cells.length == 1)
          {
            mxCell cell = (mxCell) cells[0];
            log.debug("select cell {} value is {}", cell, cell.getValue());
            if (cell.getValue() instanceof IVertex)
            {
              IVertex vertex = (IVertex) cell.getValue();
              renderVertexTable(vertex);
            }
            log.debug("load cell data to table");
          }
        }
      }
    });
    getGraphComponent().getGraph().getModel().addListener(mxEvent.CHANGE, (sender, evt) -> {
      getCurrentDocument().setModified(true);
      docTabbedPane.setTitleAt(currentDocumentIndex, getCurrentDocument().getTitle());
      log.debug("document {} changed", currentDocumentIndex);
    });
  }


  private void renderVertexTable(@NotNull IVertex vertex)
  {
    vertexTable.render(vertex);
  }


  private void status(String msg)
  {
    statusBar.setText(msg);
  }


  private void loadDocuments()
  {
    final List<GraphDocument> documents = getLastDocuments();
    if (documents == null)
    {
      openNewDocument();
    }
    else
    {
      openLastDocuments();
    }
  }


  private void openNewDocument()
  {
    currentDocumentIndex = graphDocuments.size();
    mxGraphComponent comp = new EditorGraphComponent(new EditorGraph());
    GraphDocument graphDocument = new GraphDocument(comp);
    graphDocuments.add(currentDocumentIndex, graphDocument);
  }


  private void openLastDocuments()
  {

  }


  @Nullable
  private List<GraphDocument> getLastDocuments()
  {
    return null;
  }


  @NotNull
  private GraphDocument getCurrentDocument()
  {
    return graphDocuments.get(currentDocumentIndex);
  }


  Action bind(@NotNull String name, @NotNull Action action, @NotNull Icon icon)
  {
    final mxGraphComponent graphComp = getCurrentDocument().getGraphComponent();
    AbstractAction newAction = new AbstractAction(name, icon)
    {
      public void actionPerformed(ActionEvent e)
      {
        action.actionPerformed(new ActionEvent(graphComp, e.getID(), e.getActionCommand()));
      }
    };

    newAction.putValue(Action.SHORT_DESCRIPTION, action.getValue(Action.SHORT_DESCRIPTION));

    return newAction;
  }


  @NotNull
  private mxGraphComponent getGraphComponent()
  {
    return getCurrentDocument().getGraphComponent();
  }


  @Override
  public IGraphDocument getCurrentGraphDocument()
  {
    return null;
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
    if (docFile.exists())
    {
      updateDocumentJarFile(document);
    }
    else
    {
      File workDir = Files.createTempDir();
      File jarFile = createDocumentJarFile(document, workDir);
      Files.copy(jarFile, docFile);
      if (workDir.delete())
      {
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
    File file = new File(workDir, modelFileName);
    writeToFile(data, file);
  }


  private void createDocumentStyleFile(@NotNull IGraphDocument document, @NotNull File workDir) throws IOException
  {
    GraphStyleCodecImpl codec = new GraphStyleCodecImpl();
    byte[] data = codec.encode(document.getGraphStyle());
    File file = new File(workDir, styleFileName);
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
    for (IGraphDocument document : documents)
    {
      saveOpenedGraphDocument(document);
    }
  }


  @Override
  public boolean isGraphDocumentFile(@NotNull File file)
  {
    return false;
  }
}
