package com.lgsim.engine.graphEditor.data.components.template;

/**
 * 腔输入参数
 */
public class CavityInp {

    private double x;
    private double y;
    private double z;
    private double p;

    public CavityInp() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.p = 0.0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }
}
