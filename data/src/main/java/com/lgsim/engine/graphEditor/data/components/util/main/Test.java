package com.lgsim.engine.graphEditor.data.components.util.main;

import com.lgsim.engine.graphEditor.data.components.psource.Psource;
import com.lgsim.engine.graphEditor.data.components.ptlos.entity.Ptlos;
import com.lgsim.engine.graphEditor.data.components.util.readjson.ReadPsourceJson;
import com.lgsim.engine.graphEditor.data.components.util.readjson.ReadPtlosJson;
import com.lgsim.engine.graphEditor.data.components.util.writejson.WriteJson;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 2018/4/14.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        ReadPtlosJson rptlos = new ReadPtlosJson();
        ReadPsourceJson rpsource = new ReadPsourceJson();
        WriteJson wj = new WriteJson();
        ArrayList<Ptlos> ptlos = new ArrayList<Ptlos>();
        ArrayList<Psource> psources = new ArrayList<Psource>();

        ptlos = rptlos.readPtlosJson();
        psources = rpsource.readPsourcesJson();
        //wj.writeJson();

        System.out.println(ptlos);
        System.out.println(psources);

    }
}
