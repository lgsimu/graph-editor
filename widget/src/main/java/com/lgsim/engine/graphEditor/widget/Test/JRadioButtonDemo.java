package com.lgsim.engine.graphEditor.widget.Test;

import java.awt.FlowLayout;
  
import javax.swing.ButtonGroup;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JRadioButton;  
import javax.swing.border.EmptyBorder;  
  
public class JRadioButtonDemo extends JFrame {  
    public JRadioButtonDemo(){  
        this.setTitle("单选按钮的使用");  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setBounds(100, 100, 250, 100);  
        JPanel contentPane=new JPanel();  
        contentPane.setBorder(new EmptyBorder(5,5,5,5));  
        this.setContentPane(contentPane);  
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));  
        JRadioButton randioButton1=new JRadioButton("Java");  
        JRadioButton randioButton2=new JRadioButton("PHP",true);  
        JRadioButton randioButton3=new JRadioButton("C++");  
        contentPane.add(randioButton1);  
        contentPane.add(randioButton2);  
        contentPane.add(randioButton3);  
        ButtonGroup group=new ButtonGroup();  
        group.add(randioButton1);  
        group.add(randioButton2);  
        group.add(randioButton3);  
        this.setVisible(true);  
          
    }  
    public static void main(String[]args){  
        JRadioButtonDemo example=new JRadioButtonDemo();  
    }  
}  