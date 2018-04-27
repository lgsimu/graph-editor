package com.lgsim.engine.graphEditor.widget.PoJo;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphCalcCodec;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.widget.Component.SolverOutputPanel;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import org.slf4j.Logger;

public class Solver {

    private SolverOutputPanel outputPanel;

    private JScrollPane scrollPane;

    private JTextArea textArea;

    private Logger log = LoggerFactory.getLogger(Solver.class);


    public void setAreaText() {

        textArea = new JTextArea();
        outputPanel = new SolverOutputPanel();
        scrollPane = new JScrollPane();
        Dimension dimension = outputPanel.getMaximumSize();
        textArea.setSize(dimension);
        scrollPane.add(textArea);
        outputPanel.add(scrollPane);
    }


    private File getInputFileLoad(ISolverEnvironment environment) {

        File inputFile = null;

        try {

            IGraph graph = environment.getGraph();
            IGraphCalcCodec codec = ImplementationUtil.getInstanceOf(IGraphCalcCodec.class);
            @NotNull byte[] encode = codec.encode(graph);
            inputFile = new File(Files.createTempDir(), "case.inp");
            Files.write(encode, inputFile);

        } catch (IOException e) {

            ExceptionManager.INSTANCE.dealWith(e);
        } catch (InstantiationException e) {

            ExceptionManager.INSTANCE.dealWith(e);
        }

        return inputFile;
    }

    private int dealExeCmdException(String exeCmd, Runtime runtime, File exeDir) {

        Process process = null;
        int status = 0;

        try {

            process = runtime.exec("cmd /c " + exeCmd, null, exeDir);

            try {
                process.waitFor();
            } catch (InterruptedException e) {

                ExceptionManager.INSTANCE.dealWith(e);
            }
        } catch (IOException e) {

            status = 1;

            ExceptionManager.INSTANCE.dealWith(e);
        }

        BufferedReader br;

        setAreaText();//设置需要输出的面板

        try {

            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

            String line;

            while ((line = br.readLine()) != null) {

                //textArea.append(line + "\r\n");

            }
        } catch (UnsupportedEncodingException e) {

            status = 1;
            ExceptionManager.INSTANCE.dealWith(e);
        } catch (IOException e) {

            status = 1;
            ExceptionManager.INSTANCE.dealWith(e);
        }

        return status;
    }

    public Object[] executeCmd(ISolverEnvironment environment) {

        Object[] objects = new Object[2];

        File exeDir = environment.getExecutableFile().getParentFile();//获取求解器的目录
        File inputFile = getInputFileLoad(environment);
        String cmdArgument = environment.getSolverCommandlineArguments();//获取命令
        String outFile = inputFile.toString().replace(".dat", "");
        String exeCmd = "LGSAS " + outFile + " " + "2 3 1";

        Runtime runtime = Runtime.getRuntime();

        int status = dealExeCmdException(exeCmd, runtime, exeDir);

        objects[0] = status;
        objects[1] = outFile;

        return objects;
    }
}

