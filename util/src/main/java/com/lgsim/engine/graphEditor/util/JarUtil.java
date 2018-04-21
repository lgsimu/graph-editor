package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarUtil
{
  /**
   * 打包jar包
   *
   * @param dir      要打包的目录，不包含该目录本身
   * @param jarFile  要生成的jar包文件
   * @param manifest 清单
   * @throws IOException 如果jar包文件未找到，或I/O异常
   */
  public static void pack(@NotNull File dir, @NotNull File jarFile, @NotNull Manifest manifest) throws IOException
  {
    try (JarOutputStream os = new JarOutputStream(new FileOutputStream(jarFile), manifest))
    {
      pack0(dir, os);
    }
  }


  private static void pack0(@NotNull File maybeDir, @NotNull JarOutputStream target) throws IOException
  {
    if (maybeDir.isDirectory())
    {
      JarEntry entry = createJarEntry(maybeDir);
      target.putNextEntry(entry);
      target.closeEntry();
      File[] files = maybeDir.listFiles();
      if (files != null)
      {
        for (File file : files)
        {
          pack0(file, target);
        }
      }
    }
    else
    {

    }
  }


  private static @NotNull JarEntry createJarEntry(@NotNull File src)
  {
    String path = createEntryPath(src);
    JarEntry entry = new JarEntry(path);
    entry.setTime(src.lastModified());
    return entry;
  }


  private static @NotNull String createEntryPath(@NotNull File src)
  {
    String name = src.getPath().replace("\\", "/");
    if (!name.endsWith("/"))
    {
      return name + "/";
    }
    else
    {
      return name;
    }
  }


  /**
   * 解压jar包至目标目录
   *
   * @param jarFile jar包文件
   * @param target  目标目录
   */
  public static void unpack(@NotNull File jarFile, @NotNull File target)
  {
  }
}
