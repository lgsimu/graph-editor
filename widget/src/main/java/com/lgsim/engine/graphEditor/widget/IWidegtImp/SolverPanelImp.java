package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.widget.console.IConsole;
import com.lgsim.engine.graphEditor.widget.Component.SolverOutputPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SolverPanelImp implements IConsole {
    public SolverPanelImp(){}

    @Override
    public void println(@NotNull String line) {
       // solverImp.calc(line);
    }

    @Override
    public @NotNull JComponent getSwingComponent() {
        return new SolverOutputPanel();
    }

}
