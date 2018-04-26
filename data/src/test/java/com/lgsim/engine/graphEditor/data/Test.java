package com.lgsim.engine.graphEditor.data;

import com.alibaba.fastjson.JSON;
import com.lgsim.engine.graphEditor.data.test.Student;
import net.sf.json.JSONArray;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        //json集合
        String jsonString = "[{\"Age\":[\"13\"],\"name\":\"zhangSan\"},{\"Age\":[\"14\",\"15\"],\"name\":\"liSi\"}]";

        String jstr = jsonString.toLowerCase();

        JSONArray jsonArray = JSONArray.fromObject(jstr);
        //Java集合
        List<Student> list = (List<Student>) JSONArray.toCollection(jsonArray,Student.class);
        System.out.println(list);
        System.out.println("-------------------------");

        String jsonstr = JSON.toJSONString(list);
        System.out.println(jsonstr);
    }
}
