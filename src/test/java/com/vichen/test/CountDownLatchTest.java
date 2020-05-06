package com.vichen.test;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
  @Test
  public void test() {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    new Thread(new T(countDownLatch)).start();
    new Thread(new T(countDownLatch)).start();
    new Thread(new T(countDownLatch)).start();
    new Thread(new T(countDownLatch)).start();
    new Thread(new T(countDownLatch)).start();

    countDownLatch.countDown();

    System.out.println("执行结束");
  }

  class T implements Runnable {

    private final CountDownLatch countDownLatch;

    T(CountDownLatch countDownLatch) {
      this.countDownLatch = countDownLatch;
    }

    @Override public void run() {
      try {
        System.out.println("开始执行");
        countDownLatch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
}


