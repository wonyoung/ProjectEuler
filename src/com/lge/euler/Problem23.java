package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class Problem23 {

	private ArrayList <Integer> abundants;
	
	public int sumOfProperDivisors(int num) {
		int sum;
		int i,j;
		
		sum = 1;
		
		for (i=2; i<num; i++)
			if (num % i == 0) {
				j = num/i;
				if (i > j)
					break;
				sum += i;
				if (i != j)
					sum += j;
			}
		
		return sum;
	}
	
	@Test
	public void testSumOfProperDivisors() {
		assertEquals(28, sumOfProperDivisors(28));
		assertEquals(16, sumOfProperDivisors(12));
	}
	
	public int createAbundants(int num) {
		int i;
		
		abundants = new ArrayList<Integer>();
		
		for (i=1; i<=num; i++)
			if (sumOfProperDivisors(i) > i)
				abundants.add(i);
		

		Collections.sort(abundants);
		
		return abundants.size();
	}
	
	@Test
	public void testSumOfTwoAbundantNumbers() {
		int i;
		int sum = 0;
		
		createAbundants(28124);
		
		assertEquals(false, checkSumOfTwoAbundantNumbers(28124));
		assertEquals(true, checkSumOfTwoAbundantNumbers(24));
		
		for (i=1; i< 28124; i++)
		{
			if (!checkSumOfTwoAbundantNumbers(i))
				sum += i;
		}
		
		assertEquals(4179871, sum);
	}

	private boolean checkSumOfTwoAbundantNumbers(Integer num) {
		int i;
		
		if (num > 28123)
			return false;
		
		for (i=0; i< abundants.size(); i++)
		{
			if (num <= abundants.get(i))
				return false;
			
			if (Collections.binarySearch(abundants, num - abundants.get(i)) >= 0)
				return true;
		
		}
		
		return false;
	}
}
