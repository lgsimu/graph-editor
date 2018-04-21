package com.lgsim.engine.graphEditor.data;

import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
//import com.lgsim.engine.graphEditor.data.components.impl.ComponentImpl;
import com.lgsim.engine.graphEditor.data.components.component.Components;
import com.lgsim.engine.graphEditor.data.components.component.ComponentsTest;
import com.lgsim.engine.graphEditor.data.components.library.ptlos.ptlos_111.Ptlos_111;
import com.lgsim.engine.graphEditor.data.components.template.Component;
import com.lgsim.engine.graphEditor.data.components.template.Template;
import com.lgsim.engine.graphEditor.data.components.util.writejson.WriteJson;

import java.util.ArrayList;
import java.util.List;

public class YhyTest {
    public static void main(String[] args) throws Exception {

        Template template = new Template();
        List<IVertexStencil> list = new ArrayList<IVertexStencil>();

        list = template.getPredefinedStencils();

        WriteJson wr = new WriteJson();
        String path = "C:\\Users\\admin\\Desktop\\ptlo-111.out";
        wr.writeJson(list,path);

        /*Ptlos_111 ptlos_111 = new Ptlos_111();

        System.out.println(ptlos_111.getType());
        System.out.println(ptlos_111.getArguments());*/

        System.out.println(list.size());
        System.out.println(wr);
//        System.out.println(list.size());
        System.out.println("---------------------------------");
        //System.out.println(list1);


    }
}

