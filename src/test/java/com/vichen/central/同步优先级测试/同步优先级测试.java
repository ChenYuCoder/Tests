package com.vichen.central.同步优先级测试;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class 同步优先级测试 {
  static TaskTopoGraph ttg;

  static {
    String config = "Flight:null:Passenger,Steward,Config,Airport:true:1\n"
      + "Passenger:Flight:Goods,News,Application,EBook,"
      + "Music,Video,SmallVideo,WarPackage,WebPackage,ReservationInfo,WebContent,"
      + "ExternalApplication,ExternalProducerData:true:1\n"
      + "Steward:Flight:Goods,News,Application,EBook,Music,"
      + "Video,SmallVideo,WarPackage,WebPackage,ReservationInfo,WebContent,"
      + "ExternalApplication,ExternalProducerData:true:1\n"
      + "Config:Flight:Goods,News,Application,EBook,Music,"
      + "Video,SmallVideo,WarPackage,WebPackage,ReservationInfo,WebContent:true:1\n"
      + "Airport:Flight:Goods,News,Application,EBook,Music,"
      + "Video,SmallVideo,WarPackage,WebPackage,ReservationInfo,WebContent:true:1\n"
      + "WarPackage:Passenger,Steward:null:true:4\n" + "WebPackage:Passenger,Steward:null:true:4\n"
      + "ReservationInfo:Passenger,Steward:null:true:1\n" + "News:Passenger,Steward:null:true:10\n"
      + "Application:Passenger,Steward:ResourceType,ResourceRelation:true:4\n"
      + "EBook:Passenger,Steward:ResourceType,ResourceRelation:true:4\n"
      + "Music:Passenger,Steward:ResourceType,ResourceRelation:true:4\n"
      + "Video:Passenger,Steward:ResourceType,ResourceRelation:true:4\n"
      + "SmallVideo:Passenger,Steward:ResourceType,ResourceRelation:true:4\n"
      + "Goods:Passenger,Steward:ResourceType,ResourceRelation,Activity,ActivityGoods,GoodsLabelRelation:true:2\n"
      + "GoodsLabelRelation:Goods:null:true:5\n"
      + "WebContent:Passenger,Steward:ResourceType,ResourceRelation:true:4\n"
      + "ResourceType:Goods,EBook,Music,Video,SmallVideo,Application,WebContent:Ads,Activity,ActivityGoods:true:1\n"
      + "ResourceRelation:Goods,EBook,Music,Video,SmallVideo,Application,WebContent:Ads,Activity,ActivityGoods:true:1\n"
      + "Activity:ResourceType,ResourceRelation,Goods:null:true:1\n"
      + "ActivityGoods:ResourceType,ResourceRelation,Goods:null:true:1\n"
      + "Ads:ResourceType,ResourceRelation:null:true:4\n"
      + "OrderPay:null:PassengerRegister,MessageBoard,UserMessage,Analytics,SatelliteOrder,ExternalConsumerData:true:1\n"
      + "SatelliteData:null:null:true:1\n"
      + "CarReservationOrder:null:PassengerRegister,MessageBoard,UserMessage,"
      + "Analytics,ExternalConsumerData:true:1\n" + "PassengerRegister:OrderPay,CarReservationOrder:null:true:2\n"
      + "MessageBoard:OrderPay,CarReservationOrder:null:true:3\n"
      + "UserMessage:OrderPay,CarReservationOrder:null:true:3\n"
      + "SatelliteOrder:OrderPay:null:true:3\n"
      + "Analytics:OrderPay,CarReservationOrder:null:true:4\n"
      + "ExternalApplication:Passenger,Steward:null:true:5\n"
      + "ExternalProducerData:Passenger,Steward:null:true:5\n"
      + "ExternalConsumerData:OrderPay,CarReservationOrder:null:true:5\n";
    ttg = TaskTopoGraph.deserialize(config);
  }

  @Test public void test() {
    ttg.toString();
    Map<String, Boolean> map = new HashMap<String, Boolean>() {
      {
        put("Flight", true);
        put("Passenger", true);
        put("Steward", true);
        put("Config", true);
        put("Airport", true);
        put("Goods", true);
        put("Ads", true);
        put("News", true);
        put("Application", true);
        put("EBook", true);
        put("Music", true);
        put("Video", true);
        put("OrderPay", true);
        put("PassengerRegister", true);
        put("MessageBoard", true);
        put("Analytics", true);
        put("WarPackage", true);
        put("WebPackage", true);
        put("ResourceType", true);
        put("ResourceRelation", true);
        put("ReservationInfo", true);
        put("CarReservationOrder", true);
        put("UserMessage", true);
        put("WebContent", true);
        put("SatelliteData", true);
        put("SatelliteOrder", true);
        put("Activity", true);
        put("ActivityGoods", true);
        put("SmallVideo", true);
        put("GoodsLabelRelation", true);
        put("ExternalApplication", true);
        put("ExternalProducerData", true);
        put("ExternalConsumerData", true);
      }
    };
    buildTaskTopoGraph(map);
  }

  public TaskTopoGraph buildTaskTopoGraph(Map<String, Boolean> taskStatus) {
    TaskTopoGraph graph = new TaskTopoGraph();
    for (TaskNode tn : ttg) {
      if (!taskStatus.get(tn.getTaskName())) {
        continue;
      }
      TaskNode newTask = new TaskNode();
      newTask.setTaskName(tn.getTaskName());
      newTask.setGetTask(tn.isGetTask());
      newTask.setSidesOfDice(tn.getSidesOfDice());
      graph.addTask(newTask);
    }
    graph.buildTopoGraphByTemplate(ttg);
    return graph;
  }
}
