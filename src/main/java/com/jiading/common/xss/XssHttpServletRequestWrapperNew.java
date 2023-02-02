package com.jiading.common.xss;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@SuppressWarnings("all")
public class XssHttpServletRequestWrapperNew extends HttpServletRequestWrapper {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public XssHttpServletRequestWrapperNew(HttpServletRequest request) {
        super(request);
        String contentType = request.getContentType();

        if (contentType != null && contentType.contains("multipart/form-data")) {
            String rurl = request.getRequestURI();
            if (rurl.equals("/ssqy2/manageC/upload2.do")) {

            } else if (rurl.equals("/ssqy2/manageC/upload3.do")) {

            } else if (rurl.equals("/ssqy2/manageC/upload4.do")) {

            } else if (rurl.equals("/ssqy2/kindeditor-4.1.10/jsp/upload_json.jsp")) {

            } else {
//                MultipartResolver resolver = new
//                        CommonsMultipartResolver(request.getSession().getServletContext());
//                MultipartHttpServletRequest multipartRequest =
//                        resolver.resolveMultipart(request); // 将转化后的 request 放入过滤链中
//                request = multipartRequest;
            }
        }

        this.request = request;
    }
    @Override
    public ServletInputStream getInputStream() throws IOException {
        //非json类型，直接返回
        if(!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(super.getHeader(HttpHeaders.CONTENT_TYPE))){
            return super.getInputStream();
        }

        //为空，直接返回
        String json = IOUtils.toString(super.getInputStream(), "utf-8");
        if (StringUtils.isBlank(json)) {
            return super.getInputStream();
        }

        //xss过滤
        json = stripXSSAndSql(json);
        final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes("utf-8"));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bis.read();
            }
        };
    }
    /**
     * 重写getParameter方法
     */
    @Override
    public String getParameter(String name) {
        System.out.println("1");
        String value = super.getParameter(name);
        if ("inform".equals(name)) {
            return value;
        }
        if (value == null) {
            return null;
        }
        value = format(value);
        return value;
    }

    /**
     * 重写getParameterMap
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map1 = super.getParameterMap();
        Map<String, String[]> escapseMap = new HashMap<String, String[]>();
        Set<String> keys = map1.keySet();
        for (String key : keys) {
            String[] valArr = map1.get(key);
            if (valArr != null && valArr.length > 0) {
                String[] escapseValArr = new String[valArr.length];
                for (int i = 0; i < valArr.length; i++) {
                    String escapseVal = stripXSSAndSql(valArr[i]);
                    escapseValArr[i] = escapseVal;
                }
                escapseMap.put(key, escapseValArr);
            }
        }
        return escapseMap;

//        return super.getParameterMap();
    }

    /**
     * 重写getParameterValues
     */
    @Override
    public String[] getParameterValues(String name) {
        System.out.println("2");
        String[] values = super.getParameterValues(name);
//        System.out.println(values[0]);
        int count = 0;
        if (values == null || values.length == 0) {
            return values;

        } else {
            count = values.length;
            String[] encodedValues = new String[count];

            System.out.println(count);
            if (count <= 1) {
                return values;
            } else {
                for (int i = 0; i < count; i++) {
                    if (encodedValues[i] != null && encodedValues[i] != "") {
                        encodedValues[i] = format2(values[i]);
                        System.out.println(encodedValues[i]);
                    }

                }
                return encodedValues;
            }


        }
		
		/*for (int i = 0; i < count; i++) {
			encodedValues[i] = values[i];
		}*/
		/*if (!"inform".equals(name)) {
		for (int i = 0; i < count; i++) {
			encodedValues[i] = format(values[i]);
		}
		}else{
			for (int i = 0; i < count; i++) {
				encodedValues[i] = values[i];
			}
		}*/

    }

    /**
     * 重写getHeader
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    public String filter(String message) {
        if (message == null)
            return (null);
        message = format(message);
        return message;
    }

    /**
     * @param name 要替换的字符
     * @desc 统一处理特殊字符的方法，替换掉sql和js的特殊字符
     */
    private String format(String name) {
        return xssEncode(name);
    }

    /**
     * @param name 要替换的字符
     * @desc 统一处理特殊字符的方法，替换掉sql和js的特殊字符
     */
    private String format2(String name) {
        return xssEncode2(name);
    }

    /**
     * 将容易引起xss & sql漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    private static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        } else {
            s = stripXSSAndSql(s);
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append("＞");// 转义大于号
                    break;
                case '<':
                    sb.append("＜");// 转义小于号
                    break;


//            case '\'':  
//                sb.append("＇");// 转义单引号  
//                break;  
//            case '\"':  
//                sb.append("＂");// 转义双引号  
//                break;  
//            case '&':  
//                sb.append("＆");// 转义&  
//                break;  
//            case '#':  
//                sb.append("＃");// 转义#  
//                break;  
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 将容易引起xss & sql漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    private static String xssEncode2(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        } else {
            s = stripXSSAndSql2(s);
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {


//            case '\'':  
//                sb.append("＇");// 转义单引号  
//                break;  
//            case '\"':  
//                sb.append("＂");// 转义双引号  
//                break;  
//            case '&':  
//                sb.append("＆");// 转义&  
//                break;  
//            case '#':  
//                sb.append("＃");// 转义#  
//                break;  
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 防止xss跨脚本攻击（替换，根据实际情况调整,不过滤富文本）
     */
    public static String stripXSSAndSql(String value) {
        if (value != null) {
            Pattern scriptPattern = Pattern.compile(
                    "<[\r\n| | ]*script[\r\n| | ]*>(.*?)</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid anything in a src="http://www.yihaomen.com/article/java/..." type of
            // e-xpression
//            scriptPattern = Pattern.compile("src[\r\n| | ]*=[\r\n| | ]*[\\\"|\\\'](.*?)[\\\"|\\\']",
//                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
//            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<[\r\n| | ]*script(.*?)>",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid e-xpression(...) expressions
            scriptPattern = Pattern.compile("e-xpression\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid οnlοad= expressions
            scriptPattern = Pattern.compile("onload(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("onclick(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("onerror(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }


    /**
     * 防止xss跨脚本攻击（替换，根据实际情况调整）
     */
    public static String stripXSSAndSql2(String value) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and
            // uncomment the following line to
            // avoid encoded attacks.
//             value = ESAPI.encoder().canonicalize(value);  
            // Avoid null characters
            /** value = value.replaceAll("", ""); ***/
            // Avoid anything between script tags
            //Pattern scriptPattern = Pattern.compile(
            //		"<[\r\n| | ]*script[\r\n| | ]*>(.*?)</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
            //value = scriptPattern.matcher(value).replaceAll("");
            // Avoid anything in a src="http://www.yihaomen.com/article/java/..." type of
            // e-xpression
			/*Pattern scriptPattern = Pattern.compile("src[\r\n| | ]*=[\r\n| | ]*[\\\"|\\\'](.*?)[\\\"|\\\']",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			*/// Remove any lonesome </script> tag
            Pattern scriptPattern = Pattern.compile("</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<[\r\n| | ]*script(.*?)>",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid eval(...) expressions
			/*scriptPattern = Pattern.compile("eval\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid e-xpression(...) expressions
			scriptPattern = Pattern.compile("e-xpression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");*/
            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid οnlοad= expressions
            scriptPattern = Pattern.compile("onload(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("onclick(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("onerror(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }

}