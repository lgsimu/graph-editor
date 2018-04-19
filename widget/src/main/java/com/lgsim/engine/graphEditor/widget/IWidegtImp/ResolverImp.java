package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.widget.IWidget;
import com.lgsim.engine.graphEditor.widget.Component.Resolver;

import javax.swing.*;

public class ResolverImp implements IWidget {
    @Override
    public JComponent getSwingComponent() {
        return new Resolver();
    }
}
