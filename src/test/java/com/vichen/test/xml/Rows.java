package com.vichen.test.xml;


import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Rows {


  private List<Domain> domainList;
  @XmlElement(name = "row")
  public List<Domain> getDomainList() {
    return domainList;
  }

  public void setDomainList(List<Domain> domainList) {
    this.domainList = domainList;
  }
}
