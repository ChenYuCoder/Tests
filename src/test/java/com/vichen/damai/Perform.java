package com.vichen.damai;

import java.util.Date;
import java.util.List;

/**
 * 场次
 */
public class Perform {
  /**
   * 场次ID
   */
  private long id;
  /**
   * 场次名称
   */
  private String name;
  /**
   * 场次状态：0=创建中，10=已创建，20=待销售，30=销售中，40=演出取消，50=演出结束
   */
  private int status;
  /**
   * 票价列表
   */
  private List<Price> priceList;
  /**
   * 开始时间
   */
  private Date startTime;
  /**
   * 结束时间
   */
  private Date endTime;

  /**
   * 延改期原因
   */
  private String changeReason;
  /**
   * 是否修改了场次时间
   */
  private boolean changePerformTime;
  /**
   * 场次有效期规则 每周周几可以
   */
  private String weeklyList;
  /**
   * 场次类型(1 单场次，2 多次通票，3 单次通票)
   */
  private int type;
  /**
   * 备注
   */
  private String remark;
  /**
   * 是否对号入座 0：不对号入座 1：对号入座 2：对区入座
   */
  private int reserveSeat;

  //-------------------以下为场次设置
  /**
   * 证件类型("身份证"-"id_card","护照"-"passport","港澳居民来往内地通行证"-"hk_macao_pass","台湾居民来往大陆通行证"-"taiwan_compatriot_card","士兵／军官"-"soldier_officer_card")
   */
  private String cardType;
  /**
   * 是否一单一证
   */
  private boolean oneOrderOneCard;
  /**
   * 是否一票一证
   */
  private boolean oneTicketOneCard;
  /**
   * 是否实名制入场
   */
  private boolean realNameEnter;
  /**
   * 入场方式 1纸质票入场 2电子票入场
   */
  private String issueEnterModesList;
  /**
   * 出票方式 1纸质票 2静态二维码电子票 3动态二维码电子票 4身份证电子票 5 短信码电子票
   */
  private String issueTicketModesList;
  /**
   * 取票方式 1无纸化 2自助换票 3快递配送 4 上门自取
   */
  private String takeTicketTypes;
  /**
   * 销售设置 0开票 1预售
   */
  private int saleType;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public List<Price> getPriceList() {
    return priceList;
  }

  public void setPriceList(List<Price> priceList) {
    this.priceList = priceList;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getChangeReason() {
    return changeReason;
  }

  public void setChangeReason(String changeReason) {
    this.changeReason = changeReason;
  }

  public boolean isChangePerformTime() {
    return changePerformTime;
  }

  public void setChangePerformTime(boolean changePerformTime) {
    this.changePerformTime = changePerformTime;
  }

  public String getWeeklyList() {
    return weeklyList;
  }

  public void setWeeklyList(String weeklyList) {
    this.weeklyList = weeklyList;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public int getReserveSeat() {
    return reserveSeat;
  }

  public void setReserveSeat(int reserveSeat) {
    this.reserveSeat = reserveSeat;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public boolean isOneOrderOneCard() {
    return oneOrderOneCard;
  }

  public void setOneOrderOneCard(boolean oneOrderOneCard) {
    this.oneOrderOneCard = oneOrderOneCard;
  }

  public boolean isOneTicketOneCard() {
    return oneTicketOneCard;
  }

  public void setOneTicketOneCard(boolean oneTicketOneCard) {
    this.oneTicketOneCard = oneTicketOneCard;
  }

  public boolean isRealNameEnter() {
    return realNameEnter;
  }

  public void setRealNameEnter(boolean realNameEnter) {
    this.realNameEnter = realNameEnter;
  }

  public String getIssueEnterModesList() {
    return issueEnterModesList;
  }

  public void setIssueEnterModesList(String issueEnterModesList) {
    this.issueEnterModesList = issueEnterModesList;
  }

  public String getIssueTicketModesList() {
    return issueTicketModesList;
  }

  public void setIssueTicketModesList(String issueTicketModesList) {
    this.issueTicketModesList = issueTicketModesList;
  }

  public String getTakeTicketTypes() {
    return takeTicketTypes;
  }

  public void setTakeTicketTypes(String takeTicketTypes) {
    this.takeTicketTypes = takeTicketTypes;
  }

  public int getSaleType() {
    return saleType;
  }

  public void setSaleType(int saleType) {
    this.saleType = saleType;
  }
}
