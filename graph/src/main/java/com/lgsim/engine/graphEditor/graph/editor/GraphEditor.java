package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocument;
import com.lgsim.engine.graphEditor.api.graph.IGraphEditor;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.lgsim.engine.graphEditor.graph.util.MessageBundleUtil;
import com.lgsim.engine.graphEditor.util.ResourceUtil;
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
import java.util.List;
import java.util.Vector;

public class GraphEditor extends JPanel implements IGraphEditor
{
  private static final Logger log = LoggerFactory.getLogger(GraphEditor.class);
  private static final int defaultDividerSize = 1;
  private final mxGraphOutline graphOutline = new mxGraphOutline(null);
  private final JTabbedPane libraryPane = new JTabbedPane();
  private final EditorStatusBar statusBar = new EditorStatusBar(MessageBundleUtil.get("ready"))
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
  private final EditorPalette predefinedPalette = addPalette(MessageBundleUtil.get("predefined"));
  private final EditorPalette userDefinedPalette = addPalette(MessageBundleUtil.get("userDefined"));
  private List<GraphDocument> graphDocuments = new Vector<>();
  private transient int currentDocumentIndex;


  public GraphEditor()
  {
    loadStencils();
    loadDocuments();
    initUIComponents();
    installToolBar();
    installPaletteListeners();
    installOutlineListeners();
    installGraphComponentListeners();
  }


  @NotNull
  EditorPalette addPalette(@NotNull String title)
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
    addStencils(userDefinedPalette, userDefinedStencils);
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
        String msg = MessageBundleUtil.get("scale", scale);
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
            IVertex vertex = (IVertex) cell.getValue();
            renderVertexTable(vertex);
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
    final mxGraphComponent comp = new EditorGraphComponent(new EditorGraph());
    graphDocuments.add(currentDocumentIndex, new GraphDocument(null, comp));
  }


  private void openLastDocuments()
  {

  }


  @Nullable
  private List<GraphDocument> getLastDocuments()
  {
    return null;
  }


  mxGraphOutline getGraphOutline()
  {
    return graphOutline;
  }


  @NotNull
  GraphDocument getCurrentDocument()
  {
    return graphDocuments.get(currentDocumentIndex);
  }


  Action bind(String name, final Action action)
  {
    return bind(name, action, null);
  }


  Action bind(String name, final Action action, String iconUrl)
  {
    final mxGraphComponent graphComp = getCurrentDocument().getGraphComponent();
    Icon icon = ResourceUtil.lookupImageIcon(iconUrl);
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
  mxGraphComponent getGraphComponent()
  {
    return getCurrentDocument().getGraphComponent();
  }


  @Override
  public IGraphDocument getCurrentGraphDocument()
  {
    return null;
  }


  @Override
  public List<IGraphDocument> getOpenedGraphDocuments()
  {
    return null;
  }


  @Override
  public IGraphDocument openGraphDocument(@NotNull File file)
  {
    return null;
  }


  @Override
  public void saveGraphDocument(@NotNull IGraphDocument document, @NotNull File file)
  {

  }
}
