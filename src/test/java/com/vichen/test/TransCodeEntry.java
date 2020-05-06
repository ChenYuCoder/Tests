package com.vichen.test;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class TransCodeEntry {

  private Integer customerId;
  private String type;
  private String origin;
  private String dest;
  private Object resource;
  private String fileType;
  private List<FileEntry> destFiles;
  private JSONObject param;

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDest() {
    return dest;
  }

  public void setDest(String dest) {
    this.dest = dest;
  }

  public Object getResource() {
    return resource;
  }

  public void setResource(Object resource) {
    this.resource = resource;
  }

  public JSONObject getParam() {
    return param;
  }

  public void setParam(JSONObject param) {
    this.param = param;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public List<FileEntry> getDestFiles() {
    return destFiles;
  }

  public void setDestFiles(List<FileEntry> destFiles) {
    this.destFiles = destFiles;
  }

  public void addProperity(String key, Object value) {
    if (param == null) {
      param = new JSONObject();
    }
    param.put(key, value);
  }

  public static class FileEntry {

    private String type;
    private String path;

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }
  }
}
