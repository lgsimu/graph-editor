package com.lgsim.engine.graphEditor.graph.graph;

// TODO: thread safe?
class IntCounter {

  private int count;


  IntCounter(int count) {
    this.count = count;
  }


  void inc()
  {
    if (count != Integer.MAX_VALUE) {
      count += 1;
    }
    else {
      throw new RuntimeException("integer counter overflow");
    }
  }


  public void dec()
  {
    if (count != 0) {
      count -= 1;
    }
  }


  int get() {
    return count;
  }
}
