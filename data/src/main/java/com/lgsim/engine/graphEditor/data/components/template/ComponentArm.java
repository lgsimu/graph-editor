package com.lgsim.engine.graphEditor.data.components.template;

import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 元件端口
 */
public class ComponentArm implements IVertexRestriction {

    private String componentArmName;//元件端口名称
    private String componentArmNodeName;//元件端口连接的节点名称
    private int minInputPortCount;//最小连入端口数
    private int maxInputPortCount;//最大连入端口数
    private int minOutputPortCount;//最小连出端口数
    private int maxOutputPortCount;//最大连出端口数
    private ArrayList<String> inputPortTypes = new ArrayList<String>();//连入端口类型
    private ArrayList<String> outputPortTypes = new ArrayList<String>();//连出端口类型

    public ComponentArm() {
        this.componentArmName = "";
        this.componentArmNodeName = "";
        this.minInputPortCount = 0;
        this.maxInputPortCount = 0;
        this.minOutputPortCount = 0;
        this.maxOutputPortCount = 0;
        this.inputPortTypes = new ArrayList<String>();
        this.outputPortTypes = new ArrayList<String>();
    }

    public String getComponentArmName() {
        return componentArmName;
    }

    public void setComponentArmName(String componentArmName) {
        this.componentArmName = componentArmName;
    }

    public String getComponentArmNodeName() {
        return componentArmNodeName;
    }

    public void setComponentArmNodeName(String componentArmNodeName) {
        this.componentArmNodeName = componentArmNodeName;
    }

    public void setMinInputPortCount(int minInputPortCount) {
        this.minInputPortCount = minInputPortCount;
    }

    public void setMaxInputPortCount(int maxInputPortCount) {
        this.maxInputPortCount = maxInputPortCount;
    }

    public void setMinOutputPortCount(int minOutputPortCount) {
        this.minOutputPortCount = minOutputPortCount;
    }

    public void setMaxOutputPortCount(int maxOutputPortCount) {
        this.maxOutputPortCount = maxOutputPortCount;
    }

    public void setInputPortTypes(ArrayList<String> inputPortTypes) {
        this.inputPortTypes = inputPortTypes;
    }

    public void setOutputPortTypes(ArrayList<String> outputPortTypes) {
        this.outputPortTypes = outputPortTypes;
    }

    @Override
    public int getMinInputPortCount() {
        return minInputPortCount;
    }

    @Override
    public int getMaxInputPortCount() {
        return maxInputPortCount;
    }

    @Override
    public int getMinOutputPortCount() {
        return minOutputPortCount;
    }

    @Override
    public int getMaxOutputPortCount() {
        return maxOutputPortCount;
    }

    @Override
    public @NotNull List<String> getInputPortTypes() {
        return inputPortTypes;
    }

    @Override
    public @NotNull List<String> getOutputPortTypes() {
        return outputPortTypes;
    }
}
