package com.lgsim.engine.graphEditor.ui;

import com.lgsim.engine.graphEditor.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class UISupport {

  public static @NotNull String getText(@NotNull JTextField textField) {
    Document document = textField.getDocument();
    int length = document.getLength();
    try {
      String text = document.getText(0, length);
      if (text == null) {
        return StringUtil.emptyString();
      }
      else {
        return text;
      }
    }
    catch (BadLocationException e) {
      return StringUtil.exceptionString();
    }
  }
}
