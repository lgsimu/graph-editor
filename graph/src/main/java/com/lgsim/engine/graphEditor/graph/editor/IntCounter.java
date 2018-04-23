package com.lgsim.engine.graphEditor.graph.editor;

// TODO: multiple thread?
@SuppressWarnings("unused")
class IntCounter {
  private int count;

  IntCounter()
  {
    this(0);
  }

  IntCounter(int count) {
    this.count = count;
  }

  void inc()
  {
    if (count != Integer.MAX_VALUE) {
      count += 1;
    } else {
      throw new RuntimeException("integer counter overflow");
    }
  }

  void dec()
  {
    if (count != 0) {
      count -= 1;
    }
  }

  int get() {
    return count;
  }
}
