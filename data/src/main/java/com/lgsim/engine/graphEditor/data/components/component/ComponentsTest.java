package com.lgsim.engine.graphEditor.data.components.component;

import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexRestrictionImpl;
import com.lgsim.engine.graphEditor.data.components.ptlos.entity.Ptlos;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ComponentsTest implements IStencilContext {

    @Override
    public ArrayList<IVertexStencil> getPredefinedStencils() {
        Ptlos ptloss = new Ptlos("","","","","",new IVertexRestrictionImpl(),false,new ArrayList<IVertexArgumentImpl>(),new ArrayList<IVertexOutput>());
        //IVertexRestrictionImpl iVertexRestrictionImpl = new IVertexRestrictionImpl();
        /**
         * '111'类型
         */
        Ptlos ptlos1 = new Ptlos();
        IVertexArgumentImpl iarg11 = new IVertexArgumentImpl("AA","2",0,0,0,"进口面积");
        IVertexArgumentImpl iarg12 = new IVertexArgumentImpl("GEO1","2",0,0,0,"出口面积");
        IVertexArgumentImpl iarg13 = new IVertexArgumentImpl("GEO2","2",0,0,0,"总压损失系数");
        ArrayList<String> pInt1 = new ArrayList<String>();
        ArrayList<String> pOut1 = new ArrayList<String>();
        ArrayList<IVertexArgumentImpl> iargs1 = new ArrayList<IVertexArgumentImpl>();
        ArrayList<IVertexOutput> iouts1 = new ArrayList<IVertexOutput>();

        pInt1.add("1");
        pOut1.add("2");
        iargs1.add(iarg11);
        iargs1.add(iarg12);
        iargs1.add(iarg13);
        /**
         * 元件'1'类型
         */
        Ptlos ptlos2 = new Ptlos();

        IVertexArgumentImpl iarg21 = new IVertexArgumentImpl("GEO1","4",0,0,0,"总压");
        IVertexArgumentImpl iarg22 = new IVertexArgumentImpl("GEO2","24",0,0,0,"总温");
        IVertexArgumentImpl iarg23 = new IVertexArgumentImpl("GEO3","2",0,0,0,"流通面积");
        IVertexArgumentImpl iarg24 = new IVertexArgumentImpl("GEO4","30",0,0,0,"涡量");
        ArrayList<String> pInt2 = new ArrayList<String>();
        ArrayList<String> pOut2 = new ArrayList<String>();
        ArrayList<IVertexArgumentImpl> iargs2 = new ArrayList<IVertexArgumentImpl>();
        ArrayList<IVertexOutput> iouts2 = new ArrayList<IVertexOutput>();

        pOut2.add("2");
        iargs2.add(iarg21);
        iargs2.add(iarg22);
        iargs2.add(iarg23);
        iargs2.add(iarg24);

        /**
         * 元件'2'类型
         */
        Ptlos ptlos3 = new Ptlos();

        IVertexArgumentImpl iarg31 = new IVertexArgumentImpl("GEO1","4",0,0,0,"静压");
        IVertexArgumentImpl iarg32 = new IVertexArgumentImpl("GEO2","24",0,0,0,"总温");
        IVertexArgumentImpl iarg33 = new IVertexArgumentImpl("GEO3","2",0,0,0,"流通面积");
        IVertexArgumentImpl iarg34 = new IVertexArgumentImpl("GEO4","30",0,0,0,"涡量");
        ArrayList<String> pInt3 = new ArrayList<String>();
        ArrayList<String> pOut3 = new ArrayList<String>();
        ArrayList<IVertexArgumentImpl> iargs3 = new ArrayList<IVertexArgumentImpl>();
        ArrayList<IVertexOutput> iouts3 = new ArrayList<IVertexOutput>();

        pInt3.add("1");
        iargs2.add(iarg31);
        iargs2.add(iarg32);
        iargs2.add(iarg33);
        iargs2.add(iarg34);

        /**
         * 往ptlos对象中添加属性
         */
        ptlos1.setName("1");
        ptlos1.setType("111");
        //System.out.println(ptlos1.getID());
        ptlos1.setStencilIcon("com/lgsim/engine/graphEditor/data/testjpg/PTLOS.png");
        ptlos1.setGraphIcon("com/lgsim/engine/graphEditor/data/testjpg/PTLOS.png");
        ptlos1.setRestriction(new IVertexRestrictionImpl(1,1,1,1,pInt1,pOut1));
        ptlos1.setIsPredefined(true);
        ptlos1.setArgumentType("Input");
        ptlos1.setArguments(iargs1);
        //System.out.println(ptlos1.getArguments());
        ptlos1.setOutputs(iouts1);

        ptlos2.setName("IN1");
        ptlos2.setType("1");
        ptlos2.setStencilIcon("com/lgsim/engine/graphEditor/data/testjpg/PSOURCE.png");
        ptlos2.setGraphIcon("com/lgsim/engine/graphEditor/data/testjpg/PSOURCE.png");
        ptlos2.setRestriction(new IVertexRestrictionImpl(0,0,1,1,pInt2,pOut2));
        ptlos2.setIsPredefined(true);
        ptlos2.setArgumentType("Input");
        ptlos2.setArguments(iargs2);
        ptlos2.setOutputs(iouts2);

        ptlos3.setName("OUT21");
        ptlos3.setType("2");
        ptlos3.setStencilIcon("com/lgsim/engine/graphEditor/data/testjpg/PSOURCE.png");
        ptlos3.setGraphIcon("com/lgsim/engine/graphEditor/data/testjpg/PSOURCE.png");
        ptlos3.setRestriction(new IVertexRestrictionImpl(1,1,0,0,pInt3,pOut3));
        ptlos3.setIsPredefined(true);
        ptlos3.setArgumentType("Input");
        ptlos3.setArguments(iargs3);
        ptlos3.setOutputs(iouts3);

        /**
         * 元件集合
         */
        ArrayList<IVertexStencil> list = new ArrayList<IVertexStencil>();
        list.add(ptlos1);
        list.add(ptlos2);
        list.add(ptlos3);
        return list;
    }

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
