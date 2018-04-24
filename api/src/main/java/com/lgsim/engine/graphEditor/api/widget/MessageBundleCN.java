package com.lgsim.engine.graphEditor.api.widget;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageBundleCN {

    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle("com/lgsim/engine/graphEditor/api/message_zh_CN2");

    public static String get(@NotNull String key, @NotNull Object... arguments)
    {
        String val = BUNDLE.getString(key);
        String valueCN = "";
        try {
            valueCN = new String(BUNDLE.getString(key).getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (arguments.length == 0)
        {
            return valueCN;
        }
        else
        {
            MessageFormat format = new MessageFormat("");
            format.applyPattern(valueCN);
            return format.format(arguments);
        }
    }
}
