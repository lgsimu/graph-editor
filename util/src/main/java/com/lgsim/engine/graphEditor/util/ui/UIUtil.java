package com.lgsim.engine.graphEditor.util.ui;

import com.lgsim.engine.graphEditor.util.ReferenceUtil;

import javax.swing.border.Border;
import java.lang.ref.SoftReference;
import java.util.Hashtable;
import java.util.Map;

@SuppressWarnings("unused")
public class UIUtil {
  private static SoftReference<Map<Class<? extends Border>, Border>> borderCache;

  public static Border getShadowBorder() {
    Map<Class<? extends Border>, Border> borderMap = ReferenceUtil.dereference(borderCache);
    if (borderMap == null) {
      borderMap = new Hashtable<>();
      ShadowBorder border = new ShadowBorder();
      borderMap.put(ShadowBorder.class, border);
      borderCache = new SoftReference<>(borderMap);
      return border;
    } else {
      return borderMap.get(ShadowBorder.class);
    }
  }
}