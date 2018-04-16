package com.lgsim.engine.graphEditor.graph.util;

import com.lgsim.engine.graphEditor.graph.ApplicationContext;
import com.lgsim.engine.graphEditor.graph.component.ResourceFileMissingException;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.net.URL;

public class IconUtil
{
  @NotNull
  public static ImageIcon getIcon(@NotNull String path)
  {
    URL url = Thread.currentThread().getContextClassLoader().getResource(path);
    if (url != null)
    {
      return new ImageIcon(url);
    }
    else
    {
      ApplicationContext.getInstance()
                        .getApplicationExceptionManager()
                        .dealWith(new ResourceFileMissingException());
      return new ImageIcon();
    }
  }
}
