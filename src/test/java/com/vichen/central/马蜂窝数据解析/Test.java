package com.vichen.central.马蜂窝数据解析;

import com.alibaba.fastjson.JSON;

public class Test {

  @org.junit.Test public void test() {
    String str =
      "08:30-16:30；停止售票时间:15:30；停止入场时间:15:40 (11月01日-次年03月31日 周二-周日)->08:30-17:00；停止售票时间:16:00；停止入场时间:16:10 (04月01日-10月31日 周二-周日)->tips:->除法定节假日外，全年实行周一全天闭馆；->如果遇到重大活动或特殊情况，开放时间如有改变，建议提前参考官网，安排出行；->遇到法定假期也会延长或缩短开放时间，建议参考官网;";
    System.out.println(JSON.toJSONString(str.split("；")));
  }
}
