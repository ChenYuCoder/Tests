package com.vichen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class DataTools {

  static Logger logger = LoggerFactory.getLogger(DataTools.class);

  public static String getSystemEncode() {
    String result = "";
    try {
      result = System.getProperty("file.encoding");
    } catch (Exception e) {
      logger.error("", e);
    }
    return result;
  }

  public static Integer getIntbyStr(String num) {
    Integer result = 0;
    try {
      if (num == null || num.isEmpty()) {
        return null;
      }
      result = Integer.parseInt(num);
    } catch (Exception e) {
      logger.error("", e);
      result = null;
    }
    return result;
  }

  public static Long getLongbyStr(String num) {
    Long result = 0L;
    try {
      if (num == null || num.isEmpty()) {
        return null;
      }
      result = Long.parseLong(num);
    } catch (Exception e) {
      logger.error("", e);
      result = null;
    }
    return result;
  }

  public static String getFixNum(int num, int len) {
    String result = "";
    try {
      result = num + "";
      while (result.length() > num) {
        result = "0" + result;
      }
      if (result.length() > len) {
        result = result.substring(result.length() - len, result.length());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public static String getMD5(String args, int len) {
    String result = "";
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(args.getBytes("UTF-8"));
      byte[] b = md.digest();
      int i;
      StringBuffer buf = new StringBuffer("");
      for (int offset = 0; offset < b.length; offset++) {
        i = b[offset];
        if (i < 0) {
          i += 256;
        }
        if (i < 16) {
          buf.append("0");
        }
        buf.append(Integer.toHexString(i));
      }
      if (len <= 16) {
        result = buf.toString().substring(8, 24);
      } else {
        result = buf.toString();
      }
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String getUuid() {
    String result = null;
    try {
      UUID uuid = UUID.randomUUID();
      result = uuid.toString().replace("-", "");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return result.trim();
  }

  public static int getRand(int max) {

    Random rand = new Random();
    rand.setSeed(new Date().getTime());
    return (Math.abs(rand.nextInt(Integer.MAX_VALUE))) % max;

  }

  public static Integer getIntByString(String data) {
    Integer result = null;
    try {
      result = Integer.parseInt(data);
    } catch (Exception e) {
      logger.error("", e);
      result = null;
    }
    return result;
  }

  public static Long getLongByString(String data) {
    Long result = null;
    try {
      result = Long.valueOf(data);
    } catch (Exception e) {
      logger.error("", e);
    }
    return result;
  }

  public static Double getDoubleByString(String data) {
    Double result = null;
    try {
      result = Double.valueOf(data);
    } catch (Exception e) {
      logger.error("", e);
    }
    return result;
  }


}
