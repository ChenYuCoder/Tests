package com.vichen.controller;

import com.alibaba.fastjson.JSON;
import com.fairlink.common.messagequeue.MessageNotifier;
import com.fairlink.common.messagequeue.QueueType;
import com.vichen.config.Person;
import com.vichen.entity.User;
import com.vichen.entity.UserVO;
import com.vichen.messageQueue.ActiveMqProducer;
import com.vichen.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
//import org.thymeleaf.spring4.view.ThymeleafView;


/**
 * Created by admin on 2017/4/21.
 */
@Controller @RequestMapping("/sys") public class SystemController {

  @Autowired ActiveMqProducer activeMqProducer;
  @Autowired SystemService service;

  @RequestMapping("/hello") @ResponseBody public String hello() {
    return service.helloWorld();
  }

  @RequestMapping("/test") public String test() {
    return "test";
  }

  @RequestMapping("/user") @ResponseBody public String user(User user) {
    return JSON.toJSONString(user);
  }


  @RequestMapping("/ws") public String ws() {
    return "ws";
  }

  @Autowired private Person person;

  @RequestMapping("/getInvokeClientInfo") @ResponseBody public String getInvokeClientInfo() {

    String ip = "";
    try {
      ip = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return ip;
  }

  @RequestMapping("/getTestConfig") @ResponseBody public String getTestConfig() {
    return person.getMaps().toString();
  }

  @RequestMapping("/getTestVO") @ResponseBody public String getTestVO() {
    User user = new User();
    user.setAge(18);
    user.setName("vichen");
    user.setSex(true);

    UserVO userVO = UserVO.convert(user, UserVO.class);
    return userVO.toString();
  }


  @RequestMapping("/getTestBeanParams") @ResponseBody
  public String getTestBeanParams(@Valid User user) {
    return JSON.toJSONString(user);
  }

  @RequestMapping("/testSentMessage") @ResponseBody
  public String testSentMessage(@RequestParam(name = "message") String message) {
    try {
      MessageNotifier.INSTANCE.push(QueueType.P2P, "SpringBootTest", message);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //    activeMqProducer.send("SpringBootTest", message);
    return "true";
  }


  @PostMapping("/postTest") @ResponseBody
  public String getTest(String orderId, String payInfo, String phone) {
    System.out.println(orderId);
    System.out.println(payInfo);
    System.out.println(phone);
    return "test";
  }



}
