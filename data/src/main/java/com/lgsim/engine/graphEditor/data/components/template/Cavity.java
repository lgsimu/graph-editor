package com.lgsim.engine.graphEditor.data.components.template;

import java.util.ArrayList;
import java.util.List;

/**
 * 腔类
 */
public class Cavity {

    private List<CavityInp> cavityInps;//输入参数
    private List<CavityOutp> cavityOutps;//输出参数

    public Cavity() {
        this.cavityInps = new ArrayList<>();
        this.cavityOutps = new ArrayList<>();
    }

    public List<CavityInp> getCavityInps() {
        return cavityInps;
    }

    public void setCavityInps(List<CavityInp> cavityInps) {
        this.cavityInps = cavityInps;
    }

    public List<CavityOutp> getCavityOutps() {
        return cavityOutps;
    }

    public void setCavityOutps(List<CavityOutp> cavityOutps) {
        this.cavityOutps = cavityOutps;
    }
}
