package com.lgsim.engine.graphEditor.app;

import com.lgsim.engine.graphEditor.api.IRegistry;
import com.lgsim.engine.graphEditor.api.data.*;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.data.components.component.Components;
import com.lgsim.engine.graphEditor.data.components.component.ComponentsTest;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexRestrictionImpl;
import com.lgsim.engine.graphEditor.data.components.impl.*;
import com.lgsim.engine.graphEditor.data.components.ptlos.entity.Ptlos;
import com.lgsim.engine.graphEditor.graph.editor.EditorGraph;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.widget.IWidegtImp.TablePanelImp;
import com.lgsim.engine.graphEditor.data.components.library.ptlos.impl.IStencilContextImpl;

class ImplementationRegistry implements IRegistry
{
  static final IRegistry INSTANCE = new ImplementationRegistry();


  @Override
  public void registerAll()
  {
    ImplementationUtil.put(IGraph.class, EditorGraph.class);
    ImplementationUtil.put(IGraphDecoder.class, IGraphDecoderImpl.class);
    ImplementationUtil.put(IGraphEncoder.class, IGraphEncoderImpl.class);
    ImplementationUtil.put(IStencilContext.class, Components.class);
    ImplementationUtil.put(IVertex.class, IVertexImpl.class);
    ImplementationUtil.put(IVertexArgument.class, IVertexArgumentImpl.class);
    ImplementationUtil.put(IVertexOutput.class, IVertexOutputImpl.class);
    ImplementationUtil.put(IVertexRestriction.class, IVertexRestrictionImpl.class);
    ImplementationUtil.put(IVertexStencil.class, Ptlos.class);
    ImplementationUtil.put(IVertexTable.class, TablePanelImp.class);
  }
}
