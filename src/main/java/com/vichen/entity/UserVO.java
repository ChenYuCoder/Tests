package com.vichen.entity;

/**
 * Created by admin on 2017/4/21.
 */

public class UserVO extends AbstractVOEntity {
  private String name;
  private int age;
  private boolean sex;

  public UserVO(String name, int age, boolean sex) {
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  public UserVO() {
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
    return "UserVO{" + "name='" + name + '\'' + ", age=" + age + ", sex=" + sex + '}';
  }


}
