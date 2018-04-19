package com.lgsim.engine.graphEditor.data.components.entity;

import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;

import java.util.ArrayList;

/**
 * 连接限制类
 */
public class IVertexRestrictionImpl implements IVertexRestriction {

    private int minInputPortCount;//最小连入端口数
    private int maxInputPortCount;//最大连入端口数
    private int minOutputPortCount;//最小连出端口数
    private int maxOutputPortCount;//最大连出端口数
    private ArrayList<String> inputPortTypes = new ArrayList<String>();//连入端口类型
    private ArrayList<String> outputPortTypes = new ArrayList<String>();//连出端口类型

    public IVertexRestrictionImpl() {
    }

    public IVertexRestrictionImpl(int minInputPortCount, int maxInputPortCount, int minOutputPortCount, int maxOutputPortCount, ArrayList<String> inputPortTypes, ArrayList<String> outputPortTypes) {
        this.minInputPortCount = minInputPortCount;
        this.maxInputPortCount = maxInputPortCount;
        this.minOutputPortCount = minOutputPortCount;
        this.maxOutputPortCount = maxOutputPortCount;
        this.inputPortTypes = inputPortTypes;
        this.outputPortTypes = outputPortTypes;
    }

    /**
     * 最小连入端口数
     */
    public int getMinInputPortCount() {
        return minInputPortCount;
    }

    public void setMinInputPortCount(int minInputPortCount) {
        this.minInputPortCount = minInputPortCount;
    }

    /**
     * 最大连入端口数
     */
    public int getMaxInputPortCount() {
        return maxInputPortCount;
    }

    public void setMaxInputPortCount(int maxInputPortCount) {
        this.maxInputPortCount = maxInputPortCount;
    }

    /**
     * 最小连出端口数
     */
    public int getMinOutputPortCount() {
        return minOutputPortCount;
    }

    public void setMinOutputPortCount(int minOutputPortCount) {
        this.minOutputPortCount = minOutputPortCount;
    }

    /**
     * 最大连出端口数
     */
    public int getMaxOutputPortCount() {
        return maxOutputPortCount;
    }

    public void setMaxOutputPortCount(int maxOutputPortCount) {
        this.maxOutputPortCount = maxOutputPortCount;
    }

    /**
     * 连入端口类型
     */
    public ArrayList<String> getInputPortTypes() {
        return inputPortTypes;
    }

    public void setInputPortTypes(ArrayList<String> inputPortTypes) {
        this.inputPortTypes = inputPortTypes;
    }

    /**
     * 连出端口类型
     */
    public ArrayList<String> getOutputPortTypes() {
        return outputPortTypes;
    }

    public void setOutputPortTypes(ArrayList<String> outputPortTypes) {
        this.outputPortTypes = outputPortTypes;
    }

    @Override
    /*public String toString() {
        return "{" +
                "\"minInputPortCount\":\"" + minInputPortCount + '\"' +
                ",\"maxInputPortCount\":\"" + maxInputPortCount + '\"' +
                ",\"minOutputPortCount\":\"" + minOutputPortCount + '\"' +
                ",\"maxOutputPortCount\":\"" + maxOutputPortCount + '\"' +
                ",\"inputPortTypes\":" + inputPortTypes +
                ",\"outputPortTypes\":" + outputPortTypes +
                '}';
    }*/

    public String toString() {
        return "{" +
                "\"inputPortTypes\":" + inputPortTypes +
                ",\"outputPortTypes\":" + outputPortTypes +
                '}';
    }
}
