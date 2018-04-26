package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@SuppressWarnings({"WeakerAccess"})
public class Configuration {

  private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
  private final String corporationName;
  private final String artifactName;
  private final String version;


  public Configuration(String corporationName, String artifactName, String version)
  {
    this.corporationName = corporationName;
    this.artifactName = artifactName;
    this.version = version;
  }


  public @NotNull File getWorkingDirectory() {
    return new File(getWorkingDirectory0());
  }


  @NotNull
  private String getWorkingDirectory0()
  {
    final String directoryName = "." + corporationName + artifactName + version;
    String home = System.getProperty("user.home");
    return Paths.get(home, directoryName).toString();
  }


  private String getLogFile()
  {
    return Paths.get(getWorkingDirectory0(), "log.txt").toString();
  }


  private void touchDirectories()
  {
    String[] directories = new String[]{
        getWorkingDirectory0(),
        getTempDirectory0()
    };
    for (String it : directories) {
      touchDirectoryIfAbsent(it);
    }
  }


  private void touchDirectoryIfAbsent(@NotNull String path)
  {
    File dir = new File(path);
    if (!dir.exists()) {
      boolean success = dir.mkdir();
      logger.debug("make directory {} {}", dir, success ? "success" : "failed");
    }
  }


  private void touchFiles()
  {
    String[] files = new String[]{
        getLogFile(),
    };
    for (String it : files) {
      touchEmptyFileIfAbsent(it);
    }
  }


  private void touchEmptyFileIfAbsent(@NotNull String path)
  {
    File file = new File(path);
    if (!file.exists()) {
      try {
        new FileOutputStream(file).close();
      }
      catch (IOException e) {
        logger.debug("{}", e);
      }
    }
  }


  public void applyIfPossible()
  {
    touchDirectories();
    touchFiles();
  }


  private @NotNull String getTempDirectory0() {
    return Paths.get(getWorkingDirectory0(), "temp").toString();
  }


  public @NotNull File getTempDirectory() {
    return new File(getTempDirectory0());
  }
}
