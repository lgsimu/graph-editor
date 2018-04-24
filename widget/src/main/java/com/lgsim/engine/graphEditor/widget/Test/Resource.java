package com.lgsim.engine.graphEditor.widget.Test;

import javax.swing.*;
import java.io.InputStream;

public class Resource {

    public  static void main(String[] args){
        String path = Resource.class.getClassLoader().getResource("").getPath();
        String filestr = path + ".address.png";
        //ImageIcon imageIcon = createImageIcon(filestr,"dd");
        System.out.print(path);
    }



}
