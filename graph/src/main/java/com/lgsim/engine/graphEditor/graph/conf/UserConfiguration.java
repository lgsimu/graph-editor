package com.lgsim.engine.graphEditor.graph.conf;

import com.lgsim.engine.graphEditor.graph.ApplicationContext;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class UserConfiguration
{
  private static final Logger logger = LoggerFactory.getLogger(UserConfiguration.class);
  private static final String corporationName = "Lgsim";
  private static final String artifactName = "AirDiagram";
  private static final String version = "1.0";


  @NotNull
  private String getDirectory()
  {
    final String directoryName = "." + corporationName + artifactName + version;
    String home = System.getProperty("user.home");
    return Paths.get(home, directoryName).toString();
  }


  @NotNull
  private String getUserDefinedDirectory()
  {
    return Paths.get(getDirectory(), "user-defined").toString();
  }


  public String getUserDefinedXmFile()
  {
    return Paths.get(getUserDefinedDirectory(), "user-defined.xml").toString();
  }


  public String getUserDefinedImagesDirectory()
  {
    return Paths.get(getUserDefinedDirectory(), "images").toString();
  }


  public String getLayoutXmlFile()
  {
    return Paths.get(getDirectory(), "layout.xml").toString();
  }


  public String getLogFile()
  {
    return Paths.get(getDirectory(), "log.txt").toString();
  }


  public void init()
  {
    touchDirectories();
    touchFiles();
    copyFileTemplateIfAbsent("com/lgsim/engine/graphEditor/graph/user-defined-template.xml", getUserDefinedXmFile());
  }


  private void touchDirectories()
  {
    String[] directories = new String[]{
        getDirectory(),
        getUserDefinedDirectory(),
        getUserDefinedImagesDirectory()
    };
    for (String it : directories)
    {
      touchDirectoryIfAbsent(it);
    }
  }


  private void touchDirectoryIfAbsent(@NotNull String path)
  {
    File dir = new File(path);
    if (!dir.exists())
    {
      boolean success = dir.mkdir();
      logger.debug("make directory {} {}", dir, success ? "success" : "failed");
    }
  }


  private void touchFiles()
  {
    String[] files = new String[]{
        getLogFile(),
        getLayoutXmlFile()
    };
    for (String it : files)
    {
      touchEmptyFileIfAbsent(it);
    }
  }


  private void touchEmptyFileIfAbsent(@NotNull String path)
  {
    File file = new File(path);
    if (!file.exists())
    {
      try
      {
        new FileOutputStream(file).close();
      }
      catch (IOException e)
      {
        logger.debug("{}", e);
      }
    }
  }


  private void copyFileTemplateIfAbsent(String from, String to)
  {
    File toFile = new File(to);
    if (!toFile.exists())
    {
      InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(from);
      try
      {
        FileOutputStream fileOutputStream = new FileOutputStream(toFile);
        IOUtils.copy(inputStream, fileOutputStream);
        fileOutputStream.close();
        logger.debug("copy user defined template file to {}", toFile);
      }
      catch (IOException e)
      {
        ApplicationContext.getInstance().getApplicationExceptionManager().dealWith(e);
      }
    }
  }
}
