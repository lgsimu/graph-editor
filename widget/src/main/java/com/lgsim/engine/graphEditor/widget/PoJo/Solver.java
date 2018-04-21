package com.lgsim.engine.graphEditor.widget.PoJo;

import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.widget.Component.SolverOutputPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Solver {
    private Process process;
    private static Thread thread;
    private SolverOutputPanel outputPanel;
    private JScrollPane scrollPane;
    private JTextArea textArea;

    public void setAreaText() {
        textArea = new JTextArea();
        outputPanel = new SolverOutputPanel();
        scrollPane = new JScrollPane();
        Dimension dimension = outputPanel.getMaximumSize();
        textArea.setSize(dimension);
        scrollPane.add(textArea);
        outputPanel.add(scrollPane);
    }

    public void executeCmd(ISolverEnvironment environment) {
        thread = new Thread() {
            public void run() {
                File exeDir = environment.getExecutableFile();//获取求解器的目录
                File inputFile = environment.getSolverInputFile();//获取文件的目录
                String caseName = environment.getCaseName();//文件名称
                String cmdArgument = environment.getSolverCommandlineArguments();//获取命令
                String exeCmd = "LGSAS " + inputFile.toString() + "/" + caseName + " " + cmdArgument;
                Runtime runtime = Runtime.getRuntime();
                try {
                    process = runtime.exec("cmd /c " + exeCmd, null, exeDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader br = null;
                setAreaText();//设置需要输出的面板
                try {
                    br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        textArea.append(line + "\r\n");
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
}
