package com.lgsim.engine.graphEditor.util;

import com.lgsim.engine.graphEditor.util.exception.ResourceFileMissingException;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author leiqiao
 */
public class ResourceUtil {
  private static final Map<String, ImageIcon> imageIconCache = new Hashtable<>(64);

  /**
   * 查找资源uri
   *
   * @param path 资源相对路径，注意开头没有斜杠
   * @return 如果资源存在，则返回其uri，否则返回{@code null}
   */
  @Nullable
  public static URI lookup(@NotNull String path)
  {
    try {
      URL url = Thread.currentThread().getContextClassLoader().getResource(path);
      if (url != null) {
        return url.toURI();
      } else {
        throw new NullPointerException();
      }
    } catch (URISyntaxException | NullPointerException e) {
      ExceptionManager.INSTANCE.dealWith(new ResourceFileMissingException(path, ResourceType.URI));
      return null;
    }
  }

  /**
   * 查询ImageIcon
   *
   * @param path icon资源相对路径，注意开头没有斜杠
   * @return 如果存在，则返回，否则返回一个不正确的非{@code null} {@code ImageIcon}
   */
  @NotNull
  public static ImageIcon lookupImageIcon(@NotNull String path)
  {
    ImageIcon icon = imageIconCache.get(path);
    if (icon != null) {
      return icon;
    } else {
      URI uri = lookup(path);
      try {
        if (uri != null) {
          icon = new ImageIcon(uri.toURL());
        } else {
          throw new NullPointerException();
        }
      } catch (Exception e) {

        ExceptionManager.INSTANCE.dealWith(new ResourceFileMissingException(path, ResourceType.ICON));
        icon = new ImageIcon();
      }
      imageIconCache.put(path, icon);
      return icon;
    }
  }

  // TODO: cache
  public static @NotNull String lookupText(@NotNull String path)
  {
    URI uri = lookup(path);
    try {
      if (uri != null) {
        InputStream in = uri.toURL().openStream();
        return IOUtils.toString(in, "utf-8");
      } else {
        throw new NullPointerException();
      }
    } catch (IOException e) {
      ExceptionManager.INSTANCE.dealWith(e);
      return StringUtil.emptyString();
    }
  }
}
