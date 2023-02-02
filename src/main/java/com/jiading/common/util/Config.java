package com.jiading.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

public class Config {
	
	
	/* public static int  depdrsl = 0; //单位导入的数量
	 public static int  depdrsuccess = 0; //单位导入成功的数量
	 public static int  depdrnum = 0; //单位导入总数量
*/

	/**
	 * 已经建立链接的对象缓存起来
	 */
	public static ConcurrentMap<String, Session> serverMap = new ConcurrentHashMap<String, Session>();

	 public static String getAutoKey(String strhead) {
		String autoid = "";
		Random r = new Random();
		String str = "abcdefhijkmnprstwxyz";//20
		for (int i = 0; i < 5; i++) {
			int num = r.nextInt(str.length());//0~20的随机数
			char c = str.charAt(num);
			autoid = autoid + c;//str的5个随机字母
		}
		return strhead + System.currentTimeMillis() + autoid;
	 }
	 
	 public static String ip () {
		 String ip ="";
		 try {
			 ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	 }
	 
	
	 public static String delHTMLTag(String htmlStr){ 
	        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
	        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
	        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
	         
	        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	        Matcher m_script=p_script.matcher(htmlStr); 
	        htmlStr=m_script.replaceAll(""); //过滤script标签 
	         
	        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	        Matcher m_style=p_style.matcher(htmlStr); 
	        htmlStr=m_style.replaceAll(""); //过滤style标签 
	         
	        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	        Matcher m_html=p_html.matcher(htmlStr); 
	        htmlStr=m_html.replaceAll(""); //过滤html标签 

	        return htmlStr.trim(); //返回文本字符串 
	    } 
	 
	 public static String CheckExt(String ext){ 
	    	String c_type = "";
	    	if("jpg".equals(ext) || "png".equals(ext) || "jpeg".equals(ext) || "bmp".equals(ext)
					|| "ico".equals(ext) || "gif".equals(ext)){
	    		c_type = "1";
			}else if( "mp4".equals(ext) || "mov".equals(ext)){
				c_type = "2";
			}else if("xlsx".equals(ext) || "xls".equals(ext)){
				c_type = "3";
			}else if("doc".equals(ext) || "docx".equals(ext)){
				c_type = "4";
			}else if("txt".equals(ext)){
				c_type = "5";
			}else if("ppt".equals(ext) || "pptx".equals(ext)){
				c_type = "6";
			}else if("pdf".equals(ext)){
				c_type = "7";
			}else if("zip".equals(ext) || "rar".equals(ext)){
				c_type = "8";
			}else if("mp3".equals(ext)){//语音文件 || "wmv".equals(ext) || "wav".equals(ext) || "m4a".equals(ext)
				c_type = "9";
			}else{
				c_type = "10";
			}
	        return c_type; //返回文本字符串 
	    }
	 
	 //对比时间
	 public static boolean CampareTime(String time1 ,String time2) {
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 Date a;
		 Date b;
		 try {
			a = sdf.parse(time1);
			b = sdf.parse(time2);
			 if(a.getTime()>b.getTime()){
				 return true;
			 }else{
				 return false;
			 }
		 } catch (ParseException e) {
			 e.printStackTrace();
			 return false;
		 }
	}

	// 获取当前时间
	public static String getCurrentTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sdf.format(new Date());
		return time;
	}

	public static String TimeAddHour(String time1 ,String hour) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date  date = sdf.parse(time1);
			Calendar ca=Calendar.getInstance();
			ca.setTime(date);
			int ys = new BigDecimal(hour).multiply(new BigDecimal("60")).intValue();
			ca.add(Calendar.MINUTE, ys);
			String time = sdf.format(ca.getTime());
			return  time;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	//对比时间
	 public static boolean CampareTime2(String time1 ,String time2) {
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		 Date a;
		 Date b;
		 try {
			a = sdf.parse(time1);
			b = sdf.parse(time2);
			 if(a.getTime()>b.getTime()){
				 return true;
			 }else{
				 return false;
			 }
		 } catch (ParseException e) {
			 e.printStackTrace();
			 return false;
		 }
	}
	 
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	 
	//判断日期格式
	public static boolean isValidDate(String str) {
	      boolean convertSuccess=true;
	      // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       try {
    	   // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
          format.setLenient(false);
          format.parse(str);
       } catch (ParseException e) {
           // e.printStackTrace();
    	   // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
           convertSuccess=false;
       } 
       return convertSuccess;
	}
	
	 public static boolean isInteger(String str) {  
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	        return pattern.matcher(str).matches();  
	  }
	 
	  public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,6})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match = pattern.matcher(str);
        return match.matches();
     }
	
	//判断是数字类型
	
	 public static void main(String[] args) {
		 System.out.println(isValidDate("2020-12-23 00:22:1000"));
		
	 }
	
}
