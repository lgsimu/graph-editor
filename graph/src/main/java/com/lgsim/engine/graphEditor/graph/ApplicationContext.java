package com.lgsim.engine.graphEditor.graph;

import com.lgsim.engine.graphEditor.graph.component.StencilComponentContext;
import com.lgsim.engine.graphEditor.graph.conf.UserConfiguration;
import com.mxgraph.util.mxResources;
import org.jetbrains.annotations.NotNull;

public class ApplicationContext
{
  private static final UserConfiguration userConfiguration = new UserConfiguration();
  private static final String predefinedXmlPath = "com/lgsim/engine/graphEditor/graph/predefined.xml";
  private ApplicationExceptionManager applicationExceptionManager;
  private StencilComponentContext stencilComponentContext;


  private ApplicationContext()
  {
    this.applicationExceptionManager = new ApplicationExceptionManager();
    this.stencilComponentContext =
        new StencilComponentContext(predefinedXmlPath, userConfiguration.getUserDefinedXmFile());
  }


  @NotNull
  public static ApplicationContext getInstance()
  {
    return new ApplicationContext();
  }


  public ApplicationExceptionManager getApplicationExceptionManager()
  {
    return applicationExceptionManager;
  }


  public StencilComponentContext getStencilComponentContext()
  {
    return stencilComponentContext;
  }


  public UserConfiguration getUserConfiguration()
  {
    return userConfiguration;
  }


  public static void initialize()
  {
    mxResources.add("com/lgsim/engine/graphEditor/graph/messages");
    userConfiguration.init();
  }
}
