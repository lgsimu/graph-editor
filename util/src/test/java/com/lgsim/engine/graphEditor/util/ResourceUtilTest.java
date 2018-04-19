package com.lgsim.engine.graphEditor.util;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class ResourceUtilTest
{
  @Test
  public void lookup() throws IOException
  {
    URI uri = ResourceUtil.lookup("com/lgsim/engine/graphEditor/util/default-style.xml");
    if (uri != null)
    {
      String output = IOUtils.toString(uri.toURL().openStream(), "utf-8");
      System.out.println(output);
    }
  }
}