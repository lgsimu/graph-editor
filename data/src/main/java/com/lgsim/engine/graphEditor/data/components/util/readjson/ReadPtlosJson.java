package com.lgsim.engine.graphEditor.data.components.util.readjson;

import com.lgsim.engine.graphEditor.data.components.ptlos.entity.Ptlos;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 2018/4/14.
 * 读取json数据
 * @author yhy
 */
public class ReadPtlosJson
{

    public ArrayList<Ptlos> readPtlosJson() throws IOException {
        //读取文件
        File file = new File("E:\\YHY\\案例-20180412\\simpleCase.inp");
        //将json文件转化为字符串
        String jsonStr = FileUtils.readFileToString(file,"UTF-8");
        //创建json对象,并将字符串转化为json对象
        JSONObject json1 = JSONObject.fromObject(jsonStr);

        JSONObject components = JSONObject.fromObject(json1.get("Components"));
        JSONArray component = JSONArray.fromObject(components.get("Component"));
        //创建Ptlos元件对象集合
        ArrayList<Ptlos> ptlos = new ArrayList<Ptlos>();

        /*for(int i = 0;i < component.size();i++) {
            Ptlos ptlo = new Ptlos();

            JSONObject compt = JSONObject.fromObject(component.get(i));
            JSONObject type = JSONObject.fromObject(compt.get("Type"));
            JSONArray armNodes = JSONArray.fromObject(compt.get("ArmNodes"));
            JSONArray feature = JSONArray.fromObject(compt.get("Feature"));
            JSONObject ft = JSONObject.fromObject(feature.get(0));
            JSONArray value = JSONArray.fromObject(ft.get("Value"));

            //将json数值存入类中
            if(type.getString("Type").equals("111")) {
                ptlo.setName(compt.getString("Name"));
                ptlo.setType(compt.getString("Type"));
                ptlo.setArmNode1(armNodes.getString(0));
                ptlo.setArmNode2(armNodes.getString(1));
                ptlo.setFeatureName(ft.getString("Name"));
                ptlo.setAa(value.getDouble(0));
                ptlo.setGeo1(value.getDouble(1));
                ptlo.setGeo2(value.getDouble(2));
                //将类存入数组中
                ptlos.add(ptlo);
            }

        }*/
        return ptlos;
        //System.out.println(ptlos.toString());
    }
}
