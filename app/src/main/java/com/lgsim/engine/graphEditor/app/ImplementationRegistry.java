package com.lgsim.engine.graphEditor.app;

import com.lgsim.engine.graphEditor.api.IRegistry;
import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.data.*;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.api.widget.console.IConsole;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.data.components.impl.GraphCodecImpl;
import com.lgsim.engine.graphEditor.data.components.impl.IGraphImpl;
import com.lgsim.engine.graphEditor.data.components.template.*;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.widget.IWidegtImp.ISolverImp;
import com.lgsim.engine.graphEditor.widget.IWidegtImp.SolverPanelImp;
import com.lgsim.engine.graphEditor.widget.IWidegtImp.TablePanelImp;

class ImplementationRegistry implements IRegistry
{
  static final IRegistry INSTANCE = new ImplementationRegistry();


  @Override
  public void registerAll()
  {
    //ImplementationUtil.put(IGraph.class, Graph.class);
    ImplementationUtil.put(IGraph.class, IGraphImpl.class);
    ImplementationUtil.put(IGraphCodec.class, GraphCodecImpl.class);
    //ImplementationUtil.put(IStencilContext.class, ComponentsTest.class);
//    ImplementationUtil.put(IStencilContext.class, Template.class);
    ImplementationUtil.put(IStencilContext.class, Element.class);
    ImplementationUtil.put(IVertex.class, VertexImpl.class);
    //ImplementationUtil.put(IVertexArgument.class, IVertexArgumentImpl.class);
    ImplementationUtil.put(IVertexArgument.class, Parameter.class);
    //ImplementationUtil.put(IVertexOutput.class, IVertexOutputImpl.class);
    ImplementationUtil.put(IVertexOutput.class, Parameter.class);
    //ImplementationUtil.put(IVertexRestriction.class, IVertexRestrictionImpl.class);
    ImplementationUtil.put(IVertexRestriction.class, ComponentArm.class);
    //ImplementationUtil.put(IVertexStencil.class, Ptlos.class);
    ImplementationUtil.put(IVertexStencil.class, Component.class);
    ImplementationUtil.put(IVertexTable.class, TablePanelImp.class);
    ImplementationUtil.put(IConsole.class, SolverPanelImp.class);
    ImplementationUtil.put(ISolver.class, ISolverImp.class);
  }
}
