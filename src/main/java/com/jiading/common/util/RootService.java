package com.jiading.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings("all")
public class RootService implements ApplicationContextAware {
	protected static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext = arg0;
	}

	public Object getService(String ServiceName) {
		return this.applicationContext.getBean(ServiceName);
	}
	
	public void setErrInfo(Exception e){
		String err = getErrorInfoFromException(e);
		
	}

	/**
	 * 将异常堆栈信息转换为String
	 * 
	 * @param e
	 * @return
	 */
	public String getErrorInfoFromException(Exception e) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return sw.toString() + "\r\n";
		} catch (Exception e2) {
			return "bad getErrorInfoFromException";
		}
	}
}
