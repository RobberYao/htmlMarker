package com.html.util;

import java.io.File;
import java.io.IOException;

public class htmlUtil {
	private static String xmlName="D:/htmlExample.html";
	public static void main(String[] args) {
		htmlUtil.createNewHtml();
	}
	
	public static void createNewHtml(){
		File file=null;
		File dir = new File(xmlName);
        try {
			dir.createNewFile();
			System.out.println(dir.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
