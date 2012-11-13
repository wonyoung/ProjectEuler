package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

public class Problem63 {

	public int getDigits(BigInteger num)
	{
		return num.toString().length();
	}
	
	@Test
	public void testDigits()
	{
		assertEquals(5,getDigits(new BigInteger("16807")));
		assertEquals(9,getDigits(new BigInteger("134217728")));
	}
	
	@Test
	public void testGeneric()
	{
		int count = 0;
		
		for (int a=1;a<10;a++)
			for (int b=1; ;b++)
			{
				BigInteger num = Problem56.pow(a, b);
				
				if (getDigits(num) == b)
				{
					System.out.println(num+" "+a+"^"+b);
					count++;
				}
				if (getDigits(num) < b)
					break;
			}
		
		assertEquals(49,count);

	}
}
