package com.vichen.test.gps;

import org.junit.Test;

public class GpsTest {

  @Test
  public void test(){
    System.out.println(GeographicUtil.wgs84ToBd09(121.81247439260001,31.1395496214).toString());
  }
}
