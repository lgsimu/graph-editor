package com.lgsim.engine.graphEditor.data.components.template;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 腔类
 */
public class Cavity implements IVertexStencil {

    private String cavityId;//id
    private String cavityName;//name

    private List<Parameter> cavityInps;//输入参数
    private List<Parameter> cavityOutps;//输出参数

    public Cavity() {
        this.cavityId = "";
        this.cavityName = "";
        this.cavityInps = new ArrayList<>();
        this.cavityOutps = new ArrayList<>();
    }

    public String getCavityId() {
        return cavityId;
    }

    public void setCavityId(String cavityId) {
        this.cavityId = cavityId;
    }

    public String getCavityName() {
        return cavityName;
    }

    public void setCavityName(String cavityName) {
        this.cavityName = cavityName;
    }

    public List<Parameter> getCavityInps() {
        return cavityInps;
    }

    public void setCavityInps(List<Parameter> cavityInps) {
        this.cavityInps = cavityInps;
    }

    public List<Parameter> getCavityOutps() {
        return cavityOutps;
    }

    public void setCavityOutps(List<Parameter> cavityOutps) {
        this.cavityOutps = cavityOutps;
    }

    @Override
    public boolean isPredefined() {
        return true;
    }

    @Override
    public @NotNull String getID() {
        return cavityId;
    }

    @Override
    public @NotNull String getName() {
        return cavityName;
    }

    @Override
    public @NotNull String getStencilIcon() {
        return "";
    }

    @Override
    public @NotNull String getGraphIcon() {
        return "";
    }

    @Override
    public @NotNull List<IVertexArgument> getArguments() {
        List<IVertexArgument> cavityInps = new ArrayList<IVertexArgument>();
        Parameter parameter1 = new Parameter("x","1",0.0,"");
        Parameter parameter2 = new Parameter("y","1",0.0,"");
        Parameter parameter3 = new Parameter("z","1",0.0,"");
        Parameter parameter4 = new Parameter("p","4",0.0,"");

        cavityInps.add(parameter1);
        cavityInps.add(parameter2);
        cavityInps.add(parameter3);
        cavityInps.add(parameter4);
        return cavityInps;
    }

    @Override
    public @NotNull List<IVertexOutput> getOutputs() {
        List<IVertexOutput> cavityOutps = new ArrayList<>();
        Parameter parameter1 = new Parameter("TT","24",0.0,"");
        Parameter parameter2 = new Parameter("TP","4",0.0,"");

        cavityOutps.add(parameter1);
        cavityOutps.add(parameter2);
        return cavityOutps;
    }

    @Override
    public @NotNull IVertexRestriction getRestriction() {
        return new ComponentArm();
    }
}
