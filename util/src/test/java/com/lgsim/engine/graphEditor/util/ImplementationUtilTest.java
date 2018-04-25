package com.lgsim.engine.graphEditor.util;

import org.junit.Test;

public class ImplementationUtilTest
{
  @Test
  public void test() throws InstantiationException
  {
    ImplementationUtil.putType(IFoo.class, FooImpl.class);
    IFoo foo = ImplementationUtil.getInstanceOf(IFoo.class);
    foo.foo();
  }
}