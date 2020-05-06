package com.vichen.central;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 获取登机时间
 */
public class GetBoardingTime {
  @Test public void test() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    String lastAtaStr = "2018-09-25 00:00:00";
    String nextAtdStr = "2018-09-25 00:20:00";
    long lastAta = LocalDateTime.parse(lastAtaStr, dateTimeFormatter).toInstant(ZoneOffset.of("+8"))
      .toEpochMilli();
    long nextAtd = LocalDateTime.parse(nextAtdStr, dateTimeFormatter).toInstant(ZoneOffset.of("+8"))
      .toEpochMilli();
    long boardTime;
    final Long INTERVAL = 7 * 20 * 60 * 1000L;
    if (nextAtd - lastAta > INTERVAL) { //飞机的atd减去上一航班的ata
      boardTime = nextAtd - 2 * 60 * 60 * 1000L;
    } else if (nextAtd - lastAta > 20 * 60 * 1000L) {
      boardTime = lastAta + 20 * 60 * 1000L;
    } else {
      boardTime = nextAtd;
    }
    System.out.println(LocalDateTime.ofEpochSecond(boardTime / 1000, 0, ZoneOffset.ofHours(8))
      .format(dateTimeFormatter));

  }
}
