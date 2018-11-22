package com.lah.toolBean;

import java.io.UnsupportedEncodingException;
public class MyTools {
    public static String toChinese(String str){
    	if(str==null)
    		str="";
    	try{//通过String类的构造方法将指定的字符串转化为中文
    		str=new String(str.getBytes("ISO-8859-1"),"UTF-8");
    	}
    	catch(UnsupportedEncodingException e){
    		str="";
    		e.printStackTrace();
    	}
    	return str;
    }
}
