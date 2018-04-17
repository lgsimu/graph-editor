package com.lgsim.engine.graphEditor.data.components.ptlos.entity;

import com.lgsim.engine.graphEditor.data.components.component.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/4/14.
 * 局部损失元件
 */
public class Ptlos extends Component {

    private boolean isPredefined;//是否是预定义的元件
    private ArrayList<Double> arguments = new ArrayList<Double>();//元素参数
    private ArrayList<Double> outputs = new ArrayList<Double>();//计算后的参数

    public Ptlos() {
    }

    public Ptlos(String name, String type, String stencilIcon, String graphIcon, String restriction, boolean isPredefined, ArrayList<Double> arguments, ArrayList<Double> outputs) {
        super(name, type, stencilIcon, graphIcon, restriction);
        this.isPredefined = isPredefined;
        this.arguments = arguments;
        this.outputs = outputs;
    }

    public boolean getIsPredefined() {
        return isPredefined;
    }

    public void setIsPredefined(boolean isPredefined) {
        this.isPredefined = isPredefined;
    }

    public ArrayList<Double> getArguments() {
        return arguments;
    }

    public void setArguments(ArrayList<Double> arguments) {
        this.arguments = arguments;
    }

    public ArrayList<Double> getOutputs() {
        return outputs;
    }

    public void setOutputs(ArrayList<Double> outputs) {
        this.outputs = outputs;
    }

    /*public String getArmNode1() {
        return armNode1;
    }

    public void setArmNode1(String armNode1) {
        this.armNode1 = armNode1;
    }

    public String getArmNode2() {
        return armNode2;
    }

    public void setArmNode2(String armNode2) {
        this.armNode2 = armNode2;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public double getAa() {
        return aa;
    }

    public void setAa(double aa) {
        this.aa = aa;
    }

    public double getGeo1() {
        return geo1;
    }

    public void setGeo1(double geo1) {
        this.geo1 = geo1;
    }

    public double getGeo2() {
        return geo2;
    }

    public void setGeo2(double geo2) {
        this.geo2 = geo2;
    }*/

    //@Override
    /*public String toString() {
        return "{" +
                "\"Name\":\"" + super.getName() + '\"' +
                ",\"Type\":\"" + super.getType() + '\"' +
                ",\"ArmNode1\":\"" + armNode1 + '\"' +
                ",\"ArmNode2\":\"" + armNode2 + '\"' +
                ",\"FeatureName\":\"" + featureName + '\"' +
                ",\"AA\":\"" + aa + '\"' +
                ",\"GEO1\":\"" + geo1 + '\"' +
                ",\"GEO2\":\"" + geo2 + '\"' +
                '}';
    }*/
}
