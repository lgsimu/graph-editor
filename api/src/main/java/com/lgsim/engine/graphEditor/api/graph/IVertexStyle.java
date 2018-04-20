package com.lgsim.engine.graphEditor.api.graph;

import java.awt.*;
import java.util.Map;

public interface IVertexStyle
{
  Point getPosition();


  Dimension getSize();


  Map<String, Object> getOtherStyles();
}
