package com.lgsim.engine.graphEditor.widget.Component.statusbar;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JTabbedPane {

    private JTabbedPane tabbedPane;
    private LogPanel logPanel;
    private CalcOutPanel calcOutPanel;

    public StatusBar() {

        addPanel();
        tabbedPane.addTab("日志", logPanel);
        tabbedPane.addTab("输出", calcOutPanel);
        this.add(tabbedPane);

    }

    public void addPanel() {

        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        logPanel = new LogPanel();
        calcOutPanel = new CalcOutPanel();
        Dimension dimension = this.getMaximumSize();
        this.setSize(dimension);
    }
}
