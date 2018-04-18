package com.lgsim.engine.graphEditor.data.components.component;

import com.lgsim.engine.graphEditor.api.data.IVertexRestriction;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexRestrictionImpl;

/**
 * Created by admin on 2018/4/14.
 * 元件父类
 */
public abstract class Component {

    private String name;//元件名
    private String type;//元件类型
    private String stencilIcon;//元件模板图标
    private String graphIcon;//元件模板在图中的图标
    private IVertexRestriction restriction;//连接限制

    public Component() {
    }

    public Component(String name, String type, String stencilIcon, String graphIcon, IVertexRestriction restriction) {
        this.name = name;
        this.type = type;
        this.stencilIcon = stencilIcon;
        this.graphIcon = graphIcon;
        this.restriction = restriction;
    }

    /**
     * name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * type
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取元件模板图标
     */
    public String getStencilIcon() {
        return stencilIcon;
    }

    public void setStencilIcon(String stencilIcon) {
        this.stencilIcon = stencilIcon;
    }

    /**
     * 获取元件模板图标中的图标
     */
    public String getGraphIcon() {
        return graphIcon;
    }

    public void setGraphIcon(String graphIcon) {
        this.graphIcon = graphIcon;
    }

    /**
     * 连接限制
     */
    public IVertexRestriction getRestriction() {
        //IVertexRestrictionImpl iVertexRestrictionImpl = new IVertexRestrictionImpl();
        return restriction;
    }

    public void setRestriction(IVertexRestriction restriction) {
        this.restriction = restriction;
    }
}
