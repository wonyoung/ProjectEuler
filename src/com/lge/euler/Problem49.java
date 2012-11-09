package com.lge.euler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Problem49 {
	public boolean hasSameDigit(Integer n1, Integer n2)
	{
		String n1Str, n2Str;
		
		n1Str = new String(n1.toString());
		n2Str = new String(n2.toString());
		
		if (n1Str.length() != n2Str.length())
			return false;
		
		for (int i=0;i < n1Str.length();i++)
		{
			int countn1, countn2;
			
			countn1 = 0;
			countn2 = 0;
			
			for(int j=0;j < n1Str.length(); j++)
			{
				if (n1Str.charAt(i) == n1Str.charAt(j))
					countn1++;
				if (n1Str.charAt(i) == n2Str.charAt(j))
					countn2++;
			}
			if (countn1 != countn2)
				return false;
		}

		return true;
	}
	
	@Test
	public void testHasSameDigit()
	{
		assertEquals(false, hasSameDigit(1000, 1100));
		assertEquals(true, hasSameDigit(1234, 3214));
		assertEquals(false, hasSameDigit(1346, 3214));
		assertEquals(true, hasSameDigit(2969, 9629));
	}
	
	public boolean isNotDuplicated(Integer num)
	{
		String str;
		
		str = new String(num.toString());
		
		for (int i=0;i <str.length();i++)
			if (str.lastIndexOf(str.charAt(i)) != i)
				return false;
		
		return true;
	
	}
	
	public boolean isPrime(Integer num)
	{
		for(int i=2;i<num;i++)
			if (num % i == 0)
				return false;
		
		return true;
	}
	
	@Test
	public void getResult49()
	{
		int num;
		
		for (num=1000;num<=9999;num++)
		{
			if (!isPrime(num))
				continue;
			
			for (int adder=1;num+adder*2<=9999;adder++)
			{
				if (!hasSameDigit(num, num+adder))
					continue;
				if (!isPrime(num+adder))
					continue;
				if (!isPrime(num+adder*2))
					continue;
				if (hasSameDigit(num+adder, num+adder*2))
				{
					System.out.printf("%d,%d,%d (%d)\n",num, num+adder, num+adder*2, adder);
//					assertEquals(1000, num);
//					assertEquals(2, adder);
				}
			}
		
		}
		assertEquals(1,1);
		return ;
	}
}
