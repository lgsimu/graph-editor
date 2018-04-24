package com.lgsim.engine.graphEditor.widget.Test;

import java.io.File;
import java.io.IOException;

public class Load {
    public static void main(String[] args) throws IOException {

       new Load().getLoad();
    }

    public void getLoad() throws IOException {

//        System.out.print(this.getClass().getResource("")+"\n");
//        System.out.print(Thread.currentThread().getContextClassLoader().getResource("")+"\n");
//        System.out.print(this.getClass().getClassLoader().getResource("")+"\n");
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        File f = new File("D:\\file\\case-0418-2.dat");

        System.out.println();
       // System.out.print(courseFile);

    }
}
