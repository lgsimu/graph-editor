package com.lgsim.engine.graphEditor.widget.Test.ImportentAbortTable.ResolverTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Calculate extends JFrame {

    JPanel panel, panel2, panel3;
    JButton start, stop, continues;
    JTextArea output;
    JLabel label;
    JScrollPane scrollPane;
    Box box;
    Process process;
    static Thread thread, thread2;

    public void executeCmd(String command) {
        String courseFile = "";
        File directory = new File("");// 参数为空
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String exedir2 = courseFile + "/widget/out/production" + "/resources/com/lgsim/engine/graphEditor/widget/exe/";
//                String exeDir = "D:/graph-editor/widget/out/production/resources" +
//                "/com/lgsim/engine/graphEditor/widget/exe/";
        Runtime runtime = Runtime.getRuntime();
        try {
            process = runtime.exec("cmd /c " + command, null, new File(exedir2));
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

    public void newExecuteCmd() {
        //ProcessBuilder builder = new ProcessBuilder("cmd","/c");
        String exeDir = "D:/graph-editor/widget/out/production/resources" +
                "/com/lgsim/engine/graphEditor/widget/exe/";
        String fileDir = "\"D:\\file\\\"";
        String para = "LGSAS \"D:\\file\\case-0418-2\" 2 3 1";//"D:\file\case-0418-2" 2 3 1

        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(exeDir));
        Process process = null;
        try {
            process = builder.command(para).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setListener() {

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    thread = new Thread() {
                        public void run() {
                            executeCmd("LGSAS " + "\"D:\\file\\case-0418-2\" 2 3 1");
                        }
                    };
                    //SwingUtilities.invokeLater(thread);
                    thread.start();
                    // executeCmd("LGSAS " + "\"D:\\file\\case-0418-2\" 2 3 1");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });


        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    thread.stop();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
//                continues.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (thread == null) {
//                            return;
//                        }
//                        thread.notify();
//
//                    }
//                });


    }


    public void windows() {
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
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //executeCmd("LGSAS " + "\"D:\\file\\case-0418-2\" 2 3 1");
    }
//    public Calculate() {
//
//                stop = new JButton("停止");
//                start = new JButton("开始");
//                continues = new JButton("继续");
//
//
//                label = new JLabel("输出结果", Label.LEFT);
//                label.setHorizontalAlignment(Label.LEFT);
//
//                output = new JTextArea(60, 60);
//                output.setLineWrap(true);
//
//                setListener();
//
//                panel3 = new JPanel();
//                panel3.add(label);
//
//
//                panel = new JPanel();
//                panel.add(start);
//                panel.add(stop);
//                panel.add(continues);
//
//                panel2 = new JPanel();
//                panel2.add(output);
//                scrollPane = new JScrollPane(panel2);
//
//                box = Box.createVerticalBox();
//                box.add(panel);
//                box.add(panel3);
//                box.add(scrollPane);
//                this.add(box);
//                this.setSize(600, 600);
//                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                this.setVisible(true);
//
//                }

    public static void main(String[] args) throws IOException {
        thread2 = new Thread() {
            public void run() {
                new Calculate().windows();
            }
        };
        SwingUtilities.invokeLater(thread2);

    }

}
