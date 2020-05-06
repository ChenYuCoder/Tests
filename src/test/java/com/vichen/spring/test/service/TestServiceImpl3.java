package com.vichen.spring.test.service;

import com.vichen.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * @author vichen
 */
@Service
public class TestServiceImpl3 implements ITestService {
  @Override public void test() {
    System.out.println("test3");
  }
}
