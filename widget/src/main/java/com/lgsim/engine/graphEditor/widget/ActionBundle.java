package com.lgsim.engine.graphEditor.widget;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ActionBundle
{
  private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("com/lgsim/engine/graphEditor/widget/actionMessages");


  public static String get(@NotNull String key, @NotNull Object... arguments)
  {
    String val = BUNDLE.getString(key);
    if (arguments.length == 0)
    {
      return val;
    }
    else
    {
      MessageFormat format = new MessageFormat("");
      format.applyPattern(val);
      return format.format(arguments);
    }
  }
}
