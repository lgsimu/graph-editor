package com.lgsim.engine.graphEditor.widget.Component.tabletab;

import com.lgsim.engine.graphEditor.widget.Component.TablePanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TableTab extends JTabbedPane {

    private JTabbedPane tabbedPane;
    private TablePanel panel;

    public TableTab(){

        panel = new TablePanel();

        tabbedPane = new JTabbedPane();


        tabbedPane.addTab("输入参数",panel);
        tabbedPane.addTab("输出参数",panel);


        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });




    }
}
