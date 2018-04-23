package com.lgsim.engine.graphEditor.util.exception;

import com.lgsim.engine.graphEditor.util.ResourceType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class ResourceFileMissingException extends IOException {
  private final String resourcePath;
  private final ResourceType type;

  public ResourceFileMissingException(@Nullable String resourcePath, @NotNull ResourceType type) {
    this.resourcePath = resourcePath;
    this.type = type;
  }

  public String getResourcePath() {
    return resourcePath;
  }

  public boolean triggerByIcon() {
    return type == ResourceType.ICON;
  }

  public boolean triggerByURI() {
    return type == ResourceType.URI;
  }
}
