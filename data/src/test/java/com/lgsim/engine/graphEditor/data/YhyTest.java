package com.lgsim.engine.graphEditor.data;

import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
//import com.lgsim.engine.graphEditor.data.components.impl.ComponentImpl;
import com.lgsim.engine.graphEditor.data.components.component.Components;
import com.lgsim.engine.graphEditor.data.components.component.ComponentsTest;
import com.lgsim.engine.graphEditor.data.components.util.writejson.WriteJson;

import java.util.ArrayList;

public class YhyTest {
    public static void main(String[] args) throws Exception {

        ComponentsTest com = new ComponentsTest();
        //ArrayList<IVertexStencil> list = com.getPredefinedStencils();

        Components coms = new Components();
        ArrayList<IVertexStencil> list1 = coms.getPredefinedStencils();

        WriteJson wr = new WriteJson();
        String path = "C:\\Users\\admin\\Desktop\\ptlo-111.out";

        wr.writeJson(list1,path);

//        System.out.println(list);
//        System.out.println(list.size());
        System.out.println("---------------------------------");
        System.out.println(list1);


    }
}

