package com.vichen.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Created by admin on 2017/4/21.
 */

public class User {
  @NotBlank(message = "姓名不能为空") private String name;
  @Range(min = 1, max = 150, message = "年龄需要在1-150之间") private int age;
  @NotNull private boolean sex;

  public User(String name, int age, boolean sex) {
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  public User() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
  }

  @Override public String toString() {
    return "User{" + "name='" + name + '\'' + ", age=" + age + ", sex=" + sex + '}';
  }


}
