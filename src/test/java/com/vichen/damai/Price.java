package com.vichen.damai;

/**
 * 票价
 */
public class Price {
  /**
   * 票价ID
   */
  private long id;
  /**
   * 票价名称
   */
  private String name;
  /**
   * 票价类型：0=普通单票，1=套票
   */
  private int type;
  /**
   * 可售卖的最大库存数量
   */
  private int maxStock;
  /**
   * 价格，单位=分；
   */
  private int price;

  private boolean ableSell;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getMaxStock() {
    return maxStock;
  }

  public void setMaxStock(int maxStock) {
    this.maxStock = maxStock;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public boolean isAbleSell() {
    return ableSell;
  }

  public void setAbleSell(boolean ableSell) {
    this.ableSell = ableSell;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
