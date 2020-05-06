package com.vichen.spring.test;

import com.vichen.Application;
import com.vichen.service.ITestService;
import groovy.util.logging.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 运行器
 * JUnit <4.4默认为Junit4ClassRunner：单类测试
 * JUnit >4.4默认为BlockJunit4ClassRunner：单类测试
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@Slf4j
public class Test {
  @Autowired
  private ITestService testServiceImpl3;

  @org.junit.Test public void test(){
    testServiceImpl3.test();
  }

}
