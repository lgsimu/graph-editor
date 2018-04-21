package com.lgsim.engine.graphEditor.util;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class JarUtilTest
{
  private File testDir;
  private File comDir;


  @Before
  public void setUp() throws Exception
  {
    testDir = new File(JarUtilTest.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    comDir = new File(testDir, "com");
    if (!comDir.exists())
    {
      throw new IOException();
    }
  }


  @Test
  public void pack() throws IOException
  {
    if (testDir != null)
    {
      File testJar = new File(testDir, "test.jar");
      Manifest manifest = new Manifest();
      manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VENDOR, "lgsim");
      JarUtil.pack(comDir, testJar, manifest);
    }
  }


  @Test
  public void unpack()
  {
  }
}