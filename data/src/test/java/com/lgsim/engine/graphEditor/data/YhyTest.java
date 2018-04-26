package com.lgsim.engine.graphEditor.data;

import com.lgsim.engine.graphEditor.data.components.template.Element;
import com.lgsim.engine.graphEditor.data.components.util.readjson.ReadJson;

//import com.lgsim.engine.graphEditor.data.components.impl.ComponentImpl;
//import net.sf.json.JSONObject;

public class YhyTest {
    public static void main(String[] args) throws Exception {

        /*Template template = new Template();
        List<IVertexStencil> list = new ArrayList<IVertexStencil>();

        list = template.getPredefinedStencils();

        WriteJson wr = new WriteJson();
        String path = "C:\\Users\\admin\\Desktop\\ptlo-111.out";
        wr.writeJson(list,path);*/

        /*Ptlos_111 ptlos_111 = new Ptlos_111();

        System.out.println(ptlos_111.getType());
        System.out.println(ptlos_111.getArguments());*/

        //Element element = new Element();
        //element.getPredefinedStencils();
        //String str = JSONObject.toJSONString(element.getPredefinedStencils().get(1));
        /*List<IVertexStencil> list = new ArrayList<>();
        list = element.getPredefinedStencils();*/

        String path = "com/lgsim/engine/graphEditor/data/simpleCase.inp";
        Element element = new Element();
        System.out.println(element.getPredefinedStencils().get(0));
        System.out.println("------------------------------------");
        System.out.println(element.getPredefinedStencils().get(0).getArguments().get(2).getDescription());
        System.out.println("------------------------------------");
        System.out.println(element.getCavityStencil().getArguments().get(2).getID());
        /*Component component = new Component();
        System.out.println(component.toString());*/
        System.out.println("---------------------------------");
        ReadJson readJson = new ReadJson();
        readJson.readJson(path);
        System.out.println("---------------------------------");
        //System.out.println(str);
        //System.out.println(components);



    }
}

