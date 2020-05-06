package com.vichen.damai;

/**
 * 大麦订单
 */
public class DaMaiOrder {
  /**
   * 订单ID
   */
  private String orderId;
  /**
   * 项目ID
   */
  private long projectId;
  /**
   * 场次ID
   */
  private long performId;
  /**
   * 票价ID
   */
  private long priceId;
  /**
   * 票价
   */
  private Integer price;
  /**
   * 数量
   */
  private Integer quantity;
  /**
   * 出票方式 1=纸质票 2=身份证电子票 3=二维码电子票 4=短信电子票
   */
  private Integer ticketMode;
  /**
   * 取票方式： 1，无纸化；2，快递票；3，自助换票；4，门店自取。1和3为电子票，2和4为纸质票。
   */
  private Integer deliveryType;
  /**
   * 收货地址
   */
  private String deliverAddress;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public long getProjectId() {
    return projectId;
  }

  public void setProjectId(long projectId) {
    this.projectId = projectId;
  }

  public long getPerformId() {
    return performId;
  }

  public void setPerformId(long performId) {
    this.performId = performId;
  }

  public long getPriceId() {
    return priceId;
  }

  public void setPriceId(long priceId) {
    this.priceId = priceId;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getTicketMode() {
    return ticketMode;
  }

  public void setTicketMode(Integer ticketMode) {
    this.ticketMode = ticketMode;
  }

  public Integer getDeliveryType() {
    return deliveryType;
  }

  public void setDeliveryType(Integer deliveryType) {
    this.deliveryType = deliveryType;
  }

  public String getDeliverAddress() {
    return deliverAddress;
  }

  public void setDeliverAddress(String deliverAddress) {
    this.deliverAddress = deliverAddress;
  }
}
