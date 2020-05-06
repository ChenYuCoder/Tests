package com.vichen.damai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaDamaiMaitixOrderConfirmRequest;
import com.taobao.api.request.AlibabaDamaiMaitixOrderDistributionCreateRequest;
import com.taobao.api.request.AlibabaDamaiMaitixProjectDistributionDetailQueryRequest;
import com.taobao.api.request.AlibabaDamaiMaitixProjectDistributionQueryRequest;
import com.taobao.api.request.AlibabaDamaiMaitixProjectDistributionQuerybypageRequest;
import com.taobao.api.response.AlibabaDamaiMaitixOrderConfirmResponse;
import com.taobao.api.response.AlibabaDamaiMaitixOrderDistributionCreateResponse;
import com.taobao.api.response.AlibabaDamaiMaitixProjectDistributionDetailQueryResponse;
import com.taobao.api.response.AlibabaDamaiMaitixProjectDistributionQueryResponse;
import com.taobao.api.response.AlibabaDamaiMaitixProjectDistributionQuerybypageResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DaMaiTest {
  private static TaobaoClient client =
    new DefaultTaobaoClient("", "",
      "");

  private JSONObject getProjectListByPageResponse(long page) throws ApiException {
    AlibabaDamaiMaitixProjectDistributionQuerybypageRequest req =
      new AlibabaDamaiMaitixProjectDistributionQuerybypageRequest();
    AlibabaDamaiMaitixProjectDistributionQuerybypageRequest.ProjectPageParam param =
      new AlibabaDamaiMaitixProjectDistributionQuerybypageRequest.ProjectPageParam();
    param.setPageNo(1L);
    param.setPageSize(10L);
    req.setParam(param);

    AlibabaDamaiMaitixProjectDistributionQuerybypageResponse rsp = client.execute(req);

    if (rsp == null || "50".equals(rsp.getCode())) {
      return null;
    }

    JSONObject response = JSONObject.parseObject(rsp.getBody())
      .getJSONObject("alibaba_damai_maitix_project_distribution_querybypage_response")
      .getJSONObject("result");

    if (!response.getBooleanValue("success")) {
      return null;
    }

    return response.getJSONObject("model");
  }

  /**
   * 获取项目list
   *
   * @return
   */
  public List<Project> getProjectListByPage() {

    try {
      int currentPage = 0;
      int pageCount = 0;

      List<Project> projectInfoList = new ArrayList<>();

      do {
        currentPage++;
        JSONObject response = getProjectListByPageResponse(currentPage);
        if (response == null) {
          continue;
        }
        pageCount = response.getIntValue("page_count");
        JSONArray dataArrList = response.getJSONObject("data_arr_list").getJSONArray("project_dto");

        if (dataArrList == null) {
          continue;
        }
        dataArrList.forEach(item -> {
          JSONObject itemJson = (JSONObject) item;
          Project project = new Project();
          project.setId(itemJson.getLongValue("project_id"));
          project.setName(itemJson.getString("project_name"));
          project.setHasSeat(itemJson.getIntValue("is_has_seat") == 1);
          project.setType(itemJson.getString("project_type"));
          project.setStatus(itemJson.getString("project_status"));
          project.setClassifyName(itemJson.getString("classify_name"));
          project.setSubClassifyName(itemJson.getString("sub_classify_name"));
          project.setThirdClassifyName(itemJson.getString("third_classify_name"));
          project.setPosterUrl(itemJson.getString("poster_url"));
          project.setIntroduce(itemJson.getString("introduce"));
          project.setRemark(itemJson.getString("remark"));
          project.setTraderNamesArrList(
            itemJson.getJSONObject("trader_names_arr_list").getString("string"));
          JSONObject venueJson = itemJson.getJSONObject("venue");
          project.setVenueName(venueJson.getString("name"));
          project.setVenueLat(venueJson.getDoubleValue("lat"));
          project.setVenueLng(venueJson.getDoubleValue("lng"));
          project.setVenueAddress(venueJson.getString("venue_address"));
          project.setCountry(itemJson.getJSONObject("country").getString("name"));
          project.setProvince(itemJson.getJSONObject("province").getString("name"));
          project.setCity(itemJson.getJSONObject("city").getString("name"));

          //填充详情信息
          project = getProjectDetailInfo(project);
          if (project == null) {
            return;
          }

          //填充价格场次信息
          project = getProjectPerformAndPrice(project);
          if (project == null) {
            return;
          }

          projectInfoList.add(project);
        });
      } while (pageCount > currentPage);


      return projectInfoList;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }


  /**
   * 通过项目主要信息装填项目详细信息
   *
   * @param projectInfo 主要项目信息
   * @return 详情项目信息
   */

  public Project getProjectPerformAndPrice(Project projectInfo) {
    AlibabaDamaiMaitixProjectDistributionQueryRequest req =
      new AlibabaDamaiMaitixProjectDistributionQueryRequest();
    req.setProjectId(projectInfo.getId());
    try {

      AlibabaDamaiMaitixProjectDistributionQueryResponse rsp = client.execute(req);

      if (rsp == null || "50".equals(rsp.getCode())) {
        return null;
      }

      JSONObject response = JSONObject.parseObject(rsp.getBody())
        .getJSONObject("alibaba_damai_maitix_project_distribution_query_response")
        .getJSONObject("result");

      if (!response.getBooleanValue("success")) {
        return null;
      }

      JSONArray performInfosData =
        response.getJSONObject("model_list").getJSONArray("project_info_dto").getJSONObject(0)
          .getJSONObject("perform_info_list").getJSONArray("perform_info_dto");

      if (performInfosData == null) {
        return null;
      }

      List<Perform> projectInfoList = new ArrayList<>(performInfosData.size());

      //装填场次数据
      for (Object performObj : performInfosData) {
        JSONObject performJson = (JSONObject) performObj;

        Perform perform = new Perform();
        perform.setId(performJson.getLongValue("perform_id"));
        perform.setName(performJson.getString("perform_name"));
        perform.setStatus(performJson.getIntValue("perform_status"));
        perform.setStartTime(performJson.getDate("start_time"));
        perform.setEndTime(performJson.getDate("end_time"));

        perform.setChangeReason(performJson.getString("change_reason"));
        perform.setChangePerformTime(performJson.getIntValue("is_change_perform_time") == 1);
        perform.setType(performJson.getIntValue("perform_type"));
        perform.setRemark(performJson.getString("remark"));
        perform.setReserveSeat(performJson.getIntValue("reserve_seat"));
        perform.setWeeklyList(performJson.getString("weekly_list"));

        JSONObject settingJson = performJson.getJSONObject("perform_setting");
        perform.setCardType(settingJson.getString("card_type"));
        perform.setOneOrderOneCard(settingJson.getIntValue("is_one_order_one_card") == 1);
        perform.setOneTicketOneCard(settingJson.getIntValue("is_one_ticket_one_card") == 1);
        perform.setRealNameEnter(settingJson.getIntValue("is_real_name_enter") == 1);
        perform.setIssueEnterModesList(
          settingJson.getJSONObject("issue_enter_modes_list").getString("number"));
        perform.setIssueTicketModesList(
          settingJson.getJSONObject("issue_ticket_modes_list").getString("number"));
        perform.setTakeTicketTypes(settingJson.getString("take_ticket_types"));
        perform.setSaleType(settingJson.getIntValue("sale_type"));

        //装填票价数据
        JSONArray priceJsonArray =
          performJson.getJSONObject("price_info_list").getJSONArray("price_info_dto");
        if (priceJsonArray == null) {
          return null;
        }

        List<Price> priceList = new ArrayList<>(priceJsonArray.size());
        for (Object priceObj : priceJsonArray) {
          JSONObject priceJson = (JSONObject) priceObj;

          if(priceJson.containsKey("open_combine_prices")){
            System.out.println(1);
          }

          Price price = new Price();
          price.setId(priceJson.getLongValue("price_id"));
          price.setName(priceJson.getString("price_name"));
          price.setPrice(JSON.parseObject(priceJson.getString("price")).getIntValue("cent"));
          price.setMaxStock(priceJson.getIntValue("max_stock"));
          price.setType(priceJson.getIntValue("type"));
          price.setAbleSell(priceJson.getBooleanValue("able_sell"));

          priceList.add(price);
        }
        perform.setPriceList(priceList);

        projectInfoList.add(perform);
      }

      projectInfo.setPerformList(projectInfoList);


      return projectInfo;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 通过项目主要信息装填项目详细信息
   *
   * @param projectInfo 主要项目信息
   * @return 详情项目信息
   */

  public Project getProjectDetailInfo(Project projectInfo) {
    AlibabaDamaiMaitixProjectDistributionDetailQueryRequest req =
      new AlibabaDamaiMaitixProjectDistributionDetailQueryRequest();
    req.setProjectId(projectInfo.getId());
    try {

      AlibabaDamaiMaitixProjectDistributionDetailQueryResponse rsp = client.execute(req);

      if (rsp == null || "50".equals(rsp.getCode())) {
        return null;
      }

      JSONObject response = JSONObject.parseObject(rsp.getBody())
        .getJSONObject("alibaba_damai_maitix_project_distribution_detail_query_response")
        .getJSONObject("result");

      if (response.getIntValue("code") != 0) {
        return null;
      }

      JSONObject productJson = response.getJSONObject("model");

      if (productJson == null) {
        return null;
      }

      projectInfo.setShowStartTime(productJson.getDate("show_start_time"));
      projectInfo.setShowEndTime(productJson.getDate("show_end_time"));
      projectInfo.setPosterUrl(productJson.getString("show_pic"));
      projectInfo.setShowDetail(productJson.getString("show_detail"));
      projectInfo.setChildrenNotice(productJson.getString("children_notice"));
      projectInfo.setSelfGetTicketNotice(productJson.getString("self_get_ticket_notice"));
      projectInfo.setChoiceSeatNotice(productJson.getString("choice_seat_notice"));
      projectInfo.setRealNameNotice(productJson.getString("real_name_notice"));
      projectInfo.setLimitNotice(productJson.getString("limit_notice"));
      projectInfo.setProhibitedItems(productJson.getString("prohibited_items"));
      projectInfo.setDepositInfo(productJson.getString("deposit_info"));
      projectInfo.setEntranceNotice(productJson.getString("entrance_notice"));
      projectInfo.setEticketNotice(productJson.getString("eticket_notice"));
      projectInfo.setPolicyOfReturn(productJson.getString("policy_of_return"));

      return projectInfo;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * alibaba.damai.maitix.project.distribution.querybypage  分页查询项目
   * alibaba.damai.maitix.project.distribution.query
   * alibaba.damai.maitix.project.distribution.detail.query
   * alibaba.damai.maitix.order.distribution.create
   * alibaba.damai.maitix.order.confirm 出票
   * alibaba.damai.maitix.order.query
   * alibaba.damai.maitix.eticket.distribution.query
   * alibaba.damai.maitix.order.directrefund
   */



  /**
   * 创建下单
   *
   * @param daMaiOrder 大麦订单数据
   */
  public DaMaiOrderResponse createOrder(DaMaiOrder daMaiOrder) {
    AlibabaDamaiMaitixOrderDistributionCreateRequest req =
      new AlibabaDamaiMaitixOrderDistributionCreateRequest();
    AlibabaDamaiMaitixOrderDistributionCreateRequest.MoaOrderParam obj1 =
      new AlibabaDamaiMaitixOrderDistributionCreateRequest.MoaOrderParam();
    /**
     * 项目信息
     */
    obj1.setProjectId(daMaiOrder.getProjectId());
    obj1.setPerformId(daMaiOrder.getPerformId());
    /**
     * 第三方id
     */
    obj1.setThirdOrderNo(daMaiOrder.getOrderId());
    /**
     * 订单信息
     */
    Integer totalPrice = daMaiOrder.getPrice() * daMaiOrder.getQuantity();
    obj1.setTotalPrice(totalPrice.longValue());
    obj1.setTicketMode(daMaiOrder.getTicketMode().longValue());

    obj1.setPayment(totalPrice.longValue());
    obj1.setBuyType(2L);
    obj1.setDeliverAddress(daMaiOrder.getDeliverAddress());
    obj1.setDeliveryType(daMaiOrder.getDeliveryType().longValue());
    //    obj1.setOperatorLoginId("zhangsan");
    obj1.setPayType(1L);
    /**
     * 实名制信息
     */
    //    obj1.setRealTicketBuyerName("张三");
    //    obj1.setRealTicketBuyerIdCardNo("310100100000");
    //    obj1.setRealTicketBuyerIdCardType(1L);
    //    obj1.setRealTicketBuyerPhone("15510913776");
    //    obj1.setRealTicketBuyerPhoneCountryCode("086");
    /**
     * 是否自动选座
     */
    obj1.setAutoSelectSeats(true);
    //    obj1.setMemo("备注");
    /**
     * 超时取消
     */
    obj1.setTimeoutMinutes(10L);
    List<AlibabaDamaiMaitixOrderDistributionCreateRequest.MoaTicketInfo> list3 = new ArrayList<>();
    for (int i = 0; i < daMaiOrder.getQuantity(); i++) {
      AlibabaDamaiMaitixOrderDistributionCreateRequest.MoaTicketInfo obj4 =
        new AlibabaDamaiMaitixOrderDistributionCreateRequest.MoaTicketInfo();
      list3.add(obj4);
      /**
       * 票相关实名信息和票信息
       */
      //    obj4.setCombineId(10L);
      //    obj4.setExternalSubOrderNo("121233");
      //    obj4.setRealTicketOwnerIdCardNo("210881199305102222");
      //    obj4.setRealTicketOwnerIdCardType(1L);
      //    obj4.setRealTicketOwnerName("陈宇");
      //    obj4.setRealTicketOwnerPhone("1300000000");
      //        obj4.setRealTicketOwnerPhoneCountryCode("086");
      //    obj4.setSeatId(1001244L);
      /**
       * 票价ID
       */
      {
        obj4.setTicketItemId(daMaiOrder.getPriceId());
      }
    }
    obj1.setSeatProps(list3);
    List<AlibabaDamaiMaitixOrderDistributionCreateRequest.MoaTicketItemSpec> list6 =
      new ArrayList<>();
    for (int i = 0; i < daMaiOrder.getQuantity(); i++) {
      AlibabaDamaiMaitixOrderDistributionCreateRequest.MoaTicketItemSpec obj7 =
        new AlibabaDamaiMaitixOrderDistributionCreateRequest.MoaTicketItemSpec();
      list6.add(obj7);
      /**
       * 购买信息
       */
      //      obj7.setIsPackage(0L);
      obj7.setPrice(daMaiOrder.getPrice().longValue());
      obj7.setQuantity(1L);
      obj7.setTicketItemId(daMaiOrder.getPriceId());
    }
    obj1.setTicketItems(list6);
    AlibabaDamaiMaitixOrderDistributionCreateRequest.MoaOrderContactInfo obj8 =
      new AlibabaDamaiMaitixOrderDistributionCreateRequest.MoaOrderContactInfo();
    /**
     * 联系人信息
     */
    obj8.setContactName("陈宇");
    obj8.setCountryCode("86");
    obj8.setEmail("yu.chen@fairlinkcentury.com");
    obj8.setPhone("15510913776");
    obj1.setContactInfo(obj8);
    req.setParam(obj1);


    DaMaiOrderResponse daMaiOrderResponse = new DaMaiOrderResponse();

    try {

      AlibabaDamaiMaitixOrderDistributionCreateResponse rsp = client.execute(req);

      if (rsp == null || "50".equals(rsp.getCode())) {
        return null;
      }

      JSONObject response = JSONObject.parseObject(rsp.getBody())
        .getJSONObject("alibaba_damai_maitix_order_distribution_create_response")
        .getJSONObject("result");

      if (response.getIntValue("code") != 8000200) {
        return null;
      }

      JSONObject orderJson = response.getJSONObject("model");

      if (orderJson == null) {
        return null;
      }

      daMaiOrderResponse.setOrderId(orderJson.getString("order_id"));
      int totalAmount = orderJson.getIntValue("total_amount");
      daMaiOrderResponse.setTotalAmount(totalAmount);
      daMaiOrderResponse.setExpressFee(orderJson.getIntValue("express_fee"));

      JSONArray detailOrderJsonArray =
        orderJson.getJSONObject("sub_order_dtos").getJSONArray("lock_ticket_sub_order_dto");

      List<DaMaiOrderDetailResponse> daMaiOrderDetailResponseList = new ArrayList<>(totalAmount);
      detailOrderJsonArray.forEach(item -> {
        JSONObject detailOrderJson = (JSONObject) item;
        DaMaiOrderDetailResponse daMaiOrderDetailResponse = new DaMaiOrderDetailResponse();
        daMaiOrderDetailResponse.setVoucherId(detailOrderJson.getLongValue("voucher_id"));
        daMaiOrderDetailResponse.setOriginPrice(detailOrderJson.getIntValue("origin_price"));
        daMaiOrderDetailResponse.setRealPrice(detailOrderJson.getIntValue("real_price"));
        JSONObject seatJson = detailOrderJson.getJSONObject("sub_order_seat_dto");
        daMaiOrderDetailResponse.setEntry(seatJson.getString("entry"));
        daMaiOrderDetailResponse.setStandName(seatJson.getString("stand_name"));
        daMaiOrderDetailResponse.setSeatAreaName(seatJson.getString("seat_area_name"));
        daMaiOrderDetailResponse.setSeatFloorName(seatJson.getString("seat_floor_name"));
        daMaiOrderDetailResponse.setSeatRowName(seatJson.getString("seat_row_name"));
        daMaiOrderDetailResponse.setSeatGroup(seatJson.getIntValue("seat_group"));
        daMaiOrderDetailResponse.setSeatName(seatJson.getString("seat_name"));
        daMaiOrderDetailResponseList.add(daMaiOrderDetailResponse);
      });
      daMaiOrderResponse.setDetailOderList(daMaiOrderDetailResponseList);
      return daMaiOrderResponse;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  public boolean confirmOrder(long orderId) {
    AlibabaDamaiMaitixOrderConfirmRequest req = new AlibabaDamaiMaitixOrderConfirmRequest();
    AlibabaDamaiMaitixOrderConfirmRequest.MoaConfirmOrderParam obj1 =
      new AlibabaDamaiMaitixOrderConfirmRequest.MoaConfirmOrderParam();
    obj1.setOrderId(orderId);
    req.setParam(obj1);
    try {

      AlibabaDamaiMaitixOrderConfirmResponse rsp = client.execute(req);

      if (rsp == null || "50".equals(rsp.getCode())) {
        return false;
      }

      JSONObject response = JSONObject.parseObject(rsp.getBody())
        .getJSONObject("alibaba_damai_maitix_order_confirm_response").getJSONObject("result");

      if (response.getIntValue("code") != 8000200) {
        return false;
      }

      JSONObject orderResult = response.getJSONObject("model");

      if (orderResult == null || orderResult.getIntValue("pay_status") != 1) {
        return false;
      }

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Test public void test1() {
    System.out.println(JSON.toJSONString(getProjectListByPage()));
  }

  @Test public void test2() {
    Project project = new Project();
    project.setId(201343043L);
    System.out.println(JSON.toJSONString(getProjectPerformAndPrice(project)));
  }

  @Test public void test3() {
    DaMaiOrder daMaiOrder = new DaMaiOrder();
    daMaiOrder.setOrderId("1");
    daMaiOrder.setProjectId(201547018);
    daMaiOrder.setPerformId(210135539);
    daMaiOrder.setPriceId(222249314);
    daMaiOrder.setPrice(10000);
    daMaiOrder.setQuantity(1);
    //1=纸质票 2=身份证电子票 3=二维码电子票 4=短信电子票
    daMaiOrder.setTicketMode(1);
    //1，无纸化；2，快递票；3，自助换票；4，门店自取。1和3为电子票，2和4为纸质票。
    daMaiOrder.setDeliveryType(2);
    daMaiOrder.setDeliverAddress("天津市南开区红日南路环兴科技园B座636");
    System.out.println(JSON.toJSONString(createOrder(daMaiOrder)));
  }

  @Test public void test4() {
    confirmOrder(969179810813L);
  }



}
