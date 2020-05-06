package com.vichen.test.createSpringMemberInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for beanOfApplyLyhy complex type.
 * <p>
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="beanOfApplyLyhy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applyType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cardNoLast6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="familyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flightsDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flightsNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flightsSegment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seatNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "beanOfApplyLyhy", propOrder = {"applyType", "cardNoLast6", "cardType", "email",
  "familyName", "flightsDate", "flightsNo", "flightsSegment", "personalName", "phoneNo", "seatNum"})
public class BeanOfApplyLyhy {

  protected Integer applyType;
  protected String cardNoLast6;
  protected Integer cardType;
  protected String email;
  protected String familyName;
  protected String flightsDate;
  protected String flightsNo;
  protected String flightsSegment;
  protected String personalName;
  protected String phoneNo;
  protected String seatNum;

  /**
   * Gets the value of the applyType property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getApplyType() {
    return applyType;
  }

  /**
   * Sets the value of the applyType property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setApplyType(Integer value) {
    this.applyType = value;
  }

  /**
   * Gets the value of the cardNoLast6 property.
   *
   * @return possible object is {@link String }
   */
  public String getCardNoLast6() {
    return cardNoLast6;
  }

  /**
   * Sets the value of the cardNoLast6 property.
   *
   * @param value allowed object is {@link String }
   */
  public void setCardNoLast6(String value) {
    this.cardNoLast6 = value;
  }

  /**
   * Gets the value of the cardType property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getCardType() {
    return cardType;
  }

  /**
   * Sets the value of the cardType property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setCardType(Integer value) {
    this.cardType = value;
  }

  /**
   * Gets the value of the email property.
   *
   * @return possible object is {@link String }
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the value of the email property.
   *
   * @param value allowed object is {@link String }
   */
  public void setEmail(String value) {
    this.email = value;
  }

  /**
   * Gets the value of the familyName property.
   *
   * @return possible object is {@link String }
   */
  public String getFamilyName() {
    return familyName;
  }

  /**
   * Sets the value of the familyName property.
   *
   * @param value allowed object is {@link String }
   */
  public void setFamilyName(String value) {
    this.familyName = value;
  }

  /**
   * Gets the value of the flightsDate property.
   *
   * @return possible object is {@link String }
   */
  public String getFlightsDate() {
    return flightsDate;
  }

  /**
   * Sets the value of the flightsDate property.
   *
   * @param value allowed object is {@link String }
   */
  public void setFlightsDate(String value) {
    this.flightsDate = value;
  }

  /**
   * Gets the value of the flightsNo property.
   *
   * @return possible object is {@link String }
   */
  public String getFlightsNo() {
    return flightsNo;
  }

  /**
   * Sets the value of the flightsNo property.
   *
   * @param value allowed object is {@link String }
   */
  public void setFlightsNo(String value) {
    this.flightsNo = value;
  }

  /**
   * Gets the value of the flightsSegment property.
   *
   * @return possible object is {@link String }
   */
  public String getFlightsSegment() {
    return flightsSegment;
  }

  /**
   * Sets the value of the flightsSegment property.
   *
   * @param value allowed object is {@link String }
   */
  public void setFlightsSegment(String value) {
    this.flightsSegment = value;
  }

  /**
   * Gets the value of the personalName property.
   *
   * @return possible object is {@link String }
   */
  public String getPersonalName() {
    return personalName;
  }

  /**
   * Sets the value of the personalName property.
   *
   * @param value allowed object is {@link String }
   */
  public void setPersonalName(String value) {
    this.personalName = value;
  }

  /**
   * Gets the value of the phoneNo property.
   *
   * @return possible object is {@link String }
   */
  public String getPhoneNo() {
    return phoneNo;
  }

  /**
   * Sets the value of the phoneNo property.
   *
   * @param value allowed object is {@link String }
   */
  public void setPhoneNo(String value) {
    this.phoneNo = value;
  }

  /**
   * Gets the value of the seatNum property.
   *
   * @return possible object is {@link String }
   */
  public String getSeatNum() {
    return seatNum;
  }

  /**
   * Sets the value of the seatNum property.
   *
   * @param value allowed object is {@link String }
   */
  public void setSeatNum(String value) {
    this.seatNum = value;
  }

}
