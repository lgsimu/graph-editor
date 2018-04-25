package com.lgsim.engine.graphEditor.app;

import com.lgsim.engine.graphEditor.api.IRegistry;
import com.lgsim.engine.graphEditor.api.IToolbar;
import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.data.*;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.api.widget.console.IConsole;
import com.lgsim.engine.graphEditor.api.widget.table.IVertexTable;
import com.lgsim.engine.graphEditor.api.widget.topLevel.ITopLevelMenuBar;
import com.lgsim.engine.graphEditor.data.components.impl.GraphCodecImpl;
import com.lgsim.engine.graphEditor.data.components.impl.IGraphImpl;
import com.lgsim.engine.graphEditor.data.components.template.Component;
import com.lgsim.engine.graphEditor.data.components.template.ComponentArm;
import com.lgsim.engine.graphEditor.data.components.template.Element;
import com.lgsim.engine.graphEditor.data.components.template.Parameter;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.widget.IWidegtImp.*;

class ImplementationRegistry implements IRegistry
{
  static final IRegistry INSTANCE = new ImplementationRegistry();


  @Override
  public void registerAll()
  {
    //ImplementationUtil.put(IGraph.class, Graph.class);
    ImplementationUtil.putType(IGraph.class, IGraphImpl.class);
    ImplementationUtil.putType(IGraphCodec.class, GraphCodecImpl.class);
    //ImplementationUtil.put(IStencilContext.class, ComponentsTest.class);
//    ImplementationUtil.put(IStencilContext.class, Template.class);
    ImplementationUtil.putType(IStencilContext.class, Element.class);
    ImplementationUtil.putType(IVertex.class, VertexImpl.class);
    //ImplementationUtil.put(IVertexArgument.class, IVertexArgumentImpl.class);
    ImplementationUtil.putType(IVertexArgument.class, Parameter.class);
    //ImplementationUtil.put(IVertexOutput.class, IVertexOutputImpl.class);
    ImplementationUtil.putType(IVertexOutput.class, Parameter.class);
    //ImplementationUtil.put(IVertexRestriction.class, IVertexRestrictionImpl.class);
    ImplementationUtil.putType(IVertexRestriction.class, ComponentArm.class);
    //ImplementationUtil.put(IVertexStencil.class, Ptlos.class);
    ImplementationUtil.putType(IVertexStencil.class, Component.class);
    ImplementationUtil.putType(IVertexTable.class, TablePanelImp.class);
    ImplementationUtil.putType(IConsole.class, SolverPanelImp.class);
    ImplementationUtil.putType(ISolver.class, ISolverImp.class);
    ImplementationUtil.putType(ITopLevelMenuBar.class, IMenuBarImp.class);
    ImplementationUtil.putType(IToolbar.class, IToolBarImp.class);
  }
}
