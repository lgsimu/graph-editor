package com.lgsim.engine.graphEditor.data.components.impl;

import com.lgsim.engine.graphEditor.api.data.*;
import com.lgsim.engine.graphEditor.data.components.ptlos.impl.PtlosImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ComponentImpl implements IStencilContext {

    /**
     * 获取预定义的元件模板
     */
    @Override
    public List<IVertexStencil> getPredefinedStencils() {

        //Ptlos ptlos = new Ptlos("null", "null", "null", "null", "null"，false, ArrayList<Double> arguments, ArrayList<Double> outputs)
        IVertexStencil ptImpl = new PtlosImpl();

        List<IVertexStencil> list = new ArrayList<IVertexStencil>();
        //list.add(ptImpl.isPredefined());
        //list.add(((PtlosImpl) ptImpl).getPtlo());
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
