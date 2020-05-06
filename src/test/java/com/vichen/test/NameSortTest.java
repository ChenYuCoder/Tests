package com.vichen.test;

import org.junit.Test;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class NameSortTest {
  @Test
  public void test(){

    List<Book> bookList = new ArrayList<>();
    bookList.add(new Book("吃"));
    bookList.add(new Book("张BBB"));
    bookList.add(new Book("中AAA"));
    bookList.add(new Book("a"));
    bookList.add(new Book("啊1"));
    bookList.add(new Book("啊2"));
    bookList.add(new Book("啊3"));
    bookList.add(new Book("啊4"));
    bookList.add(new Book("啊10"));
    bookList.add(new Book("啊20"));
    bookList.add(new Book("啊第一集"));
    bookList.add(new Book("啊第二集"));
    bookList.add(new Book("啊第三集"));
    bookList.add(new Book("啊第四集"));
    bookList.add(new Book("张XXX"));
    bookList.add(new Book("中CCC"));
    bookList.add(new Book("啊B"));
    bookList.add(new Book("吧"));
    bookList.add(new Book("吧啊"));
    bookList.add(new Book("张AAA"));
    bookList.add(new Book("吧啊啊啊"));
    bookList.add(new Book("吧擦擦擦"));
    bookList.add(new Book("c"));

    Collator collator = Collator.getInstance(Locale.CHINA);

    bookList = bookList.stream().sorted((b1, b2) -> {

      int compare_value = collator.compare(b1.getName(), b2.getName());
      if (compare_value > 0) {
        return 1;
      } else if (compare_value < 0) {
        return -1;
      }
      return 0;
    }).collect(Collectors.toList());

    bookList.forEach(System.out::println);

  }
  class Book{
    String name;

    public Book(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override public String toString() {
      return "Book{" + "name='" + name + '\'' + '}';
    }
  }
}

