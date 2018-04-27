package com.lgsim.engine.graphEditor.data;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.data.components.util.readjson.ReadJson;
import com.lgsim.engine.graphEditor.data.components.util.writejson.WriteJson;

import java.util.ArrayList;
import java.util.Collection;

//import com.lgsim.engine.graphEditor.data.components.impl.ComponentImpl;
//import net.sf.json.JSONObject;

public class YhyTest {
    public static void main(String[] args) throws Exception {

        Collection<IVertex> list = new ArrayList<>();
        Collection<IVertex> list1 = new ArrayList<>();
        //Element element = new Element();
        //list = element.getPredefinedStencils();
        WriteJson wr = new WriteJson();
        ReadJson rj = new ReadJson();


        String pathOut = "com/lgsim/engine/graphEditor/data/simpleCase.out";
        String pathInt = "C:\\Users\\admin\\Desktop\\test\\simpleCase.out";
        list1 = rj.readJson(pathOut);
        //wr.writeJson(list,pathInt);
        //System.out.println(element.getPredefinedStencils().get(0).getArguments());
        System.out.println("------------------------------------");
        //System.out.println(element.getPredefinedStencils().get(0).getArguments().get(2).getDescription());
        System.out.println("------------------------------------");
        //System.out.println(element.getCavityStencil().getArguments().get(2).getID());
        /*Component component = new Component();
        System.out.println(component.toString());*/
        System.out.println("---------------------------------");
        ReadJson readJson = new ReadJson();
        //readJson.readJson(pathInt);
        System.out.println("---------------------------------");
        //System.out.println(str);
        //System.out.println(components);

        /*UnitsContextImpl context = new UnitsContextImpl();
        Collection<IUnitBundle> list = context.getSupportUnitBundles();*/

    }
}

