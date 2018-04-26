package com.lgsim.engine.graphEditor.data.components.util.writejson;

import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.util.jsonformattool.JsonFormatTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 2018/4/14.
 * 输出json文件
 */
public class WriteJson {

    /**
     *
     * @param object 元件对象集合
     * @param path 生成json文件路径
     * @throws IOException
     */
    public void writeJson(List<IVertexStencil> object, String path) throws IOException {

        /*ArrayList<Ptlos> ptlos = new ArrayList<Ptlos>();
        ReadPtlosJson rptlos = new ReadPtlosJson();
        ptlos = rptlos.readPtlosJson();*/
        JsonFormatTool tool = new JsonFormatTool();

        //创建文件对象
        //File file = new File("C:\\Users\\admin\\Desktop\\ptlo-111.out");
        File file = new File(path);
        //判断文件是否已经存在
        /*if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
       file.delete();
        file.createNewFile();*/
        //创建输出流
        FileOutputStream os = new FileOutputStream(file);
        //将对象经过toString()转化成字符串
        String str1 = "{\"Name\": \"TestAutomationInput\",\"User\": \"LiZongyao\",\"SimplifiedInput\": \"1\",\"SolveSwirl\": \"-1\",\"OutputFormat\": \"2\",\"Parameters\": {\"Parameter\": []},\"SimulationData\": {\"SimulationType\": \"CompressibleSteady\",\"Material\": \"Gas\",\"TurbineData\": {\"RotationalSpeed\": [ \"0\",\"0\",\"0\"]},\"Restart\": \"0\",\"ConvergenceData\": {\"MaxIter\": \"500\",\"RelaxFactor\": [\"0.3\",\"0.2\",\"0.5\"],\"ConvergenceData\": [\"1E-08\",\"1E-08\",\"1E-08\"]}},\"Nodes\": {\"Node\": []},\"Components\":{\"Component\":";
        String str2 = "}";
        String str3 = "}";
        String json = tool.formatJson(str1 + object.toString() + str2 + str3);
        //{"Component":
        //System.out.println(str1 + object.toString() + str2);
        //将字符串转化成byte[]数组
        byte[] bjson = json.getBytes();
        //将byte数据写入文件
        os.write(bjson);
        //关闭
        os.close();
    }

}
