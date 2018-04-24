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


    public File getInputFileLoad(ISolverEnvironment environment) {
        File inputFile = null;

        try {

            IGraph graph = environment.getGraph();
            IGraphCodec codec = ImplementationUtil.getInstanceOf(IGraphCodec.class);
            @NotNull byte[] encode = codec.encode(graph);
            inputFile = new File(Files.createTempDir(), "case.dat");
            Files.write(encode, inputFile);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return inputFile;
    }

    public int dealExeCmdException(String exeCmd, Runtime runtime, File exeDir) {
        int status = 0;

        try {
            process = runtime.exec("cmd /c " + exeCmd, null, exeDir);
        } catch (IOException e) {
            status = 1;
            e.printStackTrace();
        }

        BufferedReader br = null;
        //setAreaText();//设置需要输出的面板
        StringBuilder builder = new StringBuilder();
        try {
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line = null;

            while ((line = br.readLine()) != null) {
                // textArea.append(line + "\r\n");
                builder.append(line + "\r\n");
            }
        } catch (UnsupportedEncodingException e) {
            status = 1;
            e.printStackTrace();
        } catch (IOException e) {
            status = 1;
            e.printStackTrace();
        }
       System.out.print(builder);
        return status;
    }

    public Object[] executeCmd(ISolverEnvironment environment) {

        Object[] objects = new Object[2];
        File exeDir = environment.getExecutableFile().getParentFile();//获取求解器的目录
        File inputFile = getInputFileLoad(environment);
        String cmdArgument = environment.getSolverCommandlineArguments();//获取命令

        String outFile = inputFile.toString().replace(".dat", "");
        // outFile.replace(".dat","");
        String exeCmd = "LGSAS " + outFile + " " + "2 3 1";
        Runtime runtime = Runtime.getRuntime();
        int status = dealExeCmdException(exeCmd, runtime, exeDir);
        objects[0] = status;
        objects[1] = outFile;
        return objects;
    }
}

