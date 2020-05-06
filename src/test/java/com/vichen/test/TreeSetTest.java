package com.vichen.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {
  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test public void test() {
    Set testSet = new TreeSet<>((Comparator<String>) (o1, o2) -> o1.equals(o2) ?
      0 :
      Integer.parseInt(o1) - Integer.parseInt(o2));
    testSet.add("1");
    testSet.add("2");
    testSet.add("4");
    testSet.add("3");
    ArrayList arrayList = new ArrayList();
    arrayList.add(1);


    logger.warn("------------{}", 123);
  }
}
