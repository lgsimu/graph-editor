package com.lgsim.engine.graphEditor.widget.PoJo;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphCodec;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.widget.Component.SolverOutputPanel;
import org.jetbrains.annotations.NotNull;

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

    public int executeCmd(ISolverEnvironment environment) {
        final int[] status = {0};
        thread = new Thread() {
            public void run() {
                File exeFile = environment.getExecutableFile();//获取求解器的目录
                File exeDir = exeFile.getParentFile();
                File outFile = null;
                try {
                    IGraph graph = environment.getGraph();
                    IGraphCodec codec = ImplementationUtil.getInstanceOf(IGraphCodec.class);
                    @NotNull byte[] encode = codec.encode(graph);
                    outFile = new File(Files.createTempDir(), "tmp.out");
                    Files.write(encode, outFile);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                // String caseName = environment.getCaseName();//文件名称
                String cmdArgument = environment.getSolverCommandlineArguments();//获取命令
                String exeCmd = "LGSAS " + outFile.toString() + " " + cmdArgument;
                Runtime runtime = Runtime.getRuntime();

                try {
                    process = runtime.exec("cmd /c " + exeCmd, null, exeDir);
                } catch (IOException e) {
                    status[0] = 1;
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
                    status[0] = 1;
                    e.printStackTrace();
                } catch (IOException e) {
                    status[0] = 1;
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        return status[0];
    }
}

