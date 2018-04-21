package com.lgsim.engine.graphEditor.api;

import com.lgsim.engine.graphEditor.api.graph.IGraphDocumentSpec;

public interface IApplication extends IGraphDocumentSpec
{
  String statusText = MessageBundle.get("application.status.ready");
}
