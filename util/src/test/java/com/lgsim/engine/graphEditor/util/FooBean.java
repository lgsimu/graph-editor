package com.lgsim.engine.graphEditor.util;

import java.io.Serializable;

public class FooBean implements Serializable
{
  private int apple;

  private int branches;


  public FooBean(int apple, int branches)
  {
    this.apple = apple;
    this.branches = branches;
  }


  public int getApple()
  {
    return apple;
  }


  public void setApple(int apple)
  {
    this.apple = apple;
  }


  public int getBranches()
  {
    return branches;
  }


  public void setBranches(int branches)
  {
    this.branches = branches;
  }
}
