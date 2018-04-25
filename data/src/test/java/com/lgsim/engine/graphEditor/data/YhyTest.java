package com.lgsim.engine.graphEditor.data;

import com.alibaba.fastjson.JSON;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
//import com.lgsim.engine.graphEditor.data.components.impl.ComponentImpl;
import com.lgsim.engine.graphEditor.data.components.component.Components;
import com.lgsim.engine.graphEditor.data.components.component.ComponentsTest;
import com.lgsim.engine.graphEditor.data.components.library.ptlos.ptlos_111.Ptlos_111;
import com.lgsim.engine.graphEditor.data.components.template.Component;
import com.lgsim.engine.graphEditor.data.components.template.Element;
import com.lgsim.engine.graphEditor.data.components.template.Template;
import com.lgsim.engine.graphEditor.data.components.util.readjson.ReadJson;
import com.lgsim.engine.graphEditor.data.components.util.writejson.WriteJson;
//import net.sf.json.JSONObject;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
/*        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        String jsonStr = null;
        try {
            jsonStr = IOUtils.toString(is);
        }catch (IOException e) {
            e.getStackTrace();
        }
        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
        List<Component> components = new ArrayList<>();
        components = (List<Component>) JSONArray.toCollection(jsonArray,Component.class);*/
        //System.out.println(list.get(0).getArguments().get(0));
        //System.out.println(wr);
//        System.out.println(list.size());
        ReadJson readJson = new ReadJson();
        readJson.readJson(path);
        System.out.println("---------------------------------");
        //System.out.println(str);
        //System.out.println(components);



    }
}

