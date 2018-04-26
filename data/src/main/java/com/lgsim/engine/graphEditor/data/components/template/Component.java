package com.lgsim.engine.graphEditor.data.components.template;

import com.lgsim.engine.graphEditor.api.data.*;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 元件模板
 */
public class Component extends VertexImpl implements IVertexStencil, IVertex {

    private boolean isPredefined;//是否是当前定义元件
    private String componentTemplateName;//元件模板名称
    private String componentName;//元件名称
    private String componentType;//元件类型ID
    private String imageSource;//图片所在路径
    private List<IVertexArgument> arguments;//输入参数
    private List<IVertexOutput> outputs;//输出参数
    private ComponentArm componentArms;//元件端口
    private List<String> armnodes = new ArrayList<>();//端口集合
    private List<String> feature = new ArrayList<>();//端口集合
    private List<IVertex> componentInputPorts;//输入到该节点的端口
    private List<IVertex> componentOutputPorts;//该节点输出的端口
    private String featureName;//参数特点name

    public Component() {
        this.isPredefined = false;
        this.componentTemplateName = "";
        this.componentName = "";
        this.componentType = "";
        this.imageSource = "";
        this.arguments = new ArrayList<IVertexArgument>();
        this.outputs = new ArrayList<IVertexOutput>();
        this.componentArms = new ComponentArm();
        this.armnodes.add(componentArms.getComponentArmNodeName());
        this.componentInputPorts = new ArrayList<>();
        this.componentOutputPorts = new ArrayList<>();
    }

    public Component(Component component) {
        this.isPredefined = component.isPredefined;
        this.componentTemplateName = component.componentTemplateName;
        this.componentName = component.componentName;
        this.componentType = component.componentType;
        this.imageSource = component.imageSource;
        this.arguments = component.arguments;
        this.outputs = component.outputs;
        this.componentArms = component.componentArms;
        this.componentInputPorts = component.componentInputPorts;
        this.componentOutputPorts = component.componentOutputPorts;
    }

    public void setPredefined(boolean predefined) {
        isPredefined = predefined;
    }

    public String getComponentTemplateName() {
        return componentTemplateName;
    }

    public void setComponentTemplateName(String componentTemplateName) {
        this.componentTemplateName = componentTemplateName;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }


    @Override
    public void setArguments(@NotNull List<IVertexArgument> arguments) {
        this.arguments = arguments;
    }


    public ComponentArm getComponentArms() {
        return componentArms;
    }

    public void setComponentArms(ComponentArm componentArms) {
        this.componentArms = componentArms;
    }

    public List<String> getArmnodes() {
        return armnodes;
    }

    public void setArmnodes(List<String> armnodes) {
        this.armnodes = armnodes;
    }

    public List<String> getFeature() {
        return feature;
    }

    public void setFeature(List<String> feature) {
        this.feature = feature;
    }

    public List<IVertex> getComponentInputPorts() {
        return componentInputPorts;
    }

    public void setComponentInputPorts(List<IVertex> componentInputPorts) {
        this.componentInputPorts = componentInputPorts;
    }

    public List<IVertex> getComponentOutputPorts() {
        return componentOutputPorts;
    }

    public void setComponentOutputPorts(List<IVertex> componentOutputPorts) {
        this.componentOutputPorts = componentOutputPorts;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    @Override
    public boolean isPredefined() {
        return isPredefined;
    }

    @Override
    public @NotNull String getID() {
        return componentType;
    }

    @Override
    public @NotNull String getTypeID() {
        return componentType;
    }

    @Override
    public @NotNull String getName() {
        return componentTemplateName;
    }

    @Override
    public @NotNull String getStencilIcon() {
        return imageSource;
    }

    @Override
    public @NotNull String getGraphIcon() {
        return imageSource;
    }

    @Override
    public @NotNull List<IVertexArgument> getArguments() {
        /*List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        Parameter parameter = new Parameter();
        arguments.add(parameter);*/

        return arguments;
    }

    @Override
    public @NotNull List<IVertexOutput> getOutputs() {
        /*List<IVertexOutput> outputs = new ArrayList<IVertexOutput>();
        Parameter parameter = new Parameter();
        outputs.add(parameter);*/

        return outputs;
    }

    @Override
    public void setOutputs(@NotNull List<IVertexOutput> outputs) {
        this.outputs = outputs;
    }

    @Override
    public @NotNull List<IVertex> getInputPorts() {
        return componentInputPorts;
    }

    @Override
    public @NotNull List<IVertex> getOutputPorts() {
        return componentOutputPorts;
    }

    @Override
    public boolean isCavity() {
        return false;
    }

    @Override
    public @NotNull String getDisplayName() {
        return componentName;
    }

    @Override
    public @NotNull IVertexRestriction getRestriction() {
        return componentArms;
    }

    /*public String toString() {
        return  //"{\"Component\":" +
                "{" +
                        "\"Name\":\"" + componentName + '\"' +
                        ",\"Type\":\"" + componentType + '\"' +
                        ",\"ArmNodes\":" + armnodes +
                        ",\"Feature\":[{" + "\"Name\":\"" + arguments.get(0).getParameterIOType() + '\"' +
                        ",\"Value\":" + arguments.get(0).getValues() + "}]" +
                        //'}' +
                        '}' ;
    }*/

    @Override
    public String toString() {
        return "{" +
                "\"Name\":\"" + componentName + '\"' +
                ",\"Type\":\"" + componentType + '\"' +
                ",\"ArmNodes\":" + armnodes +
                ",\"Feature\":" + feature +
                '}';
    }

    /*@Override
    public String toString() {
        return "Component{" +
                "isPredefined=" + isPredefined +
                ", componentTemplateName='" + componentTemplateName + '\'' +
                ", componentName='" + componentName + '\'' +
                ", componentType='" + componentType + '\'' +
                ", imageSource='" + imageSource + '\'' +
                ", arguments=" + arguments +
                ", outputs=" + outputs +
                ", componentArms=" + componentArms +
                ", armnodes=" + armnodes +
                ", feature=" + feature +
                ", componentInputPorts=" + componentInputPorts +
                ", componentOutputPorts=" + componentOutputPorts +
                '}';
    }*/
}
