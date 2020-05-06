package com.vichen.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8_2 {

  private List<ResourceClick> resourceClicks = Arrays
    .asList(new ResourceClick(1, "张三", "a", "pad", 2), new ResourceClick(1, "张三", "a", "pad", 3),
      new ResourceClick(1, "张三", "b", "pc", 3), new ResourceClick(1, "张三", "c", "phone", 1),
      new ResourceClick(2, "王五", "a", "pad", 5), new ResourceClick(2, "王五", "b", "pc", 1),
      new ResourceClick(2, "王五", "c", "phone", 2), new ResourceClick(3, "赵六", "a", "pad", 3),
      new ResourceClick(3, "赵六", "b", "pc", 2), new ResourceClick(3, "赵六", "c", "phone", 4));

  @Test public void test() {

    //按资源Id聚合，devId聚合
    Map<Integer, Map<String, Integer>> amp1 = resourceClicks.stream().collect(Collectors
      .groupingBy(ResourceClick::getId,
        Collectors.groupingBy(ResourceClick::getDevId, Collectors.summingInt(p -> 1))));
    //按资源Id聚合，click汇总
    Map<Integer, Integer> amp2 = resourceClicks.stream().collect(Collectors
      .groupingBy(ResourceClick::getId,
        Collectors.reducing(0, ResourceClick::getClick, Integer::sum)));
    //按资源Id聚合，设备类型聚合、devId聚合
    Map<Integer, Map<String, Map<String, Integer>>> amp3 = resourceClicks.stream().collect(
      Collectors.groupingBy(ResourceClick::getId, Collectors.groupingBy(ResourceClick::getDevType,
        Collectors.groupingBy(ResourceClick::getDevId, Collectors.summingInt(p -> 1)))));
    //按资源Id聚合，设备类型聚合、click汇总
    Map<Integer, Map<String, Integer>> amp4 = resourceClicks.stream().collect(Collectors
      .groupingBy(ResourceClick::getId, Collectors.groupingBy(ResourceClick::getDevType,
        Collectors.reducing(0, ResourceClick::getClick, Integer::sum))));
    //按设备类型聚合、资源Id聚合、devId聚合
    Map<String, Map<Integer, Map<String, Integer>>> amp5 = resourceClicks.stream().collect(
      Collectors.groupingBy(ResourceClick::getDevType, Collectors.groupingBy(ResourceClick::getId,
        Collectors.groupingBy(ResourceClick::getDevId, Collectors.summingInt(p -> 1)))));
    //按设备类型聚合、资源Id聚合、click汇总
    Map<String, Map<Integer, Integer>> amp6 = resourceClicks.stream().collect(Collectors
      .groupingBy(ResourceClick::getDevType, Collectors.groupingBy(ResourceClick::getId,
        Collectors.reducing(0, ResourceClick::getClick, Integer::sum))));
    System.out.println("----------------按资源Id聚合，devId聚合---------------");
    System.out.println(amp1);
    System.out.println("----------------按资源Id聚合，click汇总---------------");
    System.out.println(amp2);
    System.out.println("----------------按资源Id聚合，设备类型聚合、devId聚合---------------");
    System.out.println(amp3);
    System.out.println("----------------按资源Id聚合，设备类型聚合、click汇总---------------");
    System.out.println(amp4);
    System.out.println("----------------按设备类型聚合、资源Id聚合、devId聚合---------------");
    System.out.println(amp5);
    System.out.println("----------------按设备类型聚合、资源Id聚合、click汇总---------------");
    System.out.println(amp6);

    List<Map<String, Integer>> list = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        Map<String, Integer> map = new HashMap<>();
        map.put(String.valueOf(i), j);
        list.add(map);
      }
    }

    System.out.println(list.stream().collect(Collectors
      .groupingBy(item -> item.keySet().stream().findFirst().get(), Collectors
        .mapping(item -> item.values().stream().findFirst().get(), Collectors.toList()))));
  }



  public static class ResourceClick {
    private int id;
    private String name;
    private String devId;
    private String devType;
    private int click;

    public ResourceClick(int id, String name, String devId, String devType, int click) {
      this.id = id;
      this.name = name;
      this.devId = devId;
      this.devType = devType;
      this.click = click;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDevId() {
      return devId;
    }

    public void setDevId(String devId) {
      this.devId = devId;
    }

    public String getDevType() {
      return devType;
    }

    public void setDevType(String devType) {
      this.devType = devType;
    }

    public int getClick() {
      return click;
    }

    public void setClick(int click) {
      this.click = click;
    }
  }

}



