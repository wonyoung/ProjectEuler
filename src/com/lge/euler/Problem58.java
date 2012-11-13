package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

class Spirall {
	Integer primes;
	Integer nums;
	Integer last;
}

public class Problem58 {

	Map <Integer, Spirall> spiralDiagonals;
	int primes[];
	
	public Problem58()
	{
		spiralDiagonals = new HashMap<Integer, Spirall>();
		primes = createPrimes(40000000);
	}

	private int[] createPrimes(int max) {
		int primes[] = new int [max+1];
		
		primes[0]=primes[1]=1;
		
		for(int i=2;i<max;i++)
			if (primes[i] == 0)
				for (int j=i*2;j<=max; j+=i)
					primes[j] = 1;
		
		return primes;
	}

	public Spirall createDiagonalNumbers(int length)
	{
		Spirall spirall = new Spirall();
		
		
		if (spiralDiagonals.containsKey(length))
			return spiralDiagonals.get(length);
		
		if (length == 1)
		{
			spirall.primes = 0;
			spirall.nums = 1;
			spirall.last = 1;
		}
		else
		{
			spirall = createDiagonalNumbers(length-2);
			for (int i=0;i<4;i++)
			{
				spirall.last += length-1;
				if (isPrime(spirall.last))
					spirall.primes++;
				spirall.nums++;			
			}
		}
		
		spiralDiagonals.put(length, spirall);
		return spirall;
	}
	
	public double getPrimeRatio(Spirall spirall)
	{
			
		//System.out.println(primes.doubleValue() +"/ "+ list.size());
		return spirall.primes.doubleValue() / spirall.nums.doubleValue();
	}
	
	private boolean isPrime(Integer num) 
	{
		BigInteger n = new BigInteger(num.toString());
		
		return n.isProbablePrime(5);
		
//		if (num < primes.length)
//		{
//			if (primes[num] == 0)
//				return true;
//			return false;
//		}
//				
//		for (int i=2;i<num;i++)
//		{
//			if (i < primes.length)
//				if (primes[i] == 1)
//					continue;
//			if (num % i == 0)
//				return false;
//		}
//		
//		return true;
	}

	@Test
	public void testDiagonalNumbers()
	{
		int n = 26239;
		double d;
	
		createDiagonalNumbers(3001);
		createDiagonalNumbers(9001);
		createDiagonalNumbers(15001);
		createDiagonalNumbers(21001);
		
		while(true)
		{
			d = getPrimeRatio(createDiagonalNumbers(n));
			if (d < 0.1)
				break;
			n += 2;
			if (n % 100 == 1)
				System.out.println(n + "(" + d + ")");
		}
		assertEquals(26241, n);		
	}
}
