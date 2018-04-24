package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Map;

public class ImplementationUtil {
  private static final Map<Class<?>, Class<?>> typeTable = new Hashtable<>();

  public static void put(@NotNull Class<?> interfaceType, @NotNull Class<?> implType)
  {
    typeTable.put(interfaceType, implType);
  }

  @NotNull
  @SuppressWarnings("unchecked")
  public static <T> T getInstanceOf(@NotNull Class<T> interfaceType) throws InstantiationException
  {
    try {
      Class<?> implType = typeTable.get(interfaceType);
      Constructor<?> cons = implType.getConstructor();
      return (T) cons.newInstance();
    } catch (InvocationTargetException e) {
      String msg = e.getTargetException().getMessage();
      throw new InstantiationException(msg);
    } catch (IllegalAccessException | NoSuchMethodException e) {
      throw new InstantiationException();
    }
  }
}
