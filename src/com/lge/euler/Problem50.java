package com.lge.euler;

import java.math.BigInteger;
import java.util.*;

public class Problem50 {
	
	private List<Integer> primes;

	public Problem50(int max)
	{
		primes = createPrimes(max);
	}
	
	public List<Integer> createPrimes(int max)
	{
		List<Integer> primes = new ArrayList<Integer>(); 
		BigInteger n = new BigInteger("2");
				
		while(n.intValue() < max)
		{
			primes.add(n.intValue());
			n = n.nextProbablePrime();
		}
		
		return primes;
	}
	
	public int getLongestPrime()
	{
		int longestPrime = 0;
		int longestCount = 0;
		
		for(int prime: primes) {
			int count = 0;
			count = getPrimesCount(prime);
			if (count >= longestCount) {
				longestPrime = prime;
				longestCount = count;
			}
		}
		
		return longestPrime;
	}
	
	
	private int getPrimesCount(int targetPrime) {
		for(int startIndex=0;primes.get(startIndex)<targetPrime;startIndex++) {
			int sum=0;
			int count=0;

			for(int part=startIndex;sum < targetPrime;part++) {
				sum += primes.get(part);
				count++;
				if (sum == targetPrime)
					return count;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(new Problem50(1000).getLongestPrime());
		
	}
}
