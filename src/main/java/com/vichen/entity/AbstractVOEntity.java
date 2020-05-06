package com.vichen.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * @author vichen
 */
public abstract class AbstractVOEntity {
  public static <T> T convert(Object daoObject,Class<T> clazz) {
    return JSONObject.parseObject(JSONObject.toJSONString(daoObject),clazz);
  }
}
