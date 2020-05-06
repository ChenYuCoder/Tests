package com.vichen.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test4 {

  private Map<Integer, Map<Integer, Integer>> map = new ConcurrentHashMap<>();


  @Test public void test() {
    for (int i = 0; i < 1000; i++) {
      new Thread(() -> {
        for (int j = 0; j < 1000; j++) {
          int key = (int) (Math.random() * 100);
          Map<Integer, Integer> temp = map.get(key);
          if (temp == null) {
            temp = new ConcurrentHashMap<>();
          }
          temp.put((int) (Math.random() * 100), j);
          map.put(key, temp);
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
      System.out.println(i);
    }

    System.out.println("-----------");
    new Thread(() -> {
      int index = 0;
      while (true) {
        System.out.println("-----------" + index);
        System.out.println(JSON.toJSONString(map).length());
        System.out.println("++++++++++++++" + index++);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();


    try {
      Thread.sleep(1000000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


}
