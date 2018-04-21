package com.lgsim.engine.graphEditor.util;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarUtil
{
  private static final int BUFF_SIZE = 2048;
  private static final String sep = "/";


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
      pack0(dir, os, null);
    }
  }


  private static void pack0(@NotNull File maybeDir, @NotNull JarOutputStream target, @Nullable String root) throws IOException
  {
    if (maybeDir.isDirectory())
    {
      String path = putEntry(maybeDir, target, true, root);
      target.closeEntry();
      File[] files = maybeDir.listFiles();
      if (files != null)
      {
        for (File file : files)
        {
          pack0(file, target, path);
        }
      }
    }
    else
    {
      putEntry(maybeDir, target, false, root);
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


  private static String putEntry(@NotNull File file, @NotNull JarOutputStream target,
                                 boolean dir, @Nullable String root) throws IOException
  {
    String path = createEntryPath(file, dir, root);
    JarEntry entry = new JarEntry(path);
    entry.setTime(file.lastModified());
    target.putNextEntry(entry);
    return path;
  }


  private static @NotNull String createEntryPath(@NotNull File src, boolean dir, @Nullable String root)
  {
    String name = src.getName();
    if (dir && (!name.endsWith(sep)))
    {
      name += sep;
    }
    return concatPath(root, name);
  }


  private static String concatPath(@Nullable String root, @NotNull String name)
  {
    if (root == null)
    {
      return name;
    }
    else
    {
      if (root.endsWith(sep))
      {
        return root + name;
      }
      else
      {
        return root + sep + name;
      }
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
