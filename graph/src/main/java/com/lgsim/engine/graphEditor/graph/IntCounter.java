package com.lgsim.engine.graphEditor.graph;

// TODO: thread safe?
@SuppressWarnings("unused")
public class IntCounter {
  private int count;

  public IntCounter()
  {
    this(0);
  }

  public IntCounter(int count) {
    this.count = count;
  }

  public void inc()
  {
    if (count != Integer.MAX_VALUE) {
      count += 1;
    } else {
      throw new RuntimeException("integer counter overflow");
    }
  }

  public void dec()
  {
    if (count != 0) {
      count -= 1;
    }
  }

  public int get() {
    return count;
  }
}
