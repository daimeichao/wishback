package com.jiading.common.util;

import java.util.Random;

public class RandomNumber {

public static String Nuber() {
		
		Random r =new Random();
		String s ="";
		for (int i = 0; i < 4; i++) {
			s+=r.nextInt(10)+1;
		}
		return System.currentTimeMillis()+s;
	}

public static void main(String[] args) {
	System.out.println(Nuber());
}
}
