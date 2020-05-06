package com.vichen.system;

import com.vichen.controller.MyWebSocket;

import java.io.IOException;
import java.util.Scanner;

public class WebSocketThread extends Thread{
  private final MyWebSocket myWebSocket;

  WebSocketThread(MyWebSocket myWebSocket) {
    this.myWebSocket = myWebSocket;
  }

  @Override public void run() {

    System.out.println("开始监听");
    Scanner sc = new Scanner(System.in);
    while (true) {
      //接受整数
      String i = sc.nextLine();
      System.out.println("发送消息：" + i);
      try {
        myWebSocket.sendInfo(i);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
