package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.Nullable;

import java.lang.ref.Reference;

public class ReferenceUtil {
  @Nullable
  public static <T> T dereference(@Nullable Reference<T> ref) {
    return ref == null ? null : ref.get();
  }
}
