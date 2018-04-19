package com.lgsim.engine.graphEditor.graph.editor;

class Counter
{
  private int count;


  Counter()
  {
    count = 0;
  }


  int incInt()
  {
    if (count != Integer.MAX_VALUE)
    {
      count += 1;
    }
    return count;
  }


  int decInt()
  {
    if (count != 0)
    {
      count -= 1;
    }
    return count;
  }
}
