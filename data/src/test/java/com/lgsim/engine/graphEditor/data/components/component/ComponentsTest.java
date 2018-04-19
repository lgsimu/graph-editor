package com.lgsim.engine.graphEditor.data.components.component;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ComponentsTest {

    @Test
    public void testReadResource() throws IOException {
        String path = "com/lgsim/engine/graphEditor/data/simpleCase.inp";
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        String s = IOUtils.toString(in);
        System.out.println(s);
    }
}