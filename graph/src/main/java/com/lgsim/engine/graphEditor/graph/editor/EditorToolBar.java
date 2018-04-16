package com.lgsim.engine.graphEditor.graph.editor;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.lgsim.engine.graphEditor.graph.editor.EditorActions.*;

class EditorToolBar extends JToolBar
{
  private static final long serialVersionUID = -8015443128436394471L;
  private boolean ignoreZoomChange = false;


  EditorToolBar(final GraphEditor editor, int orientation)
  {
    super(orientation);
    setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), getBorder()));
    setFloatable(false);

    add(editor.bind("New", new NewAction(), "com/lgsim/engine/graphEditor/graph/images/new.gif"));
    add(editor.bind("Open", new OpenAction(),
                    "com/lgsim/engine/graphEditor/graph/images/open.gif"));
    add(editor.bind("Save", new SaveAction(false),
                    "com/lgsim/engine/graphEditor/graph/images/save.gif"));

    addSeparator();

    add(editor.bind("Print", new PrintAction(),
                    "com/lgsim/engine/graphEditor/graph/images/print.gif"));

    addSeparator();

    add(editor.bind("Cut", TransferHandler.getCutAction(),
                    "com/lgsim/engine/graphEditor/graph/images/cut.gif"));
    add(editor.bind("Copy", TransferHandler.getCopyAction(),
                    "com/lgsim/engine/graphEditor/graph/images/copy.gif"));
    add(editor.bind("Paste", TransferHandler.getPasteAction(),
                    "com/lgsim/engine/graphEditor/graph/images/paste.gif"));

    addSeparator();

    add(editor.bind("Delete", mxGraphActions.getDeleteAction(),
                    "com/lgsim/engine/graphEditor/graph/images/delete.gif"));

    addSeparator();

    add(editor.bind("Undo", new HistoryAction(true),
                    "com/lgsim/engine/graphEditor/graph/images/undo.gif"));
    add(editor.bind("Redo", new HistoryAction(false),
                    "com/lgsim/engine/graphEditor/graph/images/redo.gif"));

    addSeparator();

    GraphicsEnvironment env = GraphicsEnvironment
        .getLocalGraphicsEnvironment();
    List<String> fonts = new ArrayList<>();
    fonts.addAll(Arrays.asList("Helvetica", "Verdana", "Times New Roman", "Garamond", "Courier New", "-"));
    fonts.addAll(Arrays.asList(env.getAvailableFontFamilyNames()));

    final JComboBox fontCombo = new JComboBox(fonts.toArray());
    fontCombo.setEditable(true);
    fontCombo.setMinimumSize(new Dimension(120, 0));
    fontCombo.setPreferredSize(new Dimension(120, 0));
    fontCombo.setMaximumSize(new Dimension(120, 100));
    add(fontCombo);

    fontCombo.addActionListener(e -> {
      String font = Objects.requireNonNull(fontCombo.getSelectedItem()).toString();

      if (font != null && !font.equals("-"))
      {
        mxGraph graph = editor.getGraphComponent().getGraph();
        graph.setCellStyles(mxConstants.STYLE_FONTFAMILY, font);
      }
    });

    final JComboBox sizeCombo = new JComboBox(new Object[]{"6pt", "8pt",
        "9pt", "10pt", "12pt", "14pt", "18pt", "24pt", "30pt", "36pt",
        "48pt", "60pt"});
    sizeCombo.setEditable(true);
    sizeCombo.setMinimumSize(new Dimension(65, 0));
    sizeCombo.setPreferredSize(new Dimension(65, 0));
    sizeCombo.setMaximumSize(new Dimension(65, 100));
    add(sizeCombo);

    sizeCombo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        mxGraph graph = editor.getGraphComponent().getGraph();
        graph.setCellStyles(mxConstants.STYLE_FONTSIZE, sizeCombo
            .getSelectedItem().toString().replace("pt", ""));
      }
    });

    addSeparator();

    add(editor.bind("Bold", new FontStyleAction(true),
                    "com/lgsim/engine/graphEditor/graph/images/bold.gif"));
    add(editor.bind("Italic", new FontStyleAction(false),
                    "com/lgsim/engine/graphEditor/graph/images/italic.gif"));

    addSeparator();

    add(editor.bind("Left", new KeyValueAction(mxConstants.STYLE_ALIGN,
                                               mxConstants.ALIGN_LEFT),
                    "com/lgsim/engine/graphEditor/graph/images/left.gif"));
    add(editor.bind("Center", new KeyValueAction(mxConstants.STYLE_ALIGN,
                                                 mxConstants.ALIGN_CENTER),
                    "com/lgsim/engine/graphEditor/graph/images/center.gif"));
    add(editor.bind("Right", new KeyValueAction(mxConstants.STYLE_ALIGN,
                                                mxConstants.ALIGN_RIGHT),
                    "com/lgsim/engine/graphEditor/graph/images/right.gif"));

    addSeparator();

    add(editor.bind("Font", new ColorAction("Font",
                                            mxConstants.STYLE_FONTCOLOR),
                    "com/lgsim/engine/graphEditor/graph/images/fontcolor.gif"));
    add(editor.bind("Stroke", new ColorAction("Stroke",
                                              mxConstants.STYLE_STROKECOLOR),
                    "com/lgsim/engine/graphEditor/graph/images/linecolor.gif"));
    add(editor.bind("Fill", new ColorAction("Fill",
                                            mxConstants.STYLE_FILLCOLOR),
                    "com/lgsim/engine/graphEditor/graph/images/fillcolor.gif"));

    addSeparator();

    final mxGraphView view = editor.getGraphComponent().getGraph()
                                   .getView();
    final JComboBox zoomCombo = new JComboBox(new Object[]{"400%",
        "200%", "150%", "100%", "75%", "50%", mxResources.get("page"),
        mxResources.get("width"), mxResources.get("actualSize")});
    zoomCombo.setEditable(true);
    zoomCombo.setMinimumSize(new Dimension(75, 0));
    zoomCombo.setPreferredSize(new Dimension(75, 0));
    zoomCombo.setMaximumSize(new Dimension(75, 100));
    zoomCombo.setMaximumRowCount(9);
    add(zoomCombo);

    mxIEventListener scaleTracker = new mxIEventListener()
    {
      public void invoke(Object sender, mxEventObject evt)
      {
        ignoreZoomChange = true;

        try
        {
          zoomCombo.setSelectedItem((int) Math.round(100 * view
              .getScale())
                                    + "%");
        }
        finally
        {
          ignoreZoomChange = false;
        }
      }
    };

    view.getGraph().getView().addListener(mxEvent.SCALE, scaleTracker);
    view.getGraph().getView().addListener(mxEvent.SCALE_AND_TRANSLATE,
                                          scaleTracker);

    scaleTracker.invoke(null, null);

    zoomCombo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        mxGraphComponent graphComponent = editor.getGraphComponent();

        if (!ignoreZoomChange)
        {
          String zoom = zoomCombo.getSelectedItem().toString();

          if (zoom.equals(mxResources.get("page")))
          {
            graphComponent.setPageVisible(true);
            graphComponent
                .setZoomPolicy(mxGraphComponent.ZOOM_POLICY_PAGE);
          }
          else if (zoom.equals(mxResources.get("width")))
          {
            graphComponent.setPageVisible(true);
            graphComponent
                .setZoomPolicy(mxGraphComponent.ZOOM_POLICY_WIDTH);
          }
          else if (zoom.equals(mxResources.get("actualSize")))
          {
            graphComponent.zoomActual();
          }
          else
          {
            try
            {
              zoom = zoom.replace("%", "");
              double scale = Math.min(16, Math.max(0.01,
                                                   Double.parseDouble(zoom) / 100));
              graphComponent.zoomTo(scale, graphComponent
                  .isCenterZoom());
            }
            catch (Exception ex)
            {
              JOptionPane.showMessageDialog(editor, ex
                  .getMessage());
            }
          }
        }
      }
    });
  }
}
