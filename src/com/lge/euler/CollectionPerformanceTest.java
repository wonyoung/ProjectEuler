package com.lge.euler;

import java.math.BigInteger;
import java.util.*;

import org.junit.*;

public class CollectionPerformanceTest {

	private static final int NUM_OF_PRIMES = 100000000;
	List<BigInteger> list;
	Set<BigInteger> set;
	int [] array;
	
	@Ignore //Test
	public void makeArrayList() {
		list = createPrimesList(NUM_OF_PRIMES);

	}
	
	@Ignore // Test
	public void makeHashSet() {
		set = createPrimesSet(NUM_OF_PRIMES);
	}
	
	@Ignore //Test
	public void makeIntArray() {
		array = createPrimesArray(NUM_OF_PRIMES);
//		printPrimes(array);
	}
	
	@Test
	public void generatePrime() {
		Prime p = new Prime(NUM_OF_PRIMES);
		
//		while(p.hasNext())
//			System.out.println(p.next());
		
		
		
	}
	private void printPrimes(int[] primes) {
		for(int prime : primes)
			System.out.println(prime);
	}

	private boolean[] createPrimesTableWithEratosTenes(int max) {
		boolean [] numbers = new boolean[max];
		// mark primes to 0
		numbers[0] = true;
		numbers[1] = true;
		for(int i=2;i<max;i++) {
			for(int j=i*2;j<max;j+=i)
				numbers[j] = true;
		}
		
		return numbers;		
	}
	private boolean isPrimesWithEratosTenes(int number, boolean[] primeTable) {
		return (primeTable[number] == false);
	}

	private int[] createPrimesArray(int max) {
		boolean [] numbers = createPrimesTableWithEratosTenes(max);
		int [] primes = new int[max]; 
		
		int index = 0;
		for(int i=0;i<max;i++) 
			if (isPrimesWithEratosTenes(i, numbers))
				primes[index++] = i;
		System.out.println("Primes under "+max+" generated.");
		return Arrays.copyOf(primes, index);
	}

	public static List<BigInteger> createPrimesList(int max)
	{
		List<BigInteger> primes = new ArrayList<BigInteger>(); 
		BigInteger n = new BigInteger("2");
		BigInteger maxValue = new BigInteger(String.valueOf(max));

		while(n.compareTo(maxValue) < 0)
		{
			primes.add(n);
			n = n.nextProbablePrime();
		}
		
		System.out.println("Primes under "+max+" generated.");
		return primes;
	}		
	
	public static Set<BigInteger> createPrimesSet(int max)
	{
		Set<BigInteger> primes = new HashSet<BigInteger>(); 
		BigInteger n = new BigInteger("2");
		BigInteger maxValue = new BigInteger(String.valueOf(max));

		while(n.compareTo(maxValue) < 0)
		{
			primes.add(n);
			n = n.nextProbablePrime();
		}
		
		System.out.println("Primes under "+max+" generated.");
		return primes;
	}		
	
}