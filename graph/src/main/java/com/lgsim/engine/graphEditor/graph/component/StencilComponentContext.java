package com.lgsim.engine.graphEditor.graph.component;

import com.lgsim.engine.graphEditor.graph.ApplicationContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StencilComponentContext
{
  private String predefinedPath;
  private String userDefinedPath;


  public StencilComponentContext(@NotNull String predefinedPath, @Nullable String userDefinedPath)
  {
    this.predefinedPath = predefinedPath;
    this.userDefinedPath = userDefinedPath;
  }


  @Nullable
  public List<StencilComponent> loadPredefinedComponents()
  {
    StencilComponentXmlFileParser parser = new StencilComponentXmlFileParser(OriginType.PREDEFINED, predefinedPath);
    try
    {
      return parser.parse();
    }
    catch (Exception e)
    {
      ApplicationContext.getInstance().getApplicationExceptionManager().dealWith(e);
      return null;
    }
  }


  @Nullable
  public List<StencilComponent> loadUserDefinedComponents()
  {
    StencilComponentXmlFileParser parser = new StencilComponentXmlFileParser(OriginType.USER_DEFINED, userDefinedPath);
    try
    {
      return parser.parse();
    }
    catch (Exception e)
    {
      ApplicationContext.getInstance().getApplicationExceptionManager().dealWith(e);
      return null;
    }
  }


  public void saveUserDefinedComponent()
  {
  }
}
