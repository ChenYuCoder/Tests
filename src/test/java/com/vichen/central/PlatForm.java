package com.vichen.central;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 设备类型
 */
public class PlatForm {
  private static String[] PAD_WHITE_LIST =
    new String[] {"pad", "w09", "b6000", "b8000", "b8080", "tablet", "kindle fire", "sch-i800",
      "xoom", "sm-t710", "t310"};
  private static String phoneReg =
    "\\b(ip(hone|od)|android|opera m(ob|in)i|windows (phone|ce)|blackberry|coolpad|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp|laystation portable)|nokia|fennec|htc[-_]|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
  private static String tableReg =
    "\\b(ipad|tablet|(Nexus 7)|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
  private static Pattern phonePat;
  private static Pattern tablePat;

  @Test
  public void test(){
    System.out.println(getPlatform(
      "Mozilla/5.0 (Linux; U; Android 8.0.0; zh-cn; VKY-AL00 Build/HUAWEIVKY-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/8.8 Mobile Safari/537.36000"));
  }

  public static PlatformEnum fromName(String name) {
    PlatformEnum[] var1 = PlatformEnum.values();
    int var2 = var1.length;

    for (int var3 = 0; var3 < var2; ++var3) {
      PlatformEnum platform = var1[var3];
      if (platform.getFieldName().equalsIgnoreCase(name)) {
        return platform;
      }
    }

    return null;
  }

  public static PlatformEnum getPlatform(String str) {
    if (null == str || "".equals(str)) {
      throw new RuntimeException("input string is blank!");
    } else {
      String info = str.toLowerCase();
      if (info.startsWith("android")) {
        return PlatformEnum.PUBLIC;
      } else {
        String[] var2 = PAD_WHITE_LIST;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
          String i = var2[var4];
          if (info.contains(i) && !info.contains("coolpad")) {
            return PlatformEnum.PAD;
          }
        }

        Matcher matcher = tablePat.matcher(info);
        if (matcher.find()) {
          return PlatformEnum.PAD;
        } else {
          matcher = phonePat.matcher(info);
          if (matcher.find()) {
            return PlatformEnum.PHONE;
          } else {
            return PlatformEnum.PC;
          }
        }
      }
    }
  }

  static {
    phonePat = Pattern.compile(phoneReg, 2);
    tablePat = Pattern.compile(tableReg, 2);
  }

  enum PlatformEnum {
    PUBLIC(1, "public", "公共设备"),
    PHONE(2, "phone", "手机"),
    PAD(3, "pad", "PAD"),
    PC(4, "pc", "PC"),
    ALL(5, "all", "ALL");

    private int fieldCode;
    private String fieldName;
    private String fieldTitle;

    private PlatformEnum(int fieldCode, String fieldName, String fieldTitle) {
      this.fieldCode = fieldCode;
      this.fieldName = fieldName;
      this.fieldTitle = fieldTitle;
    }

    public int getFieldCode() {
      return this.fieldCode;
    }

    public void setFieldCode(int fieldCode) {
      this.fieldCode = fieldCode;
    }

    public String getFieldName() {
      return this.fieldName;
    }

    public void setFieldName(String fieldName) {
      this.fieldName = fieldName;
    }

    public String getFieldTitle() {
      return this.fieldTitle;
    }

    public void setFieldTitle(String fieldTitle) {
      this.fieldTitle = fieldTitle;
    }
  }

}

