package com.lgsim.engine.graphEditor.data;

import com.alibaba.fastjson.JSON;
import com.lgsim.engine.graphEditor.data.test.Student;
import net.sf.json.JSONArray;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        //json集合
        String jsonString = "[{\"Age\":[\"13\"],\"name\":\"zhangSan\"},{\"Age\":[\"14\",\"15\"],\"name\":\"liSi\"}]";

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        //Java集合
        List<Student> list = (List<Student>) jsonArray.toCollection(jsonArray,Student.class);
        System.out.println(list);
        System.out.println("-------------------------");

        String jstr = JSON.toJSONString(list);
        System.out.println(jstr);
    }
}
