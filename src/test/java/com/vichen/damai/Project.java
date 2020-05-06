package com.vichen.damai;

import java.util.Date;
import java.util.List;

/**
 * 项目
 */
public class Project {
  /**
   * 项目ID
   */
  private long id;
  /**
   * 项目名称
   */
  private String name;
  /**
   * 是否有做：true：有做，false：无座
   */
  private boolean hasSeat;

  /**
   * 项目分类名称
   */
  private String classifyName;

  /**
   * 国家
   */
  private String country;
  /**
   * 省份
   */
  private String province;
  /**
   * 城市
   */
  private String city;
  /**
   * 介绍
   */
  private String introduce;
  /**
   * 海报
   */
  private String posterUrl;
  /**
   * 项目状态，0：创建中 10：已创建 20：待销售 30：销售中 40：项目取消 50：项目结束
   */
  private String status;
  /**
   * 项目类型 0:普通项目 1:预售项目 2:先付先抢-先付项目 3:先付先抢-先抢项目 4:搭售项目 5:超级票
   */
  private String type;
  /**
   * 备注
   */
  private String remark;
  /**
   * 二级分类
   */
  private String subClassifyName;
  /**
   * 三级分类
   */
  private String thirdClassifyName;
  /**
   * 主办方名称
   */
  private String traderNamesArrList;
  /**
   * 场馆纬度
   */
  private double venueLat;
  /**
   * 场馆经度
   */
  private double venueLng;
  /**
   * 场馆名称
   */
  private String venueName;
  /**
   * 场馆地址
   */
  private String venueAddress;

  /**
   * 场次数据
   */
  private List<Perform> performList;
  /**
   * 	演出详情
   */
  private String showDetail;
  /**
   * 演出开售时间
   */
  private Date showStartTime;
  /**
   * 	演出销售结束时间
   */
  private Date showEndTime;
  /**
   * 	限购说明
   */
  private String limitNotice;
  /**
   * 	是否支持选座
   */
  private String choiceSeatNotice;
  /**
   * 实名制购票
   */
  private String realNameNotice;
  /**
   * 	儿童购票说明
   */
  private String childrenNotice;
  /**
   * 	退换政策
   */
  private String policyOfReturn;
  /**
   * 入场说明
   */
  private String entranceNotice;
  /**
   * 电子票入场提示
   */
  private String eticketNotice;
  /**
   * 自助取票
   */
  private String selfGetTicketNotice;
  /**
   * 寄存说明
   */
  private String depositInfo;
  /**
   * 禁止携带物品说明
   */
  private String prohibitedItems;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isHasSeat() {
    return hasSeat;
  }

  public void setHasSeat(boolean hasSeat) {
    this.hasSeat = hasSeat;
  }

  public String getClassifyName() {
    return classifyName;
  }

  public void setClassifyName(String classifyName) {
    this.classifyName = classifyName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getIntroduce() {
    return introduce;
  }

  public void setIntroduce(String introduce) {
    this.introduce = introduce;
  }

  public String getPosterUrl() {
    return posterUrl;
  }

  public void setPosterUrl(String posterUrl) {
    this.posterUrl = posterUrl;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getSubClassifyName() {
    return subClassifyName;
  }

  public void setSubClassifyName(String subClassifyName) {
    this.subClassifyName = subClassifyName;
  }

  public String getThirdClassifyName() {
    return thirdClassifyName;
  }

  public void setThirdClassifyName(String thirdClassifyName) {
    this.thirdClassifyName = thirdClassifyName;
  }

  public String getTraderNamesArrList() {
    return traderNamesArrList;
  }

  public void setTraderNamesArrList(String traderNamesArrList) {
    this.traderNamesArrList = traderNamesArrList;
  }

  public double getVenueLat() {
    return venueLat;
  }

  public void setVenueLat(double venueLat) {
    this.venueLat = venueLat;
  }

  public double getVenueLng() {
    return venueLng;
  }

  public void setVenueLng(double venueLng) {
    this.venueLng = venueLng;
  }

  public String getVenueName() {
    return venueName;
  }

  public void setVenueName(String venueName) {
    this.venueName = venueName;
  }

  public String getVenueAddress() {
    return venueAddress;
  }

  public void setVenueAddress(String venueAddress) {
    this.venueAddress = venueAddress;
  }

  public List<Perform> getPerformList() {
    return performList;
  }

  public void setPerformList(List<Perform> performList) {
    this.performList = performList;
  }

  public String getShowDetail() {
    return showDetail;
  }

  public void setShowDetail(String showDetail) {
    this.showDetail = showDetail;
  }

  public Date getShowStartTime() {
    return showStartTime;
  }

  public void setShowStartTime(Date showStartTime) {
    this.showStartTime = showStartTime;
  }

  public Date getShowEndTime() {
    return showEndTime;
  }

  public void setShowEndTime(Date showEndTime) {
    this.showEndTime = showEndTime;
  }

  public String getLimitNotice() {
    return limitNotice;
  }

  public void setLimitNotice(String limitNotice) {
    this.limitNotice = limitNotice;
  }

  public String getChoiceSeatNotice() {
    return choiceSeatNotice;
  }

  public void setChoiceSeatNotice(String choiceSeatNotice) {
    this.choiceSeatNotice = choiceSeatNotice;
  }

  public String getRealNameNotice() {
    return realNameNotice;
  }

  public void setRealNameNotice(String realNameNotice) {
    this.realNameNotice = realNameNotice;
  }

  public String getChildrenNotice() {
    return childrenNotice;
  }

  public void setChildrenNotice(String childrenNotice) {
    this.childrenNotice = childrenNotice;
  }

  public String getPolicyOfReturn() {
    return policyOfReturn;
  }

  public void setPolicyOfReturn(String policyOfReturn) {
    this.policyOfReturn = policyOfReturn;
  }

  public String getEntranceNotice() {
    return entranceNotice;
  }

  public void setEntranceNotice(String entranceNotice) {
    this.entranceNotice = entranceNotice;
  }

  public String getEticketNotice() {
    return eticketNotice;
  }

  public void setEticketNotice(String eticketNotice) {
    this.eticketNotice = eticketNotice;
  }

  public String getSelfGetTicketNotice() {
    return selfGetTicketNotice;
  }

  public void setSelfGetTicketNotice(String selfGetTicketNotice) {
    this.selfGetTicketNotice = selfGetTicketNotice;
  }

  public String getDepositInfo() {
    return depositInfo;
  }

  public void setDepositInfo(String depositInfo) {
    this.depositInfo = depositInfo;
  }

  public String getProhibitedItems() {
    return prohibitedItems;
  }

  public void setProhibitedItems(String prohibitedItems) {
    this.prohibitedItems = prohibitedItems;
  }
}
