package com.vichen.test.xml;


import javax.xml.bind.annotation.XmlAttribute;

class Domain{

  private String c_org_name;


  private String c_index_code;
  @XmlAttribute(name="c_org_name")
  public String getC_org_name() {
    return c_org_name;
  }

  public void setC_org_name(String c_org_name) {
    this.c_org_name = c_org_name;
  }
  @XmlAttribute(name="c_index_code")
  public String getC_index_code() {
    return c_index_code;
  }

  public void setC_index_code(String c_index_code) {
    this.c_index_code = c_index_code;
  }

  @Override public String toString() {
    return "Domain{" + "c_org_name='" + c_org_name + '\'' + ", c_index_code='" + c_index_code
      + '\'' + '}';
  }

  public Domain(String c_org_name, String c_index_code) {
    this.c_org_name = c_org_name;
    this.c_index_code = c_index_code;
  }

  public Domain() {
  }
}
