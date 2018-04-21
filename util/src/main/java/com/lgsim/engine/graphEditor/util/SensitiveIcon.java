package com.lgsim.engine.graphEditor.util;

import com.bulenkov.darcula.DarculaLaf;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SensitiveIcon
{
  private Icon darkIcon;
  private Icon lightIcon;


  public SensitiveIcon(@NotNull Icon darkIcon, @NotNull Icon lightIcon)
  {
    this.darkIcon = darkIcon;
    this.lightIcon = lightIcon;
  }


  public Icon getIcon()
  {
    if (isLightTheme())
    {
      return lightIcon;
    }
    else
    {
      return darkIcon;
    }
  }


  private boolean isLightTheme()
  {
    LookAndFeel laf = UIManager.getLookAndFeel();
    return !(laf instanceof DarculaLaf);
  }
}
