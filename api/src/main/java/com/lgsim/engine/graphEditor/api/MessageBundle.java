package com.lgsim.engine.graphEditor.api;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageBundle
{
  private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("com/lgsim/engine/graphEditor/api/messages");


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
