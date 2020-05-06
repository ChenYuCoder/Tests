package com.vichen.test;

/**
 * 锁测试，双线程交替数数
 */
public class LockTest {
  static Integer a = 0;
  private static Object lock = new Object();

  @org.junit.Test public void test() {
    new Thread(() -> {
      while (a < 10)
        synchronized (lock) {
          try {
            System.out.println("A:" + a++);
            lock.notify();
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
    }).start();

    new Thread(() -> {
      while (a < 10)
        synchronized (lock) {
          try {
            System.out.println("B:" + a++);
            lock.notify();
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
    }).start();


    TestClass t = new TestClass();
    new Thread(() -> {
      for (int i = 0; i < 10; i++)
        t.add();
    }).start();
    new Thread(() -> {
      for (int i = 0; i < 10; i++)
        t.add();
    }).start();
  }


  class TestClass {
    int n = 1;

    public synchronized void add() {
      try {
        System.out.println("线程:" + Thread.currentThread().getName() + "说：" + n);
        n++;
        this.notify();//唤醒处于等待状态的线程
        this.wait();  //进入等待状态
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}




