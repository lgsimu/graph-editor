package com.lgsim.engine.graphEditor.graph.util;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageBundleUtil
{
  private static final ResourceBundle MESSAGES;

  static
  {
    MESSAGES = ResourceBundle.getBundle("com/lgsim/engine/graphEditor/graph/messages");
  }

  public static String get(@NotNull String key, @NotNull Object... arguments)
  {
    String val = MESSAGES.getString(key);
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
