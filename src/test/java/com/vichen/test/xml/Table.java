package com.vichen.test.xml;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="table")
public class Table {


 private Rows rows;

  @XmlElement(name = "rows")
  public Rows getRows() {
    return rows;
  }

  public void setRows(Rows rows) {
    this.rows = rows;
  }
}
