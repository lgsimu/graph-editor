package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexRestrictionImpl;
import com.lgsim.engine.graphEditor.data.components.ptlos.entity.Ptlos;
import com.lgsim.engine.graphEditor.data.components.template.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 *IStencilContext实现类
 */
public class IStencilContextImpl implements IStencilContext {

    /**
     * 获取预定义的元件模板
     */
    @Override
    public @NotNull List<IVertexStencil> getPredefinedStencils() {
        Ptlos ptlos = new Ptlos("","","","","",new IVertexRestrictionImpl(),false,new ArrayList<IVertexArgumentImpl>(),new ArrayList<IVertexOutput>());
        //PtlosImpl ptImpl = new PtlosImpl();

        List<IVertexStencil> list = new ArrayList<IVertexStencil>();
        list.add(ptlos);
        return list;
    }

    /**
     * 获取用户定义的元件模板
     */
    @Override
    public @NotNull List<IVertexStencil> getUserDefinedStencils() {
        return new ArrayList<IVertexStencil>();
    }

    /**
     * 保存用户定义的元件模板
     */
    @Override
    public void saveUserDefinedStencil(@NotNull IVertexStencil stencil) {

    }

    @Override
    public @NotNull IVertexStencil getCavityStencil() {
        return null;
    }

    @Override
    public @NotNull IVertexStencil getGlobalStencil() {
        return new Component();
    }
}
