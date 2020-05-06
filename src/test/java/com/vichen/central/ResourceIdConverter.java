package com.vichen.central;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 资源ID转换器
 */
public class ResourceIdConverter {
  private static final long GOODS = 0;
  private static final long Video = 1;
  private static final long EBook = 2;
  private static final long Music = 3;
  private static final long Application = 4;
  private static final long Ads = 5;
  private static final long WebContent = 6;
  private static final long ResourceType = 7;
  private static final long WarPackage = 8;
  private static final long News = 9;
  private static final long Article = 10;

  Logger logger = LoggerFactory.getLogger(ResourceIdConverter.class);

  @Test public void test() {

    //    System.out.println(toLongPrimaryKey(ResourceType, 10080051));
    //    System.out.println(toLongPrimaryKey(Video, 12345708));
    System.out.println(toIntPrimaryKey(64424509446L));
    System.out.println(toIntPrimaryKey(64424509570L));
    //    System.out.println(toResourceEnum(4314970336L));

  }

  public static void main(String[] args) {
  }

  public static long toLongPrimaryKey(Long type, int id) {
    return ((long) id & 0xFFFFFFFFL) | ((type << 32) & 0xFFFFFFFF00000000L);
  }

  public static int toIntPrimaryKey(long id) {
    return (int) (0xFFFFFFFFL & id);
  }

  public static int toResourceEnum(long id) {
    int t = (int) ((0xFFFFFFFF00000000L & id) >> 32);
    return t;
  }
}
