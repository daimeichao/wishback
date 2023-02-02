package com.jiading.common.util;

import java.util.HashMap;
import java.util.Map;

public class JIEPIN {
	
	public static void FunJiePin(String path,String sppath,String tppath) {
		try {
			
		   String ml = path+"/dos/ffmpeg.exe -i "+sppath+" -y -f image2 -ss 0.1 -t 0.001 -s 1024x768 "+tppath; //''  ''
	       String[] cmd = new String[] { "cmd.exe", "/C", ml};  // 命令
		   Process process = Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		String path = "F:/project2019/xtdshop/WebRoot";//项目的物理跟路径
		String sppath = "F:/project2019/xtdshop/WebRoot/upload/video/1.mp4";
		String tppath = "F:/project2019/xtdshop/WebRoot/upload/video/1.jpg";
		FunJiePin(path, sppath, tppath);
	}


}
