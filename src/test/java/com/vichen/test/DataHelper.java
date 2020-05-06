package com.vichen.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class DataHelper {

  private static Logger logger = LoggerFactory.getLogger(DataHelper.class);

  @SuppressWarnings("rawtypes") public static List deserializeList(String str) {
    return JSONArray.parseArray(str, String.class);
  }

  @SuppressWarnings("rawtypes") public static String serializeList(List list) {
    return JSON.toJSONString(list);
  }

  public static String unGzip(String compressedStr) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = null;
    GZIPInputStream ginzip = null;
    byte[] compressed = null;
    String decompressed = null;
    try {
      compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
      in = new ByteArrayInputStream(compressed);
      ginzip = new GZIPInputStream(in);

      byte[] buffer = new byte[1024];
      int offset = -1;
      while ((offset = ginzip.read(buffer)) != -1) {
        out.write(buffer, 0, offset);
      }
      decompressed = out.toString();
    } catch (IOException e) {
      logger.error("GzipAnalyticsIfeSyncHandler-unGzip: unGzip failed,data:{}", compressedStr, e);
      e.printStackTrace();
    } finally {
      if (ginzip != null) {
        try {
          ginzip.close();
        } catch (IOException e) {
          logger
            .error("GzipAnalyticsIfeSyncHandler-unGzip: close ginzip failed,data:{}", compressedStr,
              e);
        }
      }
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          logger
            .error("GzipAnalyticsIfeSyncHandler-unGzip: close in failed,data:{}", compressedStr, e);
        }
      }
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
          logger
            .error("GzipAnalyticsIfeSyncHandler-unGzip: close out failed,data:{}", compressedStr,
              e);
        }
      }
    }

    return decompressed;
  }
}
