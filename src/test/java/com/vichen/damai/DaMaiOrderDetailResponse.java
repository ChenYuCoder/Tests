package com.vichen.damai;

public class DaMaiOrderDetailResponse {
  /**
   * 票单ID
   */
  private Long voucherId;
  /**
   * 原价
   */
  private int originPrice;
  /**
   * 实际价格
   */
  private int realPrice;
  /**
   * 入场口
   */
  private String entry;
  /**
   * 区域名称
   */
  private String seatAreaName;
  /**
   * 楼层名称
   */
  private String seatFloorName;
  /**
   * 座位分组，0:无座 1:有座
   */
  private int seatGroup;
  /**
   * 座位名称
   */
  private String seatName;
  /**
   * 座位排名称
   */
  private String seatRowName;
  /**
   * 看台名称
   */
  private String standName;


  public int getOriginPrice() {
    return originPrice;
  }

  public void setOriginPrice(int originPrice) {
    this.originPrice = originPrice;
  }

  public int getRealPrice() {
    return realPrice;
  }

  public void setRealPrice(int realPrice) {
    this.realPrice = realPrice;
  }

  public Long getVoucherId() {
    return voucherId;
  }

  public void setVoucherId(Long voucherId) {
    this.voucherId = voucherId;
  }

  public String getEntry() {
    return entry;
  }

  public void setEntry(String entry) {
    this.entry = entry;
  }

  public String getSeatAreaName() {
    return seatAreaName;
  }

  public void setSeatAreaName(String seatAreaName) {
    this.seatAreaName = seatAreaName;
  }

  public String getSeatFloorName() {
    return seatFloorName;
  }

  public void setSeatFloorName(String seatFloorName) {
    this.seatFloorName = seatFloorName;
  }

  public int getSeatGroup() {
    return seatGroup;
  }

  public void setSeatGroup(int seatGroup) {
    this.seatGroup = seatGroup;
  }

  public String getSeatName() {
    return seatName;
  }

  public void setSeatName(String seatName) {
    this.seatName = seatName;
  }

  public String getSeatRowName() {
    return seatRowName;
  }

  public void setSeatRowName(String seatRowName) {
    this.seatRowName = seatRowName;
  }

  public String getStandName() {
    return standName;
  }

  public void setStandName(String standName) {
    this.standName = standName;
  }
}
