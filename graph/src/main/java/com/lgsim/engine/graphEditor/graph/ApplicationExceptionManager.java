package com.lgsim.engine.graphEditor.graph;

import com.lgsim.engine.graphEditor.graph.component.ResourceFileMissingException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationExceptionManager
{
  private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionManager.class);


  public void dealWith(@NotNull Exception e)
  {
    logger.debug("exception has occurred.", e);
  }


  public void dealWith(@NotNull ResourceFileMissingException e)
  {
    dealWith((Exception) e);
    System.exit(1);
  }
}
