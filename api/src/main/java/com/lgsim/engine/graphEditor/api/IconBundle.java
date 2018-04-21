package com.lgsim.engine.graphEditor.api;

import com.lgsim.engine.graphEditor.util.SensitiveIcon;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Hashtable;
import java.util.Map;

public class IconBundle implements IRegistry
{
  private static final Logger log = LoggerFactory.getLogger(IconBundle.class);
  private static final Map<String, SensitiveIcon> iconCache = new Hashtable<>();


  @NotNull
  public static Icon get(@NotNull String id)
  {
    SensitiveIcon sensitiveIcon = iconCache.get(id);
    if (sensitiveIcon == null)
    {
      log.debug("load icon failed {}", id);
      return new ImageIcon();
    }
    else
    {
      return sensitiveIcon.getIcon();
    }
  }


  @NotNull
  public static IconBundle newInstance()
  {
    return new IconBundle();
  }


  @Override
  public void registerAll()
  {
    iconCache.put("solver.calc", new SensitiveIcon(new ImageIcon(), new ImageIcon()));
    iconCache.put("solver.settings", new SensitiveIcon(new ImageIcon(), new ImageIcon()));
    iconCache.put("graphDocument.save", new SensitiveIcon(new ImageIcon(), new ImageIcon()));
  }
}
