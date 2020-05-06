package com.vichen.test;

import com.fairlink.ufai.module.pojo.Result;
import org.junit.Test;

public class MoveFileTest {




  private static boolean rename(String originFileName, String targetFileName) {

    java.io.File file = new java.io.File(originFileName);
    if (!file.exists()) {
      return false;
    }

    if (!file.getPath().equals(targetFileName)) {
      return file.renameTo(new java.io.File(targetFileName));
    }
    return false;
  }

@Test
  public void renameTo() {

    String originAbsoluteFileName = "/Users/vichen/Desktop/B.js";

    if (!rename(originAbsoluteFileName, originAbsoluteFileName + ".temp")) {
    }
    String targetAbsoluteFileName = "/Users/vichen/Desktop/A/B.js";

    if (!rename(originAbsoluteFileName + ".temp", targetAbsoluteFileName)) {
    }

  }
}
