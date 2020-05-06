package com.vichen.test.createSpringMemberInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairlink.ddi.module.pojo.MemberInfo;
import org.junit.Test;

public class CreateSpringMemberInfoTest {

  @Test public void test() {
    System.out.println(JSON.toJSONString(createSpringMemberInfo(JSONObject.parseObject(
      "{\"applyType\":0,\"email\":\"\",\"flightDate\":\"2019-11-01\",\"flightNum\":\"9C6123\",\"idNum\":\"666982\",\"idRegister\":\"adfa49f5-edbd-4483-98cc-7f0d82d4e2c4\",\"idType\":2,\"name\":\"ZHAO/HUANTONG\",\"password\":\"\",\"phoneNum\":\"17367186831\",\"placeDst\":\"WNZ\",\"placeOrg\":\"SWA\",\"seatNum\":\"13A\"}",
      MemberInfo.class))));
  }


  private BeanOfApplyLyhy createSpringMemberInfo(MemberInfo memberInfo) {
    BeanOfApplyLyhy bean = new BeanOfApplyLyhy();
    bean.setApplyType(memberInfo.getApplyType() != 0 ? 3 : 0);
    bean.setCardNoLast6(memberInfo.getIdNum());
    bean.setCardType(memberInfo.getIdType());
    if (bean.getApplyType() == 0) {
      bean.setPhoneNo(memberInfo.getPhoneNum());
    } else {
      bean.setEmail(memberInfo.getEmail());
    }

    String name = memberInfo.getName();
    if (name.contains("/")) {
      bean.setFamilyName(name.substring(0, name.indexOf("/")));
      bean.setPersonalName(name.substring(name.indexOf("/") + 1));
    } else {
      bean.setFamilyName(name);
    }

    bean.setFlightsDate(memberInfo.getFlightDate());
    bean.setFlightsNo(memberInfo.getFlightNum());
    bean.setFlightsSegment(memberInfo.getPlaceOrg() + memberInfo.getPlaceDst());
    bean.setSeatNum(memberInfo.getSeatNum());
    return bean;
  }
}
