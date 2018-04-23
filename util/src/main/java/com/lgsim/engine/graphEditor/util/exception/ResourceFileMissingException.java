package com.lgsim.engine.graphEditor.util.exception;

import java.io.IOException;

public class ResourceFileMissingException extends IOException {
  private final String resourcePath;

  public ResourceFileMissingException(String resourcePath) {
    this.resourcePath = resourcePath;
  }

  public String getResourcePath() {
    return resourcePath;
  }
}
