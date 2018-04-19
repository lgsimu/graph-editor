package com.lgsim.engine.graphEditor.widget.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Resolver extends JPanel {
    JPanel panel, panel2, panel3;
    JButton start, stop, continues;
    JTextArea output;
    JLabel label;
    JScrollPane scrollPane;
    Box box;
    Process process;
    static Thread thread;

    /**
     * 执行命令并同步输出
     *
     * @param command
     */
    public void executeCmd(String command) {
        thread = new Thread() {
            public void run() {
                String exeDir = "D:/graph-editor/widget/out/production/resources" +
                        "/com/lgsim/engine/graphEditor/widget/exe/";
                Runtime runtime = Runtime.getRuntime();
                try {
                    process = runtime.exec("cmd /c " + command, null, new File(exeDir));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        output.append(line + "\r\n");

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        };
        thread.start();
    }

    /**
     * 添加监听
     */
    public void setListener() {
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.print("rop");
                    executeCmd("LGSAS " + "\"D:\\file\\case-0418-2\" 2 3 1");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("rrr");
                try {
                    if (thread == null) {
                        return;
                    }
                    thread.stop();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        continues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("tttt");
                if (thread == null) {
                    return;
                }
                thread.interrupt();
            }
        });

    }

    /**
     * 显示窗口
     */
    public Resolver() {
        stop = new JButton("停止");
        start = new JButton("开始");
        continues = new JButton("继续");

        label = new JLabel("输出结果", Label.LEFT);
        label.setHorizontalAlignment(Label.LEFT);

        output = new JTextArea(60, 60);
        output.setLineWrap(true);

        setListener();

        panel3 = new JPanel();
        panel3.add(label);

        panel = new JPanel();
        panel.add(start);
        panel.add(stop);
        panel.add(continues);

        panel2 = new JPanel();
        panel2.add(output);
        scrollPane = new JScrollPane(panel2);

        box = Box.createVerticalBox();
        box.add(panel);
        box.add(panel3);
        box.add(scrollPane);

        this.add(box);

    }


}