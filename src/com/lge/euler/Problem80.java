package com.lge.euler;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;


class Fraction {
	public Fraction(BigInteger m, BigInteger d)
	{
		this.m = m;
		this.d = d;
	}
	
	public BigInteger m;
	public BigInteger d;
}

public class Problem80 {
	@Test
	public void testGetSquareRoot()
	{
		Fraction f = getSquareRoot(2,1);
		assertEquals(new BigInteger("3"), f.m);
		assertEquals(new BigInteger("2"), f.d);

		f = getSquareRoot(2,2);
		assertEquals(new BigInteger("17"), f.m);
		assertEquals(new BigInteger("12"), f.d);

		f = getSquareRoot(2,3);
		assertEquals(new BigInteger("577"), f.m);
		assertEquals(new BigInteger("408"), f.d);

		f = getSquareRoot(2,4);
		assertEquals(new BigInteger("665857"), f.m);
		assertEquals(new BigInteger("470832"), f.d);

		f = getSquareRoot(2,5);
		assertEquals(new BigInteger("886731088897"), f.m);
		assertEquals(new BigInteger("627013566048"), f.d);
	}

	@Test
	public void testGetSumOfHundredDigits()
	{
		assertEquals(475, getSumOfHundredDigits(getSquareRoot(2,10)));
	}
	
	@Test
	public void run()
	{
		int sum = 0;
		
		for(int i=1;i<=100;i++)
		{
			double s = Math.sqrt(i);
			int d;
			
			if (s - Math.round(s) == 0)
				continue;
			d = getSumOfHundredDigits(getSquareRoot(i, 11));
			System.out.println(i+ ":" + d);
			sum += d;
		}
		
		assertEquals(40886, sum);
	}
	
	private int getSumOfHundredDigits(Fraction f) {
		int digit;
		int sum;
		final int maxDigits = 100;
		
		sum = 0;
		for (int i=0;i<maxDigits;i++)
		{
			digit = f.m.divide(f.d).intValue();
			f.m = f.m.remainder(f.d);
			f.m = f.m.multiply(BigInteger.TEN);
			sum += digit;
		}
		
		
		return sum;
	}

	private Fraction getSquareRoot(int num, int precision) {
		Fraction squareRoot = new Fraction(new BigInteger("1"), new BigInteger("1"));
		
		for (int i=0;i<precision;i++)
		{
			BigInteger m = squareRoot.m;
			BigInteger d = squareRoot.d;
			
			squareRoot.m = m.multiply(m).add( d.multiply(d).multiply(new BigInteger(Integer.toString(num))) );
			squareRoot.d = m.multiply(d).multiply(new BigInteger("2")); 
		}
		
		return squareRoot;
	}

}
