package com.lgsim.engine.graphEditor.data.components.library.ptlos.impl;

import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.library.ptlos.ptlos_111.Ptlos_111;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IStencilContextImpl implements IStencilContext {
    @Override
    public List<IVertexStencil> getPredefinedStencils() {
        List<IVertexStencil> list = new ArrayList<IVertexStencil>();
        Ptlos_111 ptlos_111 = new Ptlos_111();
        list.add(ptlos_111);
        return list;
    }

    /*public List<IVertexStencil> getPredefinedStencils(Object object) {
        List<IVertexStencil> list = new ArrayList<IVertexStencil>();
        list.add(object);
        return null;
    }*/

    @Override
    public List<IVertexStencil> getUserDefinedStencils() {
        return null;
    }

    @Override
    public void saveUserDefinedStencil(@NotNull IVertexStencil stencil) {

    }

    @Override
    public IVertexStencil getCavityStencil() {
        return null;
    }
}
