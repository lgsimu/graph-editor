package com.lgsim.engine.graphEditor.graph.editor;

public class CavityCounter
{
  private int count;


  public CavityCounter()
  {
    count = 0;
  }


  public int incInt()
  {
    if (count != Integer.MAX_VALUE)
    {
      count += 1;
    }
    return count;
  }


  public int decInt()
  {
    if (count != 0)
    {
      count -= 1;
    }
    return count;
  }
}
