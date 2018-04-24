package com.lgsim.engine.graphEditor.widget.Test.ImportentAbortTable.RClick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Rmenu extends JFrame {
    private JPopupMenu popupMenu;
    private JCheckBoxMenuItem menuItem, menuItem2, menuItem3;
    private  Map<JCheckBoxMenuItem,JToolBar> map;
    private Box box;
    private JPanel panel;
    public Rmenu() {
        buildToolNameAndListener();
        setPopShow();
        setToolBar();
        this.setSize(1000, 1000);
        this.setVisible(true);
        //this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void setPopShow() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(Rmenu.this, e.getX(), e.getY());
                }
            }
        });
    }

    public URL getUrl(String load){
        String loads = "/com/lgsim/engine/graphEditor/widget/png/" + load;
        URL url =  this.getClass().getResource(loads);
        return url;
    }

    public void setToolBar(){
        String image ="animal.png";
        String image2 ="animal2.png";
        String image3 = "animal3.png";

        JButton button = new JButton(new ImageIcon(getUrl(image)));
        JButton button2 = new JButton(new ImageIcon(getUrl(image2)));
        JButton button3 = new JButton(new ImageIcon(getUrl(image3)));


        String image4 ="c1.png";
        String image5 ="c2.png";
        String image6 = "c3.png";
        JButton button4 = new JButton(new ImageIcon(getUrl(image4)));
        JButton button5 = new JButton(new ImageIcon(getUrl(image5)));
        JButton button6 = new JButton(new ImageIcon(getUrl(image6)));


        String image7 ="t2.png";
        String image8 ="t3.png";
        String image9 = "t4.png";
        JButton button7 = new JButton(new ImageIcon(getUrl(image7)));
        JButton button8 = new JButton(new ImageIcon(getUrl(image8)));
        JButton button9 = new JButton(new ImageIcon(getUrl(image9)));

        JToolBar toolBar = new JToolBar();
        toolBar.setLocation(0,0);
        toolBar.setVisible(false);
        toolBar.setFloatable(true);
        toolBar.add(button);
        toolBar.add(button2);
        toolBar.add(button3);


        JToolBar toolBar2 = new JToolBar();
        toolBar2.setVisible(false);
        toolBar2.setFloatable(true);
        toolBar2.add(button4);
        toolBar2.add(button5);
        toolBar2.add(button6);

        JToolBar toolBar3 = new JToolBar();
        toolBar3.setFloatable(true);
        toolBar3.setVisible(false);
        toolBar3.add(button7);
        toolBar3.add(button8);
        toolBar3.add(button9);

        map = new HashMap<>();
        map.put(menuItem,toolBar);
        map.put(menuItem2,toolBar2);
        map.put(menuItem3,toolBar3);
        //map.put(menuItem3,)

        panel = new JPanel();
        panel.setSize(100,50);
        panel.setBackground(Color.red);
        panel.setLayout(new FlowLayout());
       // panel.add(BorderLayout.PAGE_START);
        panel.add(toolBar);
        panel.add(toolBar2);
        panel.add(toolBar3);
        this.add(panel,BorderLayout.WEST);

        //this.setLayout(panel,new FlowLayout());




    }

    public void setShowToolBar(JToolBar toolBar){
        toolBar.setVisible(true);
    }

    public void setHideToolBar(JToolBar toolBar){
        toolBar.setVisible(false);
    }

    public void buildToolNameAndListener() {//创建工具名称

        menuItem = new JCheckBoxMenuItem("工具一", false);
        menuItem2 = new JCheckBoxMenuItem("工具二", false);
        menuItem3 = new JCheckBoxMenuItem("工具三", false);

        setListener(menuItem);
        setListener(menuItem2);
        setListener(menuItem3);

        popupMenu = new JPopupMenu();
        popupMenu.setBorder(BorderFactory.createLoweredBevelBorder());
        popupMenu.add(menuItem);
        popupMenu.addSeparator();
        popupMenu.add(menuItem2);
        popupMenu.addSeparator();
        popupMenu.add(menuItem3);
    }

    public void setListener(JCheckBoxMenuItem menuItem) {
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuItem.getState()) {
                    setShowToolBar(map.get(menuItem));
                    //System.out.print("1");
                }else{
                    setHideToolBar(map.get(menuItem));
                }
                popupMenu.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new Rmenu();
    }

}
