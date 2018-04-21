package com.lgsim.engine.graphEditor.util;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarUtil
{
  private static final int BUFF_SIZE = 2048;


  /**
   * 打包jar
   *
   * @param dir      要打包的目录，不包含该目录本身
   * @param jarFile  要生成的jar文件
   * @param manifest 清单
   * @throws IOException               如果jar文件未找到，或I/O异常
   * @throws IndexOutOfBoundsException 要生成的jar文件不能在要打包的目录中
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
      putEntry(maybeDir, target, true);
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
      putEntry(maybeDir, target, false);
      try (BufferedInputStream in = IOUtils.buffer(new FileInputStream(maybeDir)))
      {
        byte[] buff = new byte[BUFF_SIZE];
        int offset = 0;
        while (in.available() != 0)
        {
          int count = in.read(buff, offset, buff.length);
          target.write(buff, offset, count);
          offset += count;
        }
        target.closeEntry();
      }
    }
  }


  private static void putEntry(@NotNull File file, @NotNull JarOutputStream target, boolean dir) throws IOException
  {
    JarEntry entry = createJarEntry(file, dir);
    target.putNextEntry(entry);
  }


  private static @NotNull JarEntry createJarEntry(@NotNull File src, boolean dir)
  {
    String path = createEntryPath(src, dir);
    JarEntry entry = new JarEntry(path);
    entry.setTime(src.lastModified());
    return entry;
  }


  private static @NotNull String createEntryPath(@NotNull File src, boolean dir)
  {
    String name = src.getPath().replace("\\", "/");
    if (dir)
    {
      if (!name.endsWith("/"))
      {
        return name + "/";
      }
      else
      {
        return name;
      }
    }
    else
    {
      return name;
    }
  }


  /**
   * 解压jar至目标目录
   *
   * @param jarFile jar文件
   * @param target  目标目录
   */
  public static void unpack(@NotNull File jarFile, @NotNull File target)
  {
  }
}
