package com.vichen.test;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MessageQueueTest {

  public static AbstractTask taskA = new TaskA();
  public static AbstractTask taskB = new TaskB();
  public static AbstractTask taskC = new TaskC();

  private ExecutorService pool =
    new ThreadPoolExecutor(1, 3, 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
      Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


  @Test public void test() {
    pool.submit(taskA);
    pool.submit(taskB);
    pool.submit(taskC);
    for (int i = 0; i < 100; i++) {
      taskA.put("A" + i);
      taskB.put("B" + i);
      taskC.put("C" + i);
    }
  }
}


abstract class AbstractTask implements Callable<Boolean> {
  private final Queue<String> queue = new LinkedBlockingQueue<>();

//  public AbstractTask() {
//    queue.offer("1");
//    queue.offer("2");
//    queue.offer("3");
//  }

  @Override public Boolean call() {
    synchronized (queue) {
      while (true) {
        String data = queue.poll();
        if (data == null) {
          try {
            System.out.println(
              Thread.currentThread().getName() + "---------" + this.getClass().getName()
                + "-----wait");
            queue.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else {
          doSomething(data);
        }
      }

    }
  }

  public void put(String data) {
    synchronized (queue) {
      System.out.println(Thread.currentThread().getName() + "---------" + this.getClass().getName()
        + "------notify");
      queue.offer(data);
      if(queue.size()>10){
        queue.notify();
      }
    }
  }

  abstract public void doSomething(String data);
}


class TaskA extends AbstractTask {
  @Override public void doSomething(String data) {
    System.out.println(
      Thread.currentThread().getName() + "---------" + this.getClass().getName() + "---do--"
        + data);
  }
}


class TaskB extends AbstractTask {
  @Override public void doSomething(String data) {
    System.out.println(
      Thread.currentThread().getName() + "---------" + this.getClass().getName() + "---do--"
        + data);
  }
}


class TaskC extends AbstractTask {
  @Override public void doSomething(String data) {
    System.out.println(
      Thread.currentThread().getName() + "---------" + this.getClass().getName() + "---do--"
        + data);
  }
}
