package com.lgsim.engine.graphEditor.data.components.util.writejson;

import com.lgsim.engine.graphEditor.data.components.ptlos.entity.Ptlos;
import com.lgsim.engine.graphEditor.data.components.util.jsonformattool.JsonFormatTool;
import com.lgsim.engine.graphEditor.data.components.util.readjson.ReadPtlosJson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 2018/4/14.
 * 输出json文件
 */
public class WriteJson {

    public void writeJson() throws IOException {

        ArrayList<Ptlos> ptlos = new ArrayList<Ptlos>();
        ReadPtlosJson rptlos = new ReadPtlosJson();
        ptlos = rptlos.readPtlosJson();
        JsonFormatTool tool = new JsonFormatTool();

        //创建文件对象
        File file = new File("C:\\Users\\admin\\Desktop\\ptlo-111.out");
        //判断文件是否已经存在
        /*if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
       file.delete();
        file.createNewFile();*/
        //创建输出流
        FileOutputStream os = new FileOutputStream(file);
        //将对象经过toString()转化成字符串
        String json = "{\n" + "\"Component\":" + tool.formatJson(ptlos.toString()) + "\n}";

        //System.out.println(json);
        //将字符串转化成byte[]数组
        byte[] bjson = json.getBytes();
        //将byte数据写入文件
        os.write(bjson);
        //关闭
        os.close();
    }

}
