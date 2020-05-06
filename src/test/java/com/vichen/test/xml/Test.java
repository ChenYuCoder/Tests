package com.vichen.test.xml;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyu
 * @Date: 2018/11/30 14:37
 */
public class Test {



  @org.junit.Test public void test() {
    Table table = new Table();
    Rows rows = new Rows();
    List<Domain> domains = new ArrayList<>();
    domains.add(new Domain("1", "1"));
    domains.add(new Domain("2", "2"));
    domains.add(new Domain("3", "3"));
    rows.setDomainList(domains);
    table.setRows(rows);
    String str = beanToXml(table, Table.class);
    System.out.println(str);

    Table table2 = (Table) xmlToBean(str, Table.class);
    table2.getRows().getDomainList().forEach(System.out::println);

  }

  public Object xmlToBean(String xml, Class<?> load) {
    StringReader reader = new StringReader(xml);
    JAXBContext jaxbContext;
    Unmarshaller jaxbUnmarshaller;
    Object result = null;
    try {
      jaxbContext = JAXBContext.newInstance(load);
      jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      result = jaxbUnmarshaller.unmarshal(reader);
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String beanToXml(Object obj, Class<?> load) {
    StringWriter writer = new StringWriter();
    JAXBContext context;
    Marshaller marshaller;
    try {
      context = JAXBContext.newInstance(load);
      marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
      marshaller.marshal(obj, writer);
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    return writer.toString();
  }


}

