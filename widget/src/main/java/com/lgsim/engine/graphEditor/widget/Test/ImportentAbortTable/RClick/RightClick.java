package com.lgsim.engine.graphEditor.widget.Test.ImportentAbortTable.RClick;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class RightClick extends JFrame{

    JPopupMenu menu;
    JMenuItem mAll, mCopy, mCut, mPaste, mDel;
    JButton button,button2,button3;
    static JToolBar toolBar,toolBar2;
    JCheckBoxMenuItem tool1,tool2,tool3;
    JMenuBar menuBar;

JMenu menu3;
    JPanel rPanel;

    public RightClick(){

        button = new JButton("day1");
        button2 = new JButton("day2");
        button3 = new JButton("day3");
        toolBar = new JToolBar(SwingConstants.HORIZONTAL);
        toolBar.add(button);
        toolBar.add(button2);
        toolBar.add(button3);


        tool1 = new JCheckBoxMenuItem("工具栏-",false);
        tool2 = new JCheckBoxMenuItem("工具栏二",false);
        tool3 = new JCheckBoxMenuItem("工具栏三",false);


        tool1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tool1.getState()){
                    setTool(true);
                }else{
                    setTool(false);
                }
            }
        });


        menu = new JPopupMenu();

        menu.add(tool1);
        menu.add(tool2);
        menu.add(tool3);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON3){
                    menu.show(RightClick.this,e.getX(),e.getY());
                    tool1.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.print("rrrr");
                            menu.show(RightClick.this,e.getX(),e.getY());
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            menu.setVisible(true);
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });
                }

//                if(e.isPopupTrigger()){
//                    menu.show(RightClick.this,e.getX(),e.getY());
//                }
            }
        });

        showEnd();

    }

    public void showEnd(){
        toolBar.setVisible(false);
        this.add(toolBar);
        this.setSize(400,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setTool(boolean flag){
        toolBar.setVisible(flag);
        this.repaint();
    }

    public static void main(String[] args){
        new RightClick();
    }
}
