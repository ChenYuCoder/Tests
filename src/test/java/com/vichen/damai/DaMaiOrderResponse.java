package com.vichen.damai;

import java.util.List;

public class DaMaiOrderResponse {
  /**
   * 快递费
   */
  private int expressFee;
  /**
   * 大麦订单ID
   */
  private String orderId;
  /**
   * 总价
   */
  private int totalAmount;
  /**
   * 票列表
   */
  private List<DaMaiOrderDetailResponse> detailOderList;

  public int getExpressFee() {
    return expressFee;
  }

  public void setExpressFee(int expressFee) {
    this.expressFee = expressFee;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public int getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }

  public List<DaMaiOrderDetailResponse> getDetailOderList() {
    return detailOderList;
  }

  public void setDetailOderList(List<DaMaiOrderDetailResponse> detailOderList) {
    this.detailOderList = detailOderList;
  }
}
