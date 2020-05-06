package com.vichen.test;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTest {


  @org.junit.Test public void test() {
    zipFile("C:\\Users\\admin\\Desktop\\1197.zip", "C:\\Users\\admin\\Desktop\\picture");
  }


  public static void zipFile(String zipDir, String destDir) {
    try {
      ZipInputStream zin = new ZipInputStream(new FileInputStream(zipDir));// 输入源zip路径
      BufferedInputStream bin = new BufferedInputStream(zin);
      String parent = destDir + File.separator; // 输出路径（文件夹目录）
      File fout = null;
      ZipEntry entry;
      try {
        while ((entry = zin.getNextEntry()) != null && !entry.isDirectory()) {
          fout = new File(parent, entry.getName());
          if (!fout.exists()) {
            (new File(fout.getParent())).mkdirs();
          }
          FileOutputStream out = new FileOutputStream(fout);
          BufferedOutputStream bout = new BufferedOutputStream(out);
          int b;
          while ((b = bin.read()) != -1) {
            bout.write(b);
          }
          bout.close();
          out.close();
        }
        bin.close();
        zin.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

}



