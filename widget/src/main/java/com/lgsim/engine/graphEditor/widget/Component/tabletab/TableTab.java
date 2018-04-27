package com.lgsim.engine.graphEditor.widget.Component.tabletab;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.widget.Component.TablePanel;
import javafx.scene.control.Tab;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TableTab extends JTabbedPane {

    private JTabbedPane tabbedPane = new JTabbedPane();


    public TableTab(IVertex vertex) {

        setTabbedPane(vertex);
        setListener();
        this.setVisible(true);
    }

    public void setListener() {

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        this.add(tabbedPane);
    }

    public void setTabbedPane(IVertex vertex) {

       TablePanel cavityPanel = new TablePanel();
        TablePanel globalPanel = new TablePanel();

        if (StringUtils.equals(getNodeType(vertex), "global")) {
            tabbedPane.addTab("全局输入参数", globalPanel);
            tabbedPane.addTab("全局输出参数", globalPanel);

        }
        if (StringUtils.equals(getNodeType(vertex), "cavity")) {
            tabbedPane.addTab("输入参数", cavityPanel);
            tabbedPane.addTab("输出参数", cavityPanel);
        }

    }

    /**
     * true 为全局节点
     * false 为腔节点
     *
     * @param vertex
     * @return
     */

    public String getNodeType(IVertex vertex) {

        if (vertex.isCavity()) {
            return "cavity";
        }
        if (vertex.isGlobal()) {
            return "global";
        }
        return "";

    }

}
