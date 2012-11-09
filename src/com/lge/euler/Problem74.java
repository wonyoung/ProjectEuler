package com.lge.euler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Problem74 {
	
	private int fact(int num)
	{
		final int f[] = new int [] {
			1, 1, 2, 3*2, 4*3*2, 5*4*3*2, 6*5*4*3*2, 7*6*5*4*3*2,
			8*7*6*5*4*3*2, 9*8*7*6*5*4*3*2 };
		
		return f[num % 10];
	}

	@Test
	public void testFactorial()
	{
		assertEquals(1,fact(1));
	}
	
	private Map <Integer, Integer> m;
	
	public Problem74()
	{
		m = new HashMap<Integer, Integer>();
	}
	
	@Test
	public void testNextSumOfFactorialDigits()
	{
		assertEquals(145, nextNumber(145));
		assertEquals(169, nextNumber(1454));
		assertEquals(871, nextNumber(45361));
	}

	private int nextNumber(int num) {
		Integer next = m.get(num);
		
		if (next != null)
			return next;

		next = getSumOfFactorialDigits(num);
		m.put(num, next);
		
		return next;
	}

	private Integer getSumOfFactorialDigits(int num) {
		int sum = 0;
		
		do {
			sum += fact(num % 10);
			num /= 10;
		} while (num != 0);
		
		return sum;
	}
	
	@Test
	public void testCountChains()
	{
		assertEquals(5, getCountChains(69));
		assertEquals(2, getCountChains(872));
	}

	private int getCountChains(int start) {
		int count = 0;
		List <Integer> terms = new ArrayList<Integer>();
		Integer next;
		
		terms.add(start);
		next = nextNumber(start);
		count++;
		while (terms.contains(next) == false)
		{
			terms.add(next);
			next = nextNumber(next);
			count++;
		}
		
		
		return count;
	}
	
	@Test
	public void run()
	{
		int count = 0;
		
		for (int i=1;i<1000000;i++)
		{
			if (getCountChains(i) == 60)
				count++;
		}
		
		assertEquals(402, count);
	}
	
}
