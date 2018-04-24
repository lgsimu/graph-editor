package com.lgsim.engine.graphEditor.widget.Test;

import java.awt.*;

import java.awt.event.*;


import javax.swing.JFrame;

import javax.swing.JMenu;

import javax.swing.JMenuItem;

import javax.swing.JPanel;

import javax.swing.JPopupMenu;

import javax.swing.JSeparator;

import javax.swing.UIManager;

import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.border.EmptyBorder;


import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import javax.swing.JCheckBoxMenuItem;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;


public class MyFrame extends JFrame {


    private JPanel contentPane;

    JPopupMenu popupMenu;


    /**
     * Launch the application.
     */

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {

                    MyFrame frame = new MyFrame();

                    frame.setVisible(true);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });

    }


    /**
     * Create the frame.
     */

    public MyFrame() {


//设定成 Windows 样式的 LookAndFeel

        try {

            UIManager.setLookAndFeel(new WindowsLookAndFeel());

        } catch (UnsupportedLookAndFeelException e) {

            e.printStackTrace();

        }


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        contentPane.setLayout(new BorderLayout(0, 0));


//手动为 contentPane 添加鼠标点击事件：鼠标左键点击弹框

        contentPane.addMouseListener(new MouseAdapter() {


            @Override

            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(MyFrame.this, e.getX(), e.getY());
                    popupMenu.setAutoscrolls(true);
                    popupMenu.repaint();

                }


            }


        });


//将 popupMenu 的声明转移到类中

        popupMenu = new JPopupMenu();
        popupMenu.setVisible(true);
        popupMenu.getComponent().setVisible(true);
        popupMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.print("hjdighfgjhfgkjdhgj");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                popupMenu.setVisible(true);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.print("7");
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                System.out.print("1");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.print("2");
            }
        });
//可以将 JPopupMenu 添加到 contentPane 中，也可以不添加

//将下面的 addPopup() 注释掉即不添加

       // addPopup(contentPane, popupMenu);


        JMenuItem mntmX = new JMenuItem("X");

        popupMenu.add(mntmX);


        JMenuItem mntmY = new JMenuItem("Y");

        popupMenu.add(mntmY);


        JMenuItem mntmZ = new JMenuItem("Z");

        popupMenu.add(mntmZ);


//分隔线，可以使用代码手动实现，

//也可以使用控件（组件） JSeparator 拖动实现

        JSeparator separator = new JSeparator();

        popupMenu.add(separator);


        JMenu mnNum = new JMenu("Num");

        popupMenu.add(mnNum);


        JMenuItem menuItem = new JMenuItem("1");

        mnNum.add(menuItem);


        JMenuItem menuItem_1 = new JMenuItem("2");

        mnNum.add(menuItem_1);


        JMenuItem menuItem_2 = new JMenuItem("3");

        mnNum.add(menuItem_2);


        JSeparator separator_1 = new JSeparator();

        popupMenu.add(separator_1);


        JMenuItem mntmClose = new JMenuItem("Close");

//对菜单项：Close 添加 actionPerformed 事件

//点击 Close 关闭程序（注意：添加鼠标点击事件无效）

        mntmClose.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }

        });


        JCheckBoxMenuItem chckbxmntmC1 = new JCheckBoxMenuItem("C1");

        chckbxmntmC1.setSelected(true);

        popupMenu.add(chckbxmntmC1);


        JCheckBoxMenuItem chckbxmntmC2 = new JCheckBoxMenuItem("C2");
        chckbxmntmC2.setVisible(true);

        popupMenu.add(chckbxmntmC2);


        JRadioButtonMenuItem rdbtnmntmR1 = new JRadioButtonMenuItem("R1");

        rdbtnmntmR1.setSelected(true);

        popupMenu.add(rdbtnmntmR1);


        JRadioButtonMenuItem rdbtnmntmR2 = new JRadioButtonMenuItem("R2");

        popupMenu.add(rdbtnmntmR2);

        popupMenu.add(mntmClose);

    }


//这是将 JPopupMenu 添加到 contentPane 中，自动生成的代码

//    private static void addPopup(Component component, final JPopupMenu popup) {
//
//        component.addMouseListener(new MouseAdapter() {
//
////鼠标右键点击弹框
//
//            public void mousePressed(MouseEvent e) {
//
//                if (e.isPopupTrigger()) {
//
//                    showMenu(e);
//
//                }
//
//            }
//
////鼠标右键点击弹框
//
//
//            private void showMenu(MouseEvent e) {
//
//                popup.show(e.getComponent(), e.getX(), e.getY());
//
//            }
//
//        });
//
//    }

}