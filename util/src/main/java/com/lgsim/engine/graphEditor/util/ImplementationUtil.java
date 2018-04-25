package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Map;

public class ImplementationUtil {
  private static final Map<Class<?>, Object> table = new Hashtable<>();

  public static void putType(@NotNull Class<?> interfaceType, @NotNull Class<?> implType)
  {
    table.put(interfaceType, implType);
  }

  public static <T> void putInstance(@NotNull Class<T> type, @NotNull T t) {
    table.put(type, t);
  }

  @NotNull
  @SuppressWarnings("unchecked")
  public static <T> T getInstanceOf(@NotNull Class<T> type) throws InstantiationException
  {
    Object o = table.get(type);
    if (o == null) {
      throw new InstantiationException("cannot find any types or instances");
    } else {
      if (o instanceof Class<?>) {
        try {
          Class<?> implType = (Class<?>) o;
          Constructor<?> cons = implType.getConstructor();
          return (T) cons.newInstance();
        } catch (InvocationTargetException e) {
          String msg = e.getTargetException().getMessage();
          throw new InstantiationException(msg);
        } catch (IllegalAccessException | NoSuchMethodException e) {
          throw new InstantiationException();
        }
      } else {
        return (T) o;
      }
    }
  }
}
