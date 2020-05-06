package com.vichen.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8_3 {

  private List<Java8_2.ResourceClick> resourceClicks = Arrays
    .asList(new Java8_2.ResourceClick(1, "张三", "a", "pad", 2),
      new Java8_2.ResourceClick(1, "张三", "a", "pad", 3),
      new Java8_2.ResourceClick(1, "张三", "b", "pc", 3),
      new Java8_2.ResourceClick(1, "张三", "c", "phone", 1),
      new Java8_2.ResourceClick(2, "王五", "a", "pad", 5),
      new Java8_2.ResourceClick(2, "王五", "b", "pc", 1),
      new Java8_2.ResourceClick(2, "王五", "c", "phone", 2),
      new Java8_2.ResourceClick(3, "赵六", "a", "pad", 3),
      new Java8_2.ResourceClick(3, "赵六", "b", "pc", 2),
      new Java8_2.ResourceClick(3, "赵六", "c", "phone", 4));

  @Test public void test() {

    //将某个key转为list
    System.out.println(
      resourceClicks.stream().map(Java8_2.ResourceClick::getName).collect(Collectors.toList()));

    System.out.println(
      resourceClicks.stream().map(Java8_2.ResourceClick::getName).collect(Collectors.toList()));


    System.out.println(JSON.toJSONString(resourceClicks.stream()
      .collect(Collectors.groupingBy(item -> item.getDevType() +"_"+ item.getName()))));

    System.out.println(
      resourceClicks.stream().map(Java8_2.ResourceClick::getClick).reduce(0, (i, j) -> i + j));

  }
}



