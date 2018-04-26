package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Map;

public class ImplementationUtil {

  private static final Logger log = LoggerFactory.getLogger(ImplementationUtil.class);
  private static final Map<Class, Class> table = new Hashtable<>();


  public static <T> void put(@NotNull Class<T> k, @NotNull Class<? extends T> v) {
    log.debug("register {} to {}", StringUtil.getName(k), StringUtil.getName(v));
    Class old = table.get(k);
    if (old == null) {
      table.put(k, v);
    }
    else {
      log.error("it has already registered {}, ignore this {}", StringUtil.getName(k), StringUtil.getName(v));
    }
  }


  @NotNull
  @SuppressWarnings("unchecked")
  public static <T> T getInstanceOf(@NotNull Class<T> type) throws InstantiationException
  {
    try {
      Class implType = table.get(type);
      if (implType != null) {
        Constructor cons = implType.getConstructor();
        return (T) cons.newInstance();
      }
      else {
        throw new InstantiationException("unregister " + StringUtil.getName(type));
      }
    }
    catch (InvocationTargetException e) {
      String msg = e.getTargetException().getMessage();
      throw new InstantiationException(msg);
    }
    catch (IllegalAccessException | NoSuchMethodException e) {
      throw new InstantiationException();
    }
  }
}
