package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.jar.Manifest;

public class JarUtil
{
  /**
   * 打包jar包
   *
   * @param dir      要打包的目录，不包含该目录本身
   * @param jarFile  jar包文件
   * @param manifest 清单
   * @return 如果遇到异常，返回{@code null}，否则，则返回jar包文件
   * @throws IOException io exception
   */
  public static @NotNull File pack(@NotNull File dir, @NotNull File jarFile, @NotNull Manifest manifest) throws IOException
  {

    return null;
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
