package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

public class Problem56 {

	BigInteger a;
	
	public int getSumOfDigits(BigInteger n)
	{
		int sum = 0;
		String str = new String(n.toString());
		
		for(int i=0; i< str.length(); i++)
			sum += str.charAt(i) - '0';
		
		return sum;
	}
	
	@Test
	public void testSumOfDigits()
	{
		assertEquals(32, getSumOfDigits(new BigInteger("9995")));
	}
	
	static public BigInteger pow(int a, int b)
	{
		BigInteger result = new BigInteger("1");
		BigInteger m = new BigInteger(Integer.toString(a));
		
		for(int i=0;i<b;i++)
			result = result.multiply(m);
		
		return result;
	}
	
	@Test
	public void testPow()
	{
		assertEquals(new BigInteger("1024"), pow(2,10));
	}
	
	@Test
	public void getResult56()
	{
		int maxSum = 0;
		for (int a=1;a<100;a++)
			for (int b=1;b<100;b++)
			{
				int sum = getSumOfDigits(pow(a,b));
				if (maxSum < sum)
					maxSum = sum;
			}
		
		assertEquals(972, maxSum);
	}
}
