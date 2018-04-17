package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.graph.IGraphDocument;
import com.lgsim.engine.graphEditor.api.graph.IGraphEditor;
import com.lgsim.engine.graphEditor.graph.ApplicationContext;
import com.lgsim.engine.graphEditor.graph.component.*;
import com.lgsim.engine.graphEditor.graph.util.IOUtil;
import com.lgsim.engine.graphEditor.graph.util.IconUtil;
import com.lgsim.engine.graphEditor.graph.util.MessageBundleUtil;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphSelectionModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

public class GraphEditor extends JPanel implements IGraphEditor
{
  private static final long serialVersionUID = -3839357795020116834L;
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
  private final InstanceComponentParameterEditor parameterEditor = new InstanceComponentParameterEditor();
  private final JTabbedPane docTabbedPane = new JTabbedPane();
  private final JSplitPane westPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, libraryPane, graphOutline);
  private final JSplitPane centerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPane, docTabbedPane);
  private final JSplitPane eastPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPane, parameterEditor);
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
  public EditorPalette addPalette(@NotNull String title)
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
    final StencilComponentContext context = ApplicationContext.getInstance().getStencilComponentContext();
    List<StencilComponent> predefinedComponents = context.loadPredefinedComponents();
    if (predefinedComponents != null)
    {
      for (StencilComponent stencil : predefinedComponents)
      {
        predefinedPalette.addStencil(stencil);
      }
    }
    List<StencilComponent> userDefinedComponents = context.loadUserDefinedComponents();
    if (userDefinedComponents != null)
    {
      for (StencilComponent stencil : userDefinedComponents)
      {
        userDefinedPalette.addStencil(stencil);
      }
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
        new Dimension((int) parameterEditor.getTable().getPreferredSize().getWidth(), 320)
    );
    eastPane.getRightComponent().setPreferredSize(parameterEditor.getTable().getPreferredSize());

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
      if ((sender != null) && (sender instanceof mxGraphSelectionModel))
      {
        mxGraphSelectionModel selectionModel = (mxGraphSelectionModel) sender;
        Object[] cells = selectionModel.getCells();
        if (cells != null)
        {
          if (cells.length == 1)
          {
            mxCell cell = (mxCell) cells[0];
            log.debug("select cell {} value is {}", cell, cell.getValue());
            InstanceComponentTable table = parameterEditor.getTable();
            loadTableData(table, cell);
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


  private void loadTableData(@NotNull InstanceComponentTable table, @NotNull mxCell vertex)
  {
    final Object value = vertex.getValue();
    if (value instanceof StencilComponent)
    {
      StencilComponent component = (StencilComponent) value;
      final List<InstanceComponentArgument> arguments = component.getArguments();
      Object[][] data = new Object[arguments.size()][4];
      for (int i = 0; i < arguments.size(); i++)
      {
        final InstanceComponentArgument argument = arguments.get(i);
        String name = argument.getName();
        BigDecimal val = argument.getNumericalValue();
        String unit = "mm"; // TODO
        String description = argument.getDescription();
        data[i][0] = name;
        data[i][1] = val;
        data[i][2] = unit;
        data[i][3] = description;
      }
      ((InstanceComponentTableModel) table.getModel()).setData(data);
    }
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
    final mxGraphComponent comp = new mxGraphComponent(new mxGraph()
    {
      {
        setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
      }

      @Override
      public String getToolTipForCell(Object cell)
      {
        return "";
      }


      @Override
      public Object createEdge(Object parent, String id, Object value, Object source, Object target, String style)
      {
        log.debug("create edge");
        mxCell edge = new mxCell(value, new mxGeometry(), style);

        edge.setId(id);
        edge.setEdge(true);
        edge.getGeometry().setRelative(true);

        return edge;
      }


      @Override
      public boolean isCellSelectable(Object cell)
      {
        return !model.isEdge(cell);
      }
    })
    {
      {
        setPageVisible(true);
        setGridVisible(true);
        setToolTips(true);
        getConnectionHandler().setCreateTarget(true);
        mxCodec codec = new mxCodec();
        Document doc = mxUtils.loadDocument(IOUtil.getResourceURI("com/lgsim/engine/graphEditor/graph/default-style.xml"));
        codec.decode(doc.getDocumentElement(), graph.getStylesheet());
        getViewport().setOpaque(true);
        getViewport().setBackground(Color.WHITE);
        graph.setCellsResizable(false);
        graph.setCellsEditable(false);
      }

      @Override
      public Object[] importCells(Object[] cells, double dx, double dy, Object target, Point location)
      {
        if (target == null && cells.length == 1 && location != null)
        {
          target = getCellAt(location.x, location.y);
          if (target instanceof mxICell && cells[0] instanceof mxICell)
          {
            mxICell targetCell = (mxICell) target;
            mxICell dropCell = (mxICell) cells[0];
            if (targetCell.isVertex() == dropCell.isVertex()
                || targetCell.isEdge() == dropCell.isEdge())
            {
              mxIGraphModel model = graph.getModel();
              model.setStyle(target, model.getStyle(cells[0]));
              graph.setSelectionCell(target);
              return null;
            }
          }
        }
        return super.importCells(cells, dx, dy, target, location);
      }


      @Override
      public mxInteractiveCanvas createCanvas()
      {
        return new GraphCanvas();
      }
    };
    currentDocumentIndex = graphDocuments.size();
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


  public mxGraphOutline getGraphOutline()
  {
    return graphOutline;
  }


  @NotNull
  public GraphDocument getCurrentDocument()
  {
    return graphDocuments.get(currentDocumentIndex);
  }


  public Action bind(String name, final Action action)
  {
    return bind(name, action, null);
  }


  public Action bind(String name, final Action action, String iconUrl)
  {
    final mxGraphComponent graphComp = getCurrentDocument().getGraphComponent();
    Icon icon = (iconUrl != null) ? IconUtil.getIcon(iconUrl) : null;
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
  public mxGraphComponent getGraphComponent()
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
