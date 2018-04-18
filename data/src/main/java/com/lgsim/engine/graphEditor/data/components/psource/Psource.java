package com.lgsim.engine.graphEditor.data.components.psource;

import com.lgsim.engine.graphEditor.data.components.component.Component;

/**
 * Created by admin on 2018/4/14.
 */
public class Psource extends Component {

    private String armNode1;//端口1
    private String featureName;//特征名
    private double geo1;//压力
    private double geo2;//总温
    private double geo3;//流通面积
    private double geo4;//涡量

    public String getArmNode1() {
        return armNode1;
    }

    public void setArmNode1(String armNode1) {
        this.armNode1 = armNode1;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
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
    }

    public double getGeo3() {
        return geo3;
    }

    public void setGeo3(double geo3) {
        this.geo3 = geo3;
    }

    public double getGeo4() {
        return geo4;
    }

    public void setGeo4(double geo4) {
        this.geo4 = geo4;
    }

    @Override
    public String toString() {
        return "{" +
                "\"Name\":\"" + super.getName() + '\"' +
                ",\"Type\":\"" + super.getType() + '\"' +
                ",\"ArmNode1\":\"" + armNode1 + '\"' +
                ",\"FeatureName\":\"" + featureName + '\"' +
                ",\"GEO1\":\"" + geo1 + '\"' +
                ",\"GEO2\":\"" + geo2 + '\"' +
                ",\"GEO3\":\"" + geo3 + '\"' +
                ",\"GEO4\":\"" + geo4 + '\"' +
                '}';
    }
}
