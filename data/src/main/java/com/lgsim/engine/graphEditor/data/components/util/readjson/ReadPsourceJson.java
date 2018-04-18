package com.lgsim.engine.graphEditor.data.components.util.readjson;

import com.lgsim.engine.graphEditor.data.components.psource.Psource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 2018/4/16.
 */
public class ReadPsourceJson {

    public ArrayList<Psource> readPsourcesJson() throws IOException {

      //读取文件
      File file = new File("E:\\YHY\\案例-20180412\\simpleCase.inp");
      //将json文件转化为字符串
      String jsonStr = FileUtils.readFileToString(file, "UTF-8");
      //创建json对象,并将字符串转化为json对象
      JSONObject json1 = JSONObject.fromObject(jsonStr);

      JSONObject components = JSONObject.fromObject(json1.get("Components"));
      JSONArray component = JSONArray.fromObject(components.get("Component"));

      //创建Psource元件对象集合
      ArrayList<Psource> psources = new ArrayList<Psource>();

      for(int i = 0;i < component.size();i++) {
          Psource psource = new Psource();

          JSONObject compt = JSONObject.fromObject(component.get(i));
          JSONObject type = JSONObject.fromObject(compt.get("Type"));
          JSONArray armNodes = JSONArray.fromObject(compt.get("ArmNodes"));
          JSONArray feature = JSONArray.fromObject(compt.get("Feature"));
          JSONObject ft = JSONObject.fromObject(feature.get(0));
          JSONArray value = JSONArray.fromObject(ft.get("Value"));

          if(!type.getString("Type").equals("111")) {
              psource.setName(compt.getString("Name"));
              psource.setName(compt.getString("Type"));
              psource.setArmNode1(armNodes.getString(0));
              psource.setFeatureName(ft.getString("Name"));
              psource.setGeo1(value.getDouble(0));
              psource.setGeo2(value.getDouble(1));
              psource.setGeo3(value.getDouble(2));
              psource.setGeo4(value.getDouble(3));
              //将类存入数组中
              psources.add(psource);
          }

        }
        return psources;
    }
}
