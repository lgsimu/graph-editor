package com.lgsim.engine.graphEditor.widget.Test;

import com.lgsim.engine.graphEditor.api.data.IGraphCodec;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocument;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AreaText extends JPanel {
    JTextArea textArea;
    JScrollPane scrollPane;

    public AreaText() {

        Dimension dimension = this.getMaximumSize();
        textArea = new JTextArea();
        textArea.setSize(dimension);
        scrollPane = new JScrollPane(textArea);
        this.add(scrollPane);
        this.setSize(300, 300);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new AreaText();
    }



}
