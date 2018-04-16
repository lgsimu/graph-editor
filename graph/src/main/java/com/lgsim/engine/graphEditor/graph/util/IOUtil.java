package com.lgsim.engine.graphEditor.graph.util;

import com.lgsim.engine.graphEditor.graph.ApplicationContext;
import com.lgsim.engine.graphEditor.graph.component.ResourceFileMissingException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class IOUtil
{
  @Nullable
  public static String getResourceURI(@NotNull String path)
  {
    try
    {
      URL url = Thread.currentThread().getContextClassLoader().getResource(path);
      if (url == null)
      {
        throw new NullPointerException();
      }
      else
      {
        URI uri = url.toURI();
        return uri.toString();
      }
    }
    catch (URISyntaxException | NullPointerException e)
    {
      ApplicationContext.getInstance().getApplicationExceptionManager()
                        .dealWith(new ResourceFileMissingException());
      return null;
    }
  }
}
