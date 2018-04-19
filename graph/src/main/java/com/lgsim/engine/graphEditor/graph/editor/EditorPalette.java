package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditorPalette extends JPanel
{
  private static final long serialVersionUID = 7771113885935187066L;
  private static final Logger logger = LoggerFactory.getLogger(EditorPalette.class);
  private mxEventSource eventSource = new mxEventSource(this);
  private JLabel selectedEntry;


  EditorPalette()
  {
    setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

    setTransferHandler(new TransferHandler()
    {
      public boolean canImport(JComponent comp, DataFlavor[] flavors)
      {
        return true;
      }
    });

    addMouseListener(new MouseAdapter()
    {
      @Override
      public void mousePressed(MouseEvent e)
      {
        setSelectionEntry(null, null);
      }
    });
  }


  public void setPreferredWidth(int width)
  {
    int cols = Math.max(1, width / 55);
    setPreferredSize(new Dimension(width, (getComponentCount() * 55 / cols) + 30));
    revalidate();
  }


  public void addStencil(@NotNull final IVertexStencil stencil)
  {

  }





  private void setSelectionEntry(JLabel entry, mxGraphTransferable t)
  {
    JLabel previous = selectedEntry;
    selectedEntry = entry;

    if (previous != null)
    {
      previous.setBorder(null);
      previous.setOpaque(false);
    }

    if (selectedEntry != null)
    {
      selectedEntry.setBorder(ShadowBorder.getSharedInstance());
      selectedEntry.setOpaque(true);
    }

    eventSource.fireEvent(new mxEventObject(mxEvent.SELECT,
                                            "entry", selectedEntry,
                                            "transferable", t,
                                            "previous", previous));
  }


  public void addListener(String eventName, mxIEventListener listener)
  {
    eventSource.addListener(eventName, listener);
  }


  public boolean isEventsEnabled()
  {
    return eventSource.isEventsEnabled();
  }


  public void removeListener(mxIEventListener listener)
  {
    eventSource.removeListener(listener);
  }


  public void removeListener(mxIEventListener listener, String eventName)
  {
    eventSource.removeListener(listener, eventName);
  }


  public void setEventsEnabled(boolean eventsEnabled)
  {
    eventSource.setEventsEnabled(eventsEnabled);
  }
}
