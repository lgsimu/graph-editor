package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.util.ResourceUtil;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.swing.util.mxSwingConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxRectangle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class StencilPalette extends JPanel {
  private mxEventSource eventSource = new mxEventSource(this);
  private JLabel selectedEntry;

  StencilPalette() {
    setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
    setTransferHandler(new TransferHandler() {
      public boolean canImport(JComponent comp, DataFlavor[] flavors)
      {
        return true;
      }
    });
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e)
      {
        setSelectionEntry(null, null);
      }
    });
  }

  void setPreferredWidth(int width)
  {
    int cols = Math.max(1, width / 55);
    setPreferredSize(new Dimension(width, (getComponentCount() * 55 / cols) + 30));
    revalidate();
  }

  void addStencil(@NotNull final IVertexStencil stencil)
  {
    IVertex cellVal = Builder.createVertex(stencil, false);
    mxCell cell = new mxCell(cellVal, new mxGeometry(0, 0, 64, 64),
        "icon;image=/" + stencil.getGraphIcon());
    cell.setVertex(true);
    mxRectangle bounds = (mxGeometry) cell.getGeometry().clone();
    final mxGraphTransferable t = new mxGraphTransferable(new Object[]{cell}, bounds);
    ImageIcon icon = ResourceUtil.lookupImageIcon(stencil.getStencilIcon());
    if (icon.getIconWidth() > 32 || icon.getIconHeight() > 32) {
      icon = new ImageIcon(icon.getImage().getScaledInstance(32, 32, 0));
    }
    final JLabel entry = new JLabel(icon);
    entry.setPreferredSize(new Dimension(50, 50));
    entry.setBackground(StencilPalette.this.getBackground().brighter());
    entry.setFont(new Font(entry.getFont().getFamily(), Font.PLAIN, 10));

    entry.setVerticalTextPosition(JLabel.BOTTOM);
    entry.setHorizontalTextPosition(JLabel.CENTER);
    entry.setIconTextGap(0);

    final String name = stencil.getName();
    entry.setToolTipText(name);
    entry.setText(name);

    entry.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e)
      {
        setSelectionEntry(entry, t);
      }
    });

    DragGestureListener dragGestureListener = e -> e.startDrag(null, mxSwingConstants.EMPTY_IMAGE,
        new Point(), t, null);
    DragSource dragSource = new DragSource();
    dragSource.createDefaultDragGestureRecognizer(entry, DnDConstants.ACTION_COPY, dragGestureListener);
    add(entry);
  }

  @SuppressWarnings("SameParameterValue")
  private void setSelectionEntry(@Nullable JLabel entry, @Nullable mxGraphTransferable t)
  {
    JLabel previous = selectedEntry;
    selectedEntry = entry;

    if (previous != null) {
      previous.setBorder(null);
      previous.setOpaque(false);
    }

    if (selectedEntry != null) {
      selectedEntry.setBorder(ShadowBorder.getSharedInstance());
      selectedEntry.setOpaque(true);
    }

    eventSource.fireEvent(new mxEventObject(mxEvent.SELECT, "entry", selectedEntry,
        "transferable", t, "previous", previous));
  }

  @SuppressWarnings({"SameParameterValue", "unused"})
  void addListener(@NotNull String eventName, @NotNull mxIEventListener listener)
  {
    eventSource.addListener(eventName, listener);
  }

  @SuppressWarnings("unused")
  void removeListener(mxIEventListener listener)
  {
    eventSource.removeListener(listener);
  }
}
