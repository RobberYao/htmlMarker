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
   * 生成xml文件（默认在E盘下生成xml文件）
   * 
   * @param text
   */
  public static void createNewXmlFile() {
    Date date = new Date();
    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateformat.format(date);
    Document doc = DocumentHelper.createDocument();// 创建一个xml文档
    doc.addComment("操作留痕xml");// 向xml文件中添加注释
    Element root = doc.addElement("html");// 创建一个名为students的节点，因为是第一个创建，所以是根节点,再通过doc创建一个则会报错。
    Element stuEle = root.addElement("body");// 在root节点下创建一个名为body的节点
    // Element nameEle = stuEle.addElement("p");// 给body节点添加一个子节点p
    // nameEle.setText(text);// 设置子节点的文本
    OutputFormat format = OutputFormat.createPrettyPrint();// 用于格式化xml内容和设置头部标签
    format.setEncoding("GBK");// 设置xml文档的编码为gbk
    Writer out;
    try {
      out = new FileWriter("E://" + currentDate + ".xml");// 创建一个输出流对象(默认地址E盘下)
      XMLWriter writer = new XMLWriter(out, format);// 创建一个dom4j创建xml的对象
      writer.write(doc);// 调用write方法将doc文档写到指定路径
      writer.close();
      System.out.print("生成XML文件成功");
    } catch (IOException e) {
      System.out.print("生成XML文件失败");
      e.printStackTrace();
    }
  }

  /**
   * 解析xml中body增加节点
   * <p>
   * xml
   * </p>
   * 
   * @param xml
   */
  public static void parseXml(String xml, String address) {
    SAXReader reader = new SAXReader();
    try {
      Document document = reader.read(new File(address));// 通过绝对路径address读取xml转成document
      Element bodyPoint = document.getRootElement().element("body");// 获取body节点
      bodyPoint.addElement("p").setText(xml);// 在body节点下添加借点p，并赋值。
      System.out.println("增加<p>"+xml+"</p>");
      writerToNewFile(document, address);
    } catch (DocumentException e1) {
      e1.printStackTrace();
    }
  }

  /**
   * 写入新文件
   * 
   * @param document
   */
  public static void writerToNewFile(Document document, String address) {
    OutputFormat format = OutputFormat.createPrettyPrint();// 输出格式
    format.setEncoding("GBK");// 设置编码
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
