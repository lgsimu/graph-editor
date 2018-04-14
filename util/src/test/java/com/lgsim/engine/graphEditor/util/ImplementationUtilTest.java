package com.lgsim.engine.graphEditor.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImplementationUtilTest
{
  @Test
  public void test()
  {
    ImplementationUtil.put(IFoo.class, FooImpl.class);
    IFoo foo = ImplementationUtil.getInstanceOf(IFoo.class);
    if (foo != null)
    {
      foo.foo();
    }
  }
}