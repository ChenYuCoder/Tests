package com.vichen.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author vichen
 */
@Service @Profile("!unittest")
public class TestServiceImpl2 implements ITestService {

  @Override public void test() {
    System.out.println("test2");
  }
}
