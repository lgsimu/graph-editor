package com.lgsim.engine.graphEditor.widget.Test;

import javax.swing.*;
import java.awt.*;

public class ToolBarGroup extends JPanel{
    JPanel panel;
    JButton button,button2,button3;
    JToolBar toolBar;

    public ToolBarGroup(){

        button = new JButton("day1");
        button2 = new JButton("day2");
        button3 = new JButton("day3");
        toolBar = new JToolBar(SwingConstants.HORIZONTAL);
        toolBar.add(button);
        toolBar.add(button2);
        toolBar.add(button3);
        //toolBar.setVisible(false);
        //toolBar.setFloatable(true);




        //panel = new JPanel();
        //panel.add(toolBar,BorderLayout.WEST);
        this.add(toolBar);
        this.setVisible(true);
        this.setSize(500,500);
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public static void main(String[] args){
//        new ToolBarGroup();
//    }

}
