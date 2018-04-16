package com.lgsim.engine.graphEditor.graph.editor;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class DefaultFileFilter extends FileFilter
{
  private String ext;
  private String desc;


  DefaultFileFilter(String extension, String description)
  {
    ext = extension.toLowerCase();
    desc = description;
  }


  public boolean accept(File file)
  {
    return file.isDirectory() || file.getName().toLowerCase().endsWith(ext);
  }


  public String getDescription()
  {
    return desc;
  }


  public String getExtension()
  {
    return ext;
  }


  public void setExtension(String extension)
  {
    this.ext = extension;
  }


  public static class ImageFileFilter extends FileFilter
  {
    static String[] imageFormats = ImageIO.getReaderFormatNames();
    String desc;


    ImageFileFilter(String description)
    {
      desc = description;
    }


    public boolean accept(File file)
    {
      if (file.isDirectory())
      {
        return true;
      }

      String filename = file.toString().toLowerCase();

      for (String imageFormat : imageFormats)
      {
        if (filename.endsWith("." + imageFormat.toLowerCase()))
        {
          return true;
        }
      }

      return false;
    }


    public String getDescription()
    {
      return desc;
    }
  }


  public static class EditorFileFilter extends FileFilter
  {
    String desc;


    public EditorFileFilter(String description)
    {
      desc = description;
    }


    public boolean accept(File file)
    {
      if (file.isDirectory())
      {
        return true;
      }

      String filename = file.getName().toLowerCase();

      return filename.endsWith(".xml") || filename.endsWith(".xml.gz");
    }


    public String getDescription()
    {
      return desc;
    }
  }
}
