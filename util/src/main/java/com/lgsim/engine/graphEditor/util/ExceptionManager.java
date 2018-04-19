package com.lgsim.engine.graphEditor.util;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionManager
{
  public static final ExceptionManager INSTANCE = new ExceptionManager();
  private static final Logger logger = LoggerFactory.getLogger(ExceptionManager.class);


  public void dealWith(@NotNull Exception e)
  {
    logger.debug("exception has occurred.", e);
  }


  public void dealWith(@NotNull ResourceFileMissingException e)
  {
    dealWith((Exception) e);
    System.exit(1);
  }


  public void dealWith(@NotNull InstantiationException e)
  {
    dealWith((Exception) e);
    System.exit(1);
  }
}
