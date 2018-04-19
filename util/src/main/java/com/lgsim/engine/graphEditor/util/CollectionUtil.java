package com.lgsim.engine.graphEditor.util;

import org.apache.commons.lang.SerializationUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class CollectionUtil
{
  @SuppressWarnings("unchecked")
  public static <T extends Serializable> List<T> cloneList(@NotNull List<T> ls)
  {
    final Vector<T> output = new Vector<>(ls.size());
    for (T t : ls)
    {
      Object clone = SerializationUtils.clone(t);
      output.add((T) clone);
    }
    return output;
  }
}
