package com.jiading.common.util;

public class FenYe {

	public static int pageindex(Object cur,Object pagesize) {//第几个开始显示
		
		cur = (cur==null) ? 1 : cur;
		pagesize = (pagesize==null) ? 10 : pagesize;
		cur = (Integer.parseInt(cur+"") < 1 ? 1 : cur);
		return (Integer.parseInt(cur+"") - 1) * Integer.parseInt(pagesize+"");
	}
	
	public static int pagesize(Object pagesize) {//显示几个
		pagesize = (pagesize==null) ? 10 : pagesize;
		return Integer.parseInt(pagesize+"");
	};
	
	public static int curpage(Object cur) {//当前页
		cur = (cur==null) ? 1 : cur;
		return Integer.parseInt(cur+"");
	}
	
	public static int pagecount(int count,int pagesize) {//页数
		int pagecount=0;
		if (count % pagesize == 0) {
			pagecount = count / pagesize;
		} else {
			pagecount = count / pagesize + 1;
		}
		return pagecount;
	}
}
