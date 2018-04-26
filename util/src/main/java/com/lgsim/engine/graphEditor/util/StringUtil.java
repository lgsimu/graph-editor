package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"WeakerAccess", "unused"})
public class StringUtil {

  @Contract(pure = true)
  public static @NotNull String emptyString() {
    return "<empty>";
  }


  @NotNull
  @Contract(pure = true)
  public static String notNullize(@Nullable String s) {
    return notNullize(s, "");
  }


  @NotNull
  @Contract(pure = true)
  public static String notNullize(@Nullable String s, @NotNull String defaultValue) {
    return s == null ? defaultValue : s;
  }


  @Nullable
  @Contract(pure = true)
  public static String nullize(@Nullable String s) {
    return nullize(s, false);
  }


  @Nullable
  @Contract(pure = true)
  public static String nullize(@Nullable String s, boolean nullizeSpaces) {
    boolean empty = nullizeSpaces ? isEmptyOrSpaces(s) : isEmpty(s);
    return empty ? null : s;
  }


  @Contract(value = "null -> true", pure = true)
  // we need to keep this method to preserve backward compatibility
  public static boolean isEmptyOrSpaces(@Nullable String s) {
    return isEmptyOrSpaces((CharSequence) s);
  }


  @Contract(value = "null -> true", pure = true)
  public static boolean isEmptyOrSpaces(@Nullable CharSequence s) {
    if (isEmpty(s)) {
      return true;
    }
    for (int i = 0;i < s.length();i++) {
      if (s.charAt(i) > ' ') {
        return false;
      }
    }
    return true;
  }


  @Contract(value = "null -> true", pure = true)
  public static boolean isEmpty(@Nullable String s) {
    return s == null || s.isEmpty();
  }


  @Contract(value = "null -> true", pure = true)
  public static boolean isEmpty(@Nullable CharSequence cs) {
    return cs == null || cs.length() == 0;
  }


  @Contract(pure = true)
  public static @NotNull String getName(@NotNull Class cls) {
    return cls.getSimpleName();
  }
}
