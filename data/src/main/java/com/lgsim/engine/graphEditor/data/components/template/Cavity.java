package com.lgsim.engine.graphEditor.data.components.template;

import com.lgsim.engine.graphEditor.api.data.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 腔类
 */
public class Cavity implements IVertex,IVertexStencil {

    private String cavityId;//id
    private String cavityTypeId;//typeid
    private List<IVertex> cavityInputPorts;//输入到该腔节点的端口
    private List<IVertex> cavityOutputPorts;//该腔节点输出的端口
    private String cavityDisplayName;//显示名
    private List<IVertexArgument> cavityInps;//输入参数
    private List<IVertexOutput> cavityOutps;//输出参数

    public Cavity() {
        this.cavityId = "";
        this.cavityTypeId = "";
        this.cavityInputPorts = new ArrayList<>();
        this.cavityOutputPorts = new ArrayList<>();
        this.cavityDisplayName = "";
        this.cavityInps = new ArrayList<>();
        this.cavityOutps = new ArrayList<>();
    }

    public String getCavityId() {
        return cavityId;
    }

    public void setCavityId(String cavityId) {
        this.cavityId = cavityId;
    }

    public String getCavityTypeId() {
        return cavityTypeId;
    }

    public void setCavityTypeId(String cavityTypeId) {
        this.cavityTypeId = cavityTypeId;
    }

    public List<IVertex> getCavityInputPorts() {
        return cavityInputPorts;
    }

    public void setCavityInputPorts(List<IVertex> cavityInputPorts) {
        this.cavityInputPorts = cavityInputPorts;
    }

    public List<IVertex> getCavityOutputPorts() {
        return cavityOutputPorts;
    }

    public void setCavityOutputPorts(List<IVertex> cavityOutputPorts) {
        this.cavityOutputPorts = cavityOutputPorts;
    }

    public String getCavityDisplayName() {
        return cavityDisplayName;
    }

    public void setCavityDisplayName(String cavityDisplayName) {
        this.cavityDisplayName = cavityDisplayName;
    }

    public List<IVertexArgument> getCavityInps() {
        return cavityInps;
    }

    public void setCavityInps(List<IVertexArgument> cavityInps) {
        this.cavityInps = cavityInps;
    }

    public List<IVertexOutput> getCavityOutps() {
        return cavityOutps;
    }

    public void setCavityOutps(List<IVertexOutput> cavityOutps) {
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
        return cavityDisplayName;
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
    public @NotNull String getTypeID() {
        return cavityTypeId;
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

    @Override
    public void setOutputs(@NotNull List<IVertexOutput> outputs) {
        this.cavityOutps = outputs;
    }

    @Override
    public @NotNull List<IVertex> getInputPorts() {
        return cavityInputPorts;
    }

    @Override
    public @NotNull List<IVertex> getOutputPorts() {
        return cavityOutputPorts;
    }

    @Override
    public boolean isCavity() {
        return true;
    }

    @Override
    public boolean isGlobal() {
        return false;
    }

    @Override
    public @NotNull String getDisplayName() {
        return cavityDisplayName;
    }

}
