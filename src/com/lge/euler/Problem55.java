package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

public class Problem55 {

	public BigInteger addReverse(BigInteger num)
	{
		return num.add(new BigInteger((new StringBuffer(num.toString()).reverse().toString()))); 
	}
	
	@Test
	public void testAddReverse()
	{
		assertEquals(new BigInteger("1292"), addReverse(new BigInteger("349")));
		assertEquals(new BigInteger("4213"), addReverse(new BigInteger("1292")));
	}
	
	public boolean isPalindromic(BigInteger num)
	{
		String str = num.toString();
		
		return str.equals((new StringBuffer(str)).reverse().toString());
	}
	
	@Test
	public void testPalindromic()
	{
		assertEquals(true, isPalindromic(new BigInteger("121")));
		assertEquals(false, isPalindromic(new BigInteger("2234")));
		assertEquals(true, isPalindromic(new BigInteger("12344321")));
	}
	
	public boolean isLychrel(BigInteger num)
	{
		for (int i=0;i<50;i++)
		{
			num = addReverse(num);
			
			if (isPalindromic(num))
				return false;
		}
		
		return true;
	}
	
	@Test
	public void testLychrel()
	{
		assertEquals(false, isLychrel(new BigInteger("349")));
		assertEquals(true, isLychrel(new BigInteger("4994")));
	}
	
	@Test
	public void testGeneric()
	{
		int count = 0;
		
		for (Integer i=1;i<10000;i++)
		{
			if (isLychrel(new BigInteger(i.toString())))
				count++;
		}
		
		assertEquals(249, count);
		
	}
}
