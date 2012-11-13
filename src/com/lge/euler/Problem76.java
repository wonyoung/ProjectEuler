package com.lge.euler;

import static org.junit.Assert.*;
import org.junit.Test;

public class Problem76 {

	@Test
	public void testGetSetOfSums()
	{
		assertEquals(1,getSums(2));
		assertEquals(2,getSums(3));
		assertEquals(4,getSums(4));
		assertEquals(6,getSums(5));
		assertEquals(10,getSums(6));


		assertEquals(190569291,getSums(100));

	}
	
	@Test
	public void testGetSumsWithSeed()
	{
		assertEquals(1,getSumsWithMaxSeed(2,1));

		assertEquals(1,getSumsWithMaxSeed(3,1));
		assertEquals(1,getSumsWithMaxSeed(3,2));

		assertEquals(1,getSumsWithMaxSeed(4,1));
		assertEquals(2,getSumsWithMaxSeed(4,2));
		assertEquals(1,getSumsWithMaxSeed(4,3));
		
		assertEquals(1,getSumsWithMaxSeed(5,1));
		assertEquals(2,getSumsWithMaxSeed(5,2));
		assertEquals(2,getSumsWithMaxSeed(5,3));
		assertEquals(1,getSumsWithMaxSeed(5,4));

		assertEquals(1,getSumsWithMaxSeed(6,1));
		assertEquals(3,getSumsWithMaxSeed(6,2));
		assertEquals(3,getSumsWithMaxSeed(6,3));
		assertEquals(2,getSumsWithMaxSeed(6,4));
		assertEquals(1,getSumsWithMaxSeed(6,5));
		
	}

	private int getSums(int num) {
		return getSums(num, num-1);
	}
	
	private int getSums(int num, int seed) {
		int sum = 0;
//		System.out.println("getsum"+num);
		for (int i=1;i<=seed;i++)
			sum += getSumsWithMaxSeed(num, i);
			
		return sum;
	}	
	private int getSumsWithOne(int num) {
//		System.out.println("getsumOne"+num);
		return getSums(num)+1;
	}

	// 1
	
	// 2
	// 1,1
	
	// 3
	// 2,1
	// 1,1,1
	
	// 4
	// 3,1
	// 2,2
	// 2,1,1
	// 1,1,1,1
	
	// 5
	// 4,1				1
	// 3,2				2
	// 3,1,1		
	// 2,2,1			2
	// 2,1,1,1
	// 1,1,1,1,1		1
	
	// 6
	// 5,1				1
	// 4,2				2
	// 4,1,1
	// 3,3				3
	// 3,2,1
	// 3,1,1,1
	// 2,2,2			3  6,2 4,2 2,2(1)
	// 2,2,1,1             6,2 4,2 2,2 2,1(1)
	// 2,1,1,1,1		   6,2 4,1 3,1 2,1 1,1(1)
	// 1,1,1,1,1,1		1   6,1 5,1 4,1, 3,1 2,1 1,1(1)

	// num seed
	// 1. num - seed < seed
	//  getsum(num-seed)
	// 2. num - seed == seed			6,3   4,2
	//  getsum(num-seed)+자기자신
	// 3. num - seed > seed
	//  getsum(num-seed)               6,2 -> 4
	private int getSumsWithMaxSeed(int num, int seed) {
//		System.out.println("seed "+num+","+seed);

		if (seed == 1)
			return 1;
		
		if (num <= 2*seed)
		{
			return getSumsWithOne(num-seed);
		}
		return getSums(num-seed, seed);
	}
}
