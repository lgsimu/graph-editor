package com.lgsim.engine.graphEditor.graph.editor;

import com.mxgraph.swing.handler.mxCellHandler;
import com.mxgraph.swing.handler.mxGraphTransferHandler;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class JTableRenderer extends JComponent
{
  private static final long serialVersionUID = 2106746763664760745L;
  private static final String IMAGE_PATH = "com/lgsim/engine/graphEditor/graph/images/";
  private static JTableRenderer dragSource = null;
  private static int sourceRow = 0;
  private Object cell;
  private mxGraphComponent graphContainer;
  private mxGraph graph;
  public JTable table;


  JTableRenderer(final Object cell,
                 final mxGraphComponent graphContainer)
  {
    this.cell = cell;
    this.graphContainer = graphContainer;
    this.graph = graphContainer.getGraph();
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createCompoundBorder(ShadowBorder
                                                     .getSharedInstance(), BorderFactory
                                                     .createBevelBorder(BevelBorder.RAISED)));

    JPanel title = new JPanel();
    title.setBackground(new Color(149, 173, 239));
    title.setOpaque(true);
    title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
    title.setLayout(new BorderLayout());

    JLabel icon = new JLabel(new ImageIcon(JTableRenderer.class
                                               .getResource(IMAGE_PATH + "preferences.gif")));
    icon.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 1));
    title.add(icon, BorderLayout.WEST);

    JLabel label = new JLabel(String.valueOf(graph.getLabel(cell)));
    label.setForeground(Color.WHITE);
    label.setFont(title.getFont().deriveFont(Font.BOLD, 11));
    label.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 2));
    title.add(label, BorderLayout.CENTER);

    JPanel toolBar2 = new JPanel();
    toolBar2.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 2));
    toolBar2.setOpaque(false);
    JButton button = new JButton(new AbstractAction("", new ImageIcon(
        JTableRenderer.class.getResource(IMAGE_PATH + "minimize.gif")))
    {
      public void actionPerformed(ActionEvent e)
      {
        graph.foldCells(!graph.isCellCollapsed(cell), false,
                        new Object[]{cell});
        ((JButton) e.getSource())
            .setIcon(new ImageIcon(
                JTableRenderer.class
                    .getResource(IMAGE_PATH
                                 + ((graph.isCellCollapsed(cell)) ? "maximize.gif"
                                                                  : "minimize.gif"))));
      }
    });
    button.setPreferredSize(new Dimension(16, 16));
    button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    button.setToolTipText("Collapse/Expand");
    button.setOpaque(false);
    toolBar2.add(button);

    title.add(toolBar2, BorderLayout.EAST);
    add(title, BorderLayout.NORTH);

    table = new MyTable();
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    if (graph.getModel().getChildCount(cell) == 0)
    {
      scrollPane.getViewport().setBackground(Color.WHITE);
      setOpaque(true);
      add(scrollPane, BorderLayout.CENTER);
    }

    scrollPane.getVerticalScrollBar().addAdjustmentListener(
        e -> graphContainer.refresh());

    label = new JLabel(new ImageIcon(JTableRenderer.class
                                         .getResource(IMAGE_PATH + "resize.gif")));
    label.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(label, BorderLayout.EAST);

    add(panel, BorderLayout.SOUTH);

    ResizeHandler resizeHandler = new ResizeHandler();
    label.addMouseListener(resizeHandler);
    label.addMouseMotionListener(resizeHandler);

    setMinimumSize(new Dimension(20, 30));
  }


  public class ResizeHandler implements MouseListener, MouseMotionListener
  {
    int index;


    ResizeHandler()
    {
      this(7);
    }


    ResizeHandler(int index)
    {
      this.index = index;
    }


    public void mouseClicked(MouseEvent e)
    {

    }


    public void mouseEntered(MouseEvent e)
    {

    }


    public void mouseExited(MouseEvent e)
    {

    }


    public void mousePressed(MouseEvent e)
    {

      if (!graph.isCellSelected(cell))
      {
        graphContainer.selectCellForEvent(cell, e);
      }

      mxCellHandler handler = graphContainer.getSelectionCellsHandler().getHandler(
          cell);

      if (handler != null)
      {

        handler.start(SwingUtilities.convertMouseEvent((Component) e
                          .getSource(), e, graphContainer.getGraphControl()),
                      index);
        e.consume();
      }
    }


    public void mouseReleased(MouseEvent e)
    {
      graphContainer.getGraphControl().dispatchEvent(
          SwingUtilities.convertMouseEvent((Component) e.getSource(),
                                           e, graphContainer.getGraphControl()));
    }


    public void mouseDragged(MouseEvent e)
    {
      graphContainer.getGraphControl().dispatchEvent(
          SwingUtilities.convertMouseEvent((Component) e.getSource(),
                                           e, graphContainer.getGraphControl()));
    }


    public void mouseMoved(MouseEvent e)
    {

    }
  }


  public class MyTable extends JTable implements DropTargetListener
  {
    private static final long serialVersionUID = 5841175227984561071L;
    Object[][] data;
    String[] colNames = new String[]{"A", "B", "C", "D", "E"};


    MyTable()
    {
      data = new Object[30][5];
      for (int i = 0; i < 30; i++)
      {
        data[i][0] = Boolean.FALSE;
        data[i][1] = "Column " + i;
        data[i][2] = (Math.random() > 0.5) ? new ImageIcon(
            JTableRenderer.class.getResource(IMAGE_PATH
                                             + "preferences.gif")) : null;
        data[i][3] = (Math.random() > 0.5) ? new ImageIcon(
            JTableRenderer.class.getResource(IMAGE_PATH
                                             + "preferences.gif")) : null;
        data[i][4] = (Math.random() > 0.5) ? new ImageIcon(
            JTableRenderer.class.getResource(IMAGE_PATH
                                             + "preferences.gif")) : null;
      }
      setModel(createModel());
      setTableHeader(null);
      setAutoscrolls(true);
      setGridColor(Color.WHITE);
      TableColumn column = getColumnModel().getColumn(0);
      column.setMaxWidth(20);
      column = getColumnModel().getColumn(2);
      column.setMaxWidth(12);
      column = getColumnModel().getColumn(3);
      column.setMaxWidth(12);
      column = getColumnModel().getColumn(4);
      column.setMaxWidth(12);

      setTransferHandler(new TransferHandler()
      {
        @Override
        public int getSourceActions(JComponent c)
        {
          return COPY_OR_MOVE;
        }


        protected Transferable createTransferable(JComponent c)
        {
          sourceRow = getSelectedRow();
          dragSource = JTableRenderer.this;

          return new mxGraphTransferable(null, null, null);
        }
      });

      setDragEnabled(true);
      setDropTarget(new DropTarget(this,
                                   DnDConstants.ACTION_COPY_OR_MOVE,
                                   this));
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }


    public DropTarget getDropTarget()
    {
      if (!((mxGraphTransferHandler) graphContainer.getTransferHandler())
          .isLocalDrag())
      {
        return super.getDropTarget();
      }

      return null;
    }


    public void dragEnter(DropTargetDragEvent e)
    {

    }


    public void dragOver(DropTargetDragEvent e)
    {
      if (!((mxGraphTransferHandler) graphContainer.getTransferHandler())
          .isLocalDrag()
          && JTableRenderer.this != dragSource)
      {
        Point p = e.getLocation();
        int row = rowAtPoint(p);
        getSelectionModel().setSelectionInterval(row, row);
      }
    }


    public void dropActionChanged(DropTargetDragEvent dtde)
    {

    }


    public void drop(DropTargetDropEvent e)
    {
      if (dragSource != null)
      {
        e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
        Point p = e.getLocation();
        int targetRow = rowAtPoint(p);

        Object edge = graph.insertEdge(null, null, null,
                                       dragSource.cell, JTableRenderer.this.cell, "sourceRow="
                                                                                  + sourceRow + ";targetRow=" + targetRow);
        graph.setSelectionCell(edge);

        dragSource = null;
        e.dropComplete(true);
      }
      else
      {
        e.rejectDrop();
      }
    }


    public void dragExit(DropTargetEvent dte)
    {

    }


    TableModel createModel()
    {
      return new AbstractTableModel()
      {
        private static final long serialVersionUID = -3642207266816170738L;


        public int getColumnCount()
        {
          return colNames.length;
        }


        public int getRowCount()
        {
          return data.length;
        }


        public String getColumnName(int col)
        {
          return colNames[col];
        }


        public Object getValueAt(int row, int col)
        {
          return data[row][col];
        }


        public Class<?> getColumnClass(int c)
        {
          Object value = getValueAt(0, c);
          return (value != null) ? value.getClass() : ImageIcon.class;
        }


        public boolean isCellEditable(int row, int col)
        {
          return col == 0;
        }


        public void setValueAt(Object value, int row, int col)
        {
          data[row][col] = value;
          fireTableCellUpdated(row, col);
        }
      };
    }
  }


  public static JTableRenderer getVertex(Component component)
  {
    while (component != null)
    {
      if (component instanceof JTableRenderer)
      {
        return (JTableRenderer) component;
      }
      component = component.getParent();
    }

    return null;
  }
}
