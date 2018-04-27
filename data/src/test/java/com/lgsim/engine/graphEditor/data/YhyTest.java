package com.lgsim.engine.graphEditor.data;

import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.template.Element;
import com.lgsim.engine.graphEditor.data.components.util.readjson.ReadJson;
import com.lgsim.engine.graphEditor.data.components.util.writejson.WriteJson;

import java.util.ArrayList;
import java.util.List;

//import com.lgsim.engine.graphEditor.data.components.impl.ComponentImpl;
//import net.sf.json.JSONObject;

public class YhyTest {
    public static void main(String[] args) throws Exception {

        List<IVertexStencil> list = new ArrayList<>();
        Element element = new Element();
        list = element.getPredefinedStencils();
        WriteJson wr = new WriteJson();

        String pathOut = "C:\\Users\\admin\\Desktop\\ptlo-112.out";
        String pathInt = "com/lgsim/engine/graphEditor/data/simpleCase.inp";

        wr.writeJson(list,pathOut);
        System.out.println(element.getPredefinedStencils().get(0).getArguments());
        System.out.println("------------------------------------");
        //System.out.println(element.getPredefinedStencils().get(0).getArguments().get(2).getDescription());
        System.out.println("------------------------------------");
        System.out.println(element.getCavityStencil().getArguments().get(2).getID());
        /*Component component = new Component();
        System.out.println(component.toString());*/
        System.out.println("---------------------------------");
        ReadJson readJson = new ReadJson();
        readJson.readJson(pathInt);
        System.out.println("---------------------------------");
        //System.out.println(str);
        //System.out.println(components);



    }
}

