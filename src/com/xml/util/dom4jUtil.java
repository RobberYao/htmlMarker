package com.xml.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class dom4jUtil {

  public static void main(String[] args) {

    //dom4jUtil.createNewXmlFile();
    dom4jUtil.parseXml("3123","E://2017-05-11.xml");
  }

  /**
   * ����xml�ļ���Ĭ����E��������xml�ļ���
   * 
   * @param text
   */
  public static void createNewXmlFile() {
    Date date = new Date();
    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateformat.format(date);
    Document doc = DocumentHelper.createDocument();// ����һ��xml�ĵ�
    doc.addComment("��������xml");// ��xml�ļ������ע��
    Element root = doc.addElement("html");// ����һ����Ϊstudents�Ľڵ㣬��Ϊ�ǵ�һ�������������Ǹ��ڵ�,��ͨ��doc����һ����ᱨ��
    Element stuEle = root.addElement("body");// ��root�ڵ��´���һ����Ϊbody�Ľڵ�
    // Element nameEle = stuEle.addElement("p");// ��body�ڵ����һ���ӽڵ�p
    // nameEle.setText(text);// �����ӽڵ���ı�
    OutputFormat format = OutputFormat.createPrettyPrint();// ���ڸ�ʽ��xml���ݺ�����ͷ����ǩ
    format.setEncoding("GBK");// ����xml�ĵ��ı���Ϊgbk
    Writer out;
    try {
      out = new FileWriter("E://" + currentDate + ".xml");// ����һ�����������(Ĭ�ϵ�ַE����)
      XMLWriter writer = new XMLWriter(out, format);// ����һ��dom4j����xml�Ķ���
      writer.write(doc);// ����write������doc�ĵ�д��ָ��·��
      writer.close();
      System.out.print("����XML�ļ��ɹ�");
    } catch (IOException e) {
      System.out.print("����XML�ļ�ʧ��");
      e.printStackTrace();
    }
  }

  /**
   * ����xml��body���ӽڵ�
   * <p>
   * xml
   * </p>
   * 
   * @param xml
   */
  public static void parseXml(String xml, String address) {
    SAXReader reader = new SAXReader();
    try {
      Document document = reader.read(new File(address));// ͨ������·��address��ȡxmlת��document
      Element bodyPoint = document.getRootElement().element("body");// ��ȡbody�ڵ�
      bodyPoint.addElement("p").setText(xml);// ��body�ڵ�����ӽ��p������ֵ��
      System.out.println("����<p>"+xml+"</p>");
      writerToNewFile(document, address);
    } catch (DocumentException e1) {
      e1.printStackTrace();
    }
  }

  /**
   * д�����ļ�
   * 
   * @param document
   */
  public static void writerToNewFile(Document document, String address) {
    OutputFormat format = OutputFormat.createPrettyPrint();// �����ʽ
    format.setEncoding("GBK");// ���ñ���
    try {
      XMLWriter writer = new XMLWriter(new OutputStreamWriter(
          new FileOutputStream(new File(address)), "GBK"), format);
      writer.write(document);
      writer.flush();
      writer.close();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
