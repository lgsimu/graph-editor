package com.lgsim.engine.graphEditor.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtilTest
{
  @Test
  public void cloneList()
  {
    List<FooBean> beans = new ArrayList<>(3);
    beans.add(new FooBean(1, 2));
    beans.add(new FooBean(3, 5));
    beans.add(new FooBean(4, 9));
    List<FooBean> newBeans = CollectionUtil.cloneList(beans);
    newBeans.get(0).setApple(3);
    Assert.assertEquals(beans.get(0).getApple(), 1);
  }
}