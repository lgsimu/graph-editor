package com.lgsim.engine.graphEditor.app;

import com.bulenkov.darcula.DarculaLaf;
import com.lgsim.engine.graphEditor.graph.editor.EditorMenuBar;
import com.lgsim.engine.graphEditor.graph.editor.GraphEditor;
import com.lgsim.engine.graphEditor.util.Configuration;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.mxgraph.swing.util.mxSwingConstants;
import com.mxgraph.util.mxConstants;

import javax.swing.*;
import java.awt.*;

public class Application
{
  private static final Configuration CONFIGURATION =
      new Configuration("LGSimulator", "GraphEditor", "1.0");


  private Application()
  {
    mxSwingConstants.SHADOW_COLOR = Color.LIGHT_GRAY;
    mxConstants.W3C_SHADOWCOLOR = "#D3D3D3";

    JFrame frame = new JFrame();
    GraphEditor editor = new GraphEditor();
    frame.setJMenuBar(new EditorMenuBar(editor));
    frame.getContentPane().add(editor);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    frame.addWindowListener(new ApplicationListener());
  }


  public static void main(String[] args)
  {
    ResourceRegistry.INSTANCE.registerAll();
    ImplementationRegistry.INSTANCE.registerAll();
    CONFIGURATION.applyIfPossible();
    SwingUtilities.invokeLater(() -> {
      try
      {
        UIManager.setLookAndFeel(new DarculaLaf());
        new Application();
      }
      catch (Exception e)
      {
        ExceptionManager.INSTANCE.dealWith(e);
      }
    });
  }
}
