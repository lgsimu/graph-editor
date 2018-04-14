package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Map;

public class ImplementationUtil
{
  private static final Logger log = LoggerFactory.getLogger(ImplementationUtil.class);
  private static final Map<Class<?>, Class<?>> typeTable = new Hashtable<>();


  public static void put(@NotNull Class<?> interfaceType, @NotNull Class<?> implType)
  {
    typeTable.put(interfaceType, implType);
  }


  @Nullable
  @SuppressWarnings("unchecked")
  public static <T> T getInstanceOf(@NotNull Class<T> interfaceType)
  {
    Class<?> implType = typeTable.get(interfaceType);
    try
    {
      Constructor<?> cons = implType.getConstructor();
      return (T) cons.newInstance();
    }
    catch (Exception e)
    {
      log.debug("", e);
      return null;
    }
  }
}
