package com.lgsim.engine.graphEditor.api.data;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public interface IGraphDocumentContext
{
  IGraphDocument importDocument(@NotNull File file);


  List<IGraphDocument> importLastOpenedDocuments();


  List<IGraphDocument> getDocuments();
}
