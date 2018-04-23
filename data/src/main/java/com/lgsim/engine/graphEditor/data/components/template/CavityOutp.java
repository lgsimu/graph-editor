package com.lgsim.engine.graphEditor.data.components.template;

/**
 * 腔类输出参数
 */
public class CavityOutp {

    private double Tt;
    private double Tp;

    public CavityOutp() {
        this.Tt = 0.0;
        this.Tp = 0.0;
    }

    public double getTt() {
        return Tt;
    }

    public void setTt(double tt) {
        Tt = tt;
    }

    public double getTp() {
        return Tp;
    }

    public void setTp(double tp) {
        Tp = tp;
    }
}
