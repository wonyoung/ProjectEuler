package com.lge.euler;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Problem44 {

	public int getPentagonal(int n)
	{
		return n*(3*n-1)/2;
	}
	
	public boolean isPentagonal(int num)
	{
		int n;
		
		for(n=1;n*(3*n-1)/2<num;n++);
		
		if (n*(3*n-1)/2 == num)
			return true;
		
		return false;
	}

	@Test
	public void testGetPentagonal()
	{
		assertEquals(1, getPentagonal(1));
		assertEquals(117, getPentagonal(9));
	}
	
	@Test
	public void testIsPentagonal()
	{
		assertEquals(true, isPentagonal(117));
		assertEquals(false, isPentagonal(118));
	}
	
	@Test
	public void testProblem44()
	{
		
		for(int p=3;p<10000;p++)
		{
			for(int k=p-1;k>1;k--)
			{
				if (!isPentagonal(getPentagonal(p) - getPentagonal(k)))
					continue;
				
				if (isPentagonal(2*getPentagonal(k) - getPentagonal(p)))
				{
					assertEquals(5482660, 2*getPentagonal(k) - getPentagonal(p));
					return;
				}
			}
		}
		
		
	}
	
}
