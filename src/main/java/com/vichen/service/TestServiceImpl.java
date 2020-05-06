package com.vichen.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author vichen
 */
@Service @Profile("!unittest111")
public class TestServiceImpl implements ITestService {
  @Override public void test() {
    System.out.println("test");
  }
}
