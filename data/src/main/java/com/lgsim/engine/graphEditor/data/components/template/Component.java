package com.lgsim.engine.graphEditor.data.components.template;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 元件模板
 */
public class Component implements IVertexStencil {

    private boolean isPredefined;//是否是当前定义元件
    private String componentTemplateName;//元件模板名称
    private String componentName;//元件名称
    private String componentType;//元件类型ID
    private String imageSource;//图片所在路径
    private List<Parameter> arguments;//输入参数
    private List<Parameter> outputs;//输出参数
    private ComponentArm componentArms;//元件端口

    public Component() {
        this.isPredefined = false;
        this.componentTemplateName = "";
        this.componentName = "";
        this.componentType = "";
        this.imageSource = "";
        this.arguments = new ArrayList<Parameter>();
        this.outputs = new ArrayList<Parameter>();
        this.componentArms = new ComponentArm();
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

    public void setArguments(List<Parameter> arguments) {
        this.arguments = arguments;
    }

    public void setOutputs(List<Parameter> outputs) {
        this.outputs = outputs;
    }

    public ComponentArm getComponentArms() {
        return componentArms;
    }

    public void setComponentArms(ComponentArm componentArms) {
        this.componentArms = componentArms;
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
    public @NotNull String getName() {
        return componentName;
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
        List<IVertexArgument> arguments = new ArrayList<IVertexArgument>();
        Parameter parameter = new Parameter();
        arguments.add(parameter);

        return arguments;
    }

    @Override
    public @NotNull List<IVertexOutput> getOutputs() {
        List<IVertexOutput> outputs = new ArrayList<IVertexOutput>();
        Parameter parameter = new Parameter();
        outputs.add(parameter);

        return outputs;
    }

    @Override
    public @NotNull IVertexRestriction getRestriction() {
        return componentArms;
    }
}
