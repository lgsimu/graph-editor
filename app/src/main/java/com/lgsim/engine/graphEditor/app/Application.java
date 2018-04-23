package com.lgsim.engine.graphEditor.app;

import com.bulenkov.darcula.DarculaLaf;
import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.IconBundle;
import com.lgsim.engine.graphEditor.graph.editor.EditorMenuBar;
import com.lgsim.engine.graphEditor.graph.editor.Editor;
import com.lgsim.engine.graphEditor.util.Configuration;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.mxgraph.swing.util.mxSwingConstants;
import com.mxgraph.util.mxConstants;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class Application implements IApplication
{
  private static final String corporationName = "LGSimulator";
  private static final String artifactName = "GraphEditor";
  private static final String version = "1.0";
  private static final Configuration CONFIGURATION =
      new Configuration(corporationName, artifactName, version);


  private Application()
  {
    mxSwingConstants.SHADOW_COLOR = Color.LIGHT_GRAY;
    mxConstants.W3C_SHADOWCOLOR = "#D3D3D3";

    JFrame frame = new JFrame();
    Editor editor = new Editor(this);
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
    IconBundle.newInstance().registerAll();
    ResourceRegistry.INSTANCE.registerAll();
    ImplementationRegistry.INSTANCE.registerAll();
    CONFIGURATION.applyIfPossible();
    SwingUtilities.invokeLater(() -> {
      try
      {
        // workaround due to bug in initializer (bulenkov/Darcula issue #29)
        UIManager.getFont("Label.font");
        UIManager.setLookAndFeel(new DarculaLaf());
        new Application();
      }
      catch (Exception e)
      {
        ExceptionManager.INSTANCE.dealWith(e);
      }
    });
  }


  @Override
  public @NotNull String getImplementationTitle()
  {
    return artifactName;
  }


  @Override
  public @NotNull String getImplementationVersion()
  {
    return version;
  }


  @Override
  public @NotNull String getImplementationVendor()
  {
    return corporationName;
  }
}
