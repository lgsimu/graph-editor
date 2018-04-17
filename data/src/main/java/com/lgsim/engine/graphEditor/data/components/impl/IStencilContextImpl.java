package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.*;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.ptlos.entity.Ptlos;
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
    public List<IVertexStencil> getPredefinedStencils() {
        Ptlos ptlos = new Ptlos("","","","","",false,new ArrayList<IVertexArgumentImpl>(),new ArrayList<IVertexOutput>());
        //PtlosImpl ptImpl = new PtlosImpl();

        List<IVertexStencil> list = new ArrayList<IVertexStencil>();
        list.add(ptlos);
        return list;
    }

    /**
     * 获取用户定义的元件模板
     */
    @Override
    public List<IVertexStencil> getUserDefinedStencils() {
        return null;
    }

    /**
     * 保存用户定义的元件模板
     */
    @Override
    public void saveUserDefinedStencil(@NotNull IVertexStencil stencil) {

    }
}
