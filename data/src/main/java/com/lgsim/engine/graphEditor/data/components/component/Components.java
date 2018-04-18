package com.lgsim.engine.graphEditor.data.components.component;

import com.lgsim.engine.graphEditor.api.data.IStencilContext;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexArgumentImpl;
import com.lgsim.engine.graphEditor.data.components.entity.IVertexRestrictionImpl;
import com.lgsim.engine.graphEditor.data.components.ptlos.entity.Ptlos;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取json文件中的参数
 */
public class Components implements IStencilContext {
    @Override
    public List<IVertexStencil> getPredefinedStencils() {

        //读取文件
        File file = new File("E:\\YHY\\案例-20180412\\simpleCase.inp");
        //将json文件转化为字符串
        String jsonStr = null;
        try {
            jsonStr = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建json对象,并将字符串转化为json对象
        JSONObject json = JSONObject.fromObject(jsonStr);

        JSONObject components = JSONObject.fromObject(json.get("Components"));
        JSONArray component = JSONArray.fromObject(components.get("Component"));

        //创建Component元件对象集合
        List<IVertexStencil> coms = new ArrayList<IVertexStencil>();

        for (int i = 0; i < coms.size();i++) {
            Ptlos ptlos = new Ptlos();//元件对象
            ArrayList<IVertexArgumentImpl> arguments = new ArrayList<IVertexArgumentImpl>();//参数集合
            ArrayList<IVertexOutput> outputs = new ArrayList<IVertexOutput>();//计算后的输出参数
            ArrayList<String> pInt = new ArrayList<String>();//连入端口类型
            ArrayList<String> pOut = new ArrayList<String>();//连出端口类型

            JSONObject com = JSONObject.fromObject(component.getJSONObject(i));
            JSONArray armnode = JSONArray.fromObject(com.get("ArmNodes"));
            JSONArray features = JSONArray.fromObject(com.get("Feature"));
            JSONObject feature = JSONObject.fromObject(features.getJSONObject(0));
            JSONArray values = JSONArray.fromObject(feature.get("Value"));

            if(com.getString("Type").equals("111")) {

                IVertexArgumentImpl argument1 = new IVertexArgumentImpl("AA","2",0,0,values.getDouble(0),"进口面积");
                IVertexArgumentImpl argument2 = new IVertexArgumentImpl("GEO1","2",0,0,values.getDouble(1),"出口面积");
                IVertexArgumentImpl argument3 = new IVertexArgumentImpl("GEO2","2",0,0,values.getDouble(2),"总压损失系数");

                pInt.add(armnode.getString(0));
                pOut.add(armnode.getString(1));

                arguments.add(argument1);
                arguments.add(argument2);
                arguments.add(argument3);

                ptlos.setName(com.getString("Name"));
                ptlos.setType(com.getString("Type"));
                ptlos.setStencilIcon("");
                ptlos.setGraphIcon("");
                ptlos.setRestriction(new IVertexRestrictionImpl(1,1,1,1,pInt,pOut));
                ptlos.setIsPredefined(true);
                ptlos.setArgumentType(feature.getString("Name"));
                ptlos.setArguments(arguments);
                ptlos.setOutputs(outputs);

            }else if(com.getString("Type").equals("1")) {

                IVertexArgumentImpl argument1 = new IVertexArgumentImpl("GEO1","4",0,0,values.getDouble(0),"总压");
                IVertexArgumentImpl argument2 = new IVertexArgumentImpl("GEO2","24",0,0,values.getDouble(1),"总温");
                IVertexArgumentImpl argument3 = new IVertexArgumentImpl("GEO3","2",0,0,values.getDouble(2),"流通面积");
                IVertexArgumentImpl argument4 = new IVertexArgumentImpl("GEO4","30",0,0,values.getDouble(3),"涡量");

                pOut.add(armnode.getString(0));

                arguments.add(argument1);
                arguments.add(argument2);
                arguments.add(argument3);
                arguments.add(argument4);

                ptlos.setName(com.getString("Name"));
                ptlos.setType(com.getString("Type"));
                ptlos.setStencilIcon("");
                ptlos.setGraphIcon("");
                ptlos.setRestriction(new IVertexRestrictionImpl(0,0,1,1,pInt,pOut));
                ptlos.setIsPredefined(true);
                ptlos.setArgumentType(feature.getString("Name"));
                ptlos.setArguments(arguments);
                ptlos.setOutputs(outputs);

            }else {

                IVertexArgumentImpl argument1 = new IVertexArgumentImpl("GEO1","4",0,0,values.getDouble(0),"静压");
                IVertexArgumentImpl argument2 = new IVertexArgumentImpl("GEO2","24",0,0,values.getDouble(1),"总温");
                IVertexArgumentImpl argument3 = new IVertexArgumentImpl("GEO3","2",0,0,values.getDouble(2),"流通面积");
                IVertexArgumentImpl argument4 = new IVertexArgumentImpl("GEO4","30",0,0,values.getDouble(3),"涡量");

                pInt.add(armnode.getString(0));

                arguments.add(argument1);
                arguments.add(argument2);
                arguments.add(argument3);
                arguments.add(argument4);

                ptlos.setName(com.getString("Name"));
                ptlos.setType(com.getString("Type"));
                ptlos.setStencilIcon("");
                ptlos.setGraphIcon("");
                ptlos.setRestriction(new IVertexRestrictionImpl(0,0,1,1,pInt,pOut));
                ptlos.setIsPredefined(true);
                ptlos.setArgumentType(feature.getString("Name"));
                ptlos.setArguments(arguments);
                ptlos.setOutputs(outputs);

            }
            coms.add(ptlos);

        }

        return coms;
    }

    @Override
    public List<IVertexStencil> getUserDefinedStencils() {
        return null;
    }

    @Override
    public void saveUserDefinedStencil(@NotNull IVertexStencil stencil) {

    }
}
