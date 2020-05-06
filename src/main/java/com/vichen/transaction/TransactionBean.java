package com.vichen.transaction;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vichen
 */

@Entity @Table(name = "transaction_bean") public class TransactionBean {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
  @Column(name = "[unique]") private String unique;
  @Column(name = "[index]") private Integer index;

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUnique() {
    return unique;
  }

  public void setUnique(String unique) {
    this.unique = unique;
  }
}
