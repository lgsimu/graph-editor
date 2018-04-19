package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author leiqiao
 */
public class ResourceUtil
{
  /**
   * 查找资源uri
   *
   * @param path 资源相对路径，注意开头没有斜杠
   * @return 如果资源存在，则返回其uri，否则返回{@code null}
   */
  @Nullable
  public static URI lookup(@NotNull String path)
  {
    try
    {
      URL url = Thread.currentThread().getContextClassLoader().getResource(path);
      if (url != null)
      {
        return url.toURI();
      }
      else
      {
        throw new NullPointerException();
      }
    }
    catch (URISyntaxException | NullPointerException e)
    {
      ExceptionManager.INSTANCE.dealWith(new ResourceFileMissingException());
      return null;
    }
  }
}
