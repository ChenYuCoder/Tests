package com.vichen.test;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8 {


  @Test public void test() throws UnsupportedEncodingException {

    String str = "str";
    System.out.println("\n\r----------------------------------------------------");
    List<Student> students =
      Arrays.asList(new Student(0, "小A", true, 18), new Student(0, "小B", false, 16));
    students.forEach(student -> System.out.println(student.toString()));
    Map<String, Integer> map =
      students.stream().collect(Collectors.toMap(Student::getName, Student::getAge));
    map.forEach((k, v) -> System.out.println(k + ":" + v + str));
    System.out.println("\n\r----------------------------------------------------");
    Person person = (name -> System.out.printf("hello :" + name));
    person.sayHello("ViChen");
    System.out.println("\n\r----------------------------------------------------");
    List<Integer> array =
      Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 3, 5, 7, 8, 5, 4, 1, 2, 3, 5, 6, 4, 8, 9);
    System.out.println("去重1");
    new HashSet<>(array).forEach(System.out::print);
    System.out.println("去重2");
    array.stream().distinct().collect(Collectors.toList()).forEach(System.out::print);
    System.out.println("偶数");
    array.stream().filter(a -> a % 2 == 0).collect(Collectors.toList()).forEach(System.out::print);
    System.out.println("取合");
    System.out.print(students.stream().mapToInt(Student::getAge).sum());
    System.out.println("平方");
    array.stream().map(i -> i * i).collect(Collectors.toList()).forEach(System.out::print);
    System.out.println("过滤");
    System.out.print(array.stream().distinct().filter(i -> i > 5).count());
    System.out.println("倒序");
    array.stream().sorted((i1, i2) -> i2 - i1).collect(Collectors.toList())
      .forEach(System.out::print);
    Collections.sort(array, (i1, i2) -> i2 - i1);
    System.out.println("正序");
    array.stream().sorted().collect(Collectors.toList()).forEach(System.out::print);
    System.out.println("取平均数");
    System.out.println(array.stream().mapToInt(i -> i).summaryStatistics().getAverage());
    System.out.println("\n\r----------------------------------------------------");
    System.out.println(LocalDate.now());
    System.out.println(LocalDate.of(2014, 10, 10));
    System.out.println(LocalDateTime.now());
    System.out.println(LocalDateTime.now().withDayOfMonth(5).withHour(0));
    System.out.println("\n\r----------------------------------------------------");
    String encode = Base64.getEncoder().encodeToString("测试base64编码与解码".getBytes("utf-8"));
    System.out.println(encode);
    System.out.println(new String(Base64.getDecoder().decode(encode), "utf-8"));

  }

  interface Person {
    void sayHello(String name);
  }


  public class Student {
    private int id;
    private String name;
    private boolean sex;
    private int age;

    public Student(int id, String name, boolean sex, int age) {
      this.id = id;
      this.name = name;
      this.sex = sex;
      this.age = age;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public boolean isSex() {
      return sex;
    }

    public void setSex(boolean sex) {
      this.sex = sex;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    @Override public String toString() {
      return "Student{" + "id=" + id + ", name='" + name + '\'' + ", sex=" + sex + ", age=" + age
        + '}';
    }
  }
}



