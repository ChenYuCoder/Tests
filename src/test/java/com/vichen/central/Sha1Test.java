package com.vichen.central;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Sha1Test {

  //43a56eb156e60447c542aab03a54ae76015e0863
  //5b23f902d368ffd30d97986764354cb496d11e8f

  private static final String ARCHIVE_ROOT_FOLDER = "/object/";

  @org.junit.Test public void test() {
    System.out.println(calcFileSha1(
      "/Users/vichen/Desktop/mnt/xvdb3/html/data/source/object/68/0ebfb916671744d8eb767a643fa81a376a6a9b/",
      true));
  }

  public static String calcFileSha1(String filePath, boolean update) {

    File file = new File(filePath);

    if (!file.exists()) {
      //      logger.error("[calcFileSha1]file is not exist. filePath:{}", filePath);
      return null;
    }

    if (!file.isDirectory()) {
      try (InputStream is = new FileInputStream(filePath)) {
        return DigestUtils.sha1Hex(is);
      } catch (Exception e) {
        //        logger.error("[calcFileSha1]failed to calc sha1. fileName:{}", filePath, e);
        return null;
      }
    }

    Map<String, String> originAndSha1FileNameMap = new HashMap<>();
    SortedSet<FileEntry> sha1AndFileLength = new TreeSet<>((o1, o2) -> {
      if (o1.getLength() == o2.getLength()) {
        return 0;
      } else {
        return o1.getLength() < o2.getLength() ? -1 : 1;
      }
    });

    Comparator<File> comparator = (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName());
    for (final File originFile : Arrays.stream(Objects.requireNonNull(file.listFiles()))
      .sorted(comparator).collect(Collectors.toList())) {

      String originFilePath = originFile.getAbsolutePath();
      String[] nameAndSuffix = originFile.getName().split("\\.");
      //计算sha1值
      String sha1;
      if (originFile.getName().endsWith("shal.shal")) {
        continue;
      }
      try (InputStream is = new FileInputStream(originFilePath)) {
        sha1 = DigestUtils.sha1Hex(is);
      } catch (Exception e) {
        //        logger.error("[calcFileSha1]failed to calc sha1. fileName:{}", filePath, e);
        continue;
      }
      //缓存全部sha1值，用于后面计算总sha1路径（这里使用set进行缓存，length排序，如果length相同则只保存一份）
      sha1AndFileLength.add(new FileEntry(originFile.length(), sha1));
      //根据sha1生成shal和文件名对应的map
      originAndSha1FileNameMap.put(nameAndSuffix[0] + "." + nameAndSuffix[1], sha1);
    }
    //如果不想增加shal文件就为false
    if (update) {
      File shalFile = new File(filePath + File.separator + "shal.shal");
      try {
        Files.write(Paths.get(shalFile.getAbsolutePath()),
          JSONObject.toJSONString(originAndSha1FileNameMap).getBytes());
      } catch (Exception shle) {
        //        logger.error("create shalFile failed data:{}", originAndSha1FileNameMap, shle);
      }
    }
    //根据文件大小排序sha1值，然后拼接字符串，计算总sha1值
    StringBuffer sha1Buffer = new StringBuffer();
    sha1AndFileLength.forEach(enter -> sha1Buffer.append(enter.getSha1()));
    return DigestUtils.sha1Hex(sha1Buffer.toString());

  }


  private static class FileEntry {
    public long getLength() {
      return length;
    }

    public void setLength(long length) {
      this.length = length;
    }

    public String getSha1() {
      return sha1;
    }

    public void setSha1(String sha1) {
      this.sha1 = sha1;
    }

    private long length;
    private String sha1;

    public FileEntry() {
    }

    public FileEntry(long length, String sha1) {
      this.length = length;
      this.sha1 = sha1;
    }

    @Override public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      FileEntry fileEntry = (FileEntry) o;
      return length == fileEntry.length && Objects.equals(sha1, fileEntry.sha1);
    }

    @Override public int hashCode() {
      return Objects.hash(length, sha1);
    }
  }

}



