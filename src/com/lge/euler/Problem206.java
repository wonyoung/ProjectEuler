package com.lge.euler;

import org.junit.Test;

public class Problem206 {
	@Test
	public void run() {
		long num = 1000000000L;
		for(;num<1400000000L;num+=10)
			if(String.valueOf(num*num).matches("1.2.3.4.5.6.7.8.9.0")) {
				System.out.println(num);
				break;
			}
		System.out.println(Long.MAX_VALUE);
		System.out.println(num*num);
		System.out.println("1.2.3.4.5.6.7.8.9.0");
	}
}
