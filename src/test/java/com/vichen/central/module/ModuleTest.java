package com.vichen.central.module;

import com.alibaba.fastjson.JSON;
import com.vichen.central.module.Module;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModuleTest {
  @Test public void test() {
    String str =
      "[{\"name\":\"db_init\",\"version\":\"3.13.13.5\"},{\"name\":\"ife_config\",\"version\":\"3.13.13.5\"},{\"name\":\"attraction\",\"versio\n"
        + "n\":\"3.0\"},{\"name\":\"travel_guide\",\"version\":\"20180903\"},{\"name\":\"news\",\"version\":\"2.5.0.4\"},{\"name\":\"ROOT\",\"version\":\"3.13.13.5\"},{\"name\":\"web\",\"version\":\"3.13.13.9\"},{\"name\":\"map\",\"version\":\"3.0\"},{\"name\":\"SystemImage\",\"version\":\"ChinaExpressAirline/0.9.1.2\"},{\"name\":\"t\n"
        + "omcat\",\"version\":\"7.0.75.6\"},{\"name\":\"mysql\",\"version\":\"20180904\"},{\"name\":\"ife_service_module\",\"version\":\"3.13.13.2\"},{\"name\":\"news_module\",\"version\":\"3.13.13.1\"},{\"name\":\"docker_base\"},{\"name\":\"vac_image\",\"version\":\"1.0.0.0\"}]";
    List<Module> list = JSON.parseArray(str, Module.class);
    Map<String, String> moduleMap = list.stream().collect(Collectors.toMap(Module::getName, Module::getVersion));
    System.out.printf(JSON.toJSONString(moduleMap));
  }
}
