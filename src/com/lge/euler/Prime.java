package com.lge.euler;

import java.util.Arrays;

class Prime {
	static boolean [] primeTable;
	static int max = 2;
	int nextPrime = 2;

	
	public Prime(int max) {
		if (primeTable == null) {
			primeTable = new boolean[max];
			primeTable[0] = true;
			primeTable[1] = true;			
			generatePrimeTableTo(max);
			Prime.max = primeTable.length;
		} else if (Prime.max < max) {
			primeTable = Arrays.copyOf(primeTable, max);
			generatePrimeTableTo(max);
			Prime.max = primeTable.length;
		}
	}
	
	public Prime() {
		this(100);
	}

	public int next() {
		int result = nextPrime;
		
		nextPrime++;
		while(nextPrime < Prime.max && primeTable[nextPrime])
			nextPrime++;
		return result;
	}

	public boolean hasNext() {
		return (nextPrime < Prime.max);
	}
	
	private void generatePrimeTableTo(int to) {
		int index = Prime.max;
		
		while(primeTable[index])
			index++;
		while(index<to) {
			for(int j=index*2;j<to;j+=index)
				primeTable[j] = true;
			index++;
		}
		
		System.out.println("Primes "+Prime.max+" to "+ to+" generated.");
	}

	public void setNext(int next) {
		this.nextPrime = next;
	}

	public static boolean isPrime(int number) {
		return (primeTable[number] == false);
	}
}