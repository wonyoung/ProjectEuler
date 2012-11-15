package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.*;

import org.junit.*;

public class Problem60 {

	private static List<Integer> primes = createPrimes(100000000);
	private static SparseMatrix matrix = generateConcatenatedPrimeMatrix(10000);

	@Test
	public void testSparseMatrixShouldBeEmpty() {
		SparseMatrix m = new SparseMatrix();
		assertEquals(true, m.isEmpty());
	}


	@Test
	public void testSparseMatrixShouldNotBeEmpty() {
		SparseMatrix m = new SparseMatrix();
		
		m.add(1,2);
		m.add(1,4);
		assertEquals(false, m.isEmpty());
	}
	
	@Ignore
	public void printGeneratedPrimes() {
		for(Integer i : primes) {
			Set<Integer> l = matrix.get(i);
			
			if (l != null)
				for(Integer j : l)
					System.out.println("" + i +"  and " + j);
		}
	}
	
	@Test
	public void run() {
		
		List<Integer> resultPrimes;
		
		for(Integer prime : primes) {
			resultPrimes = getNConcatenatablePrimes(prime, 5);
			if (resultPrimes != null) {
				for (Integer i : resultPrimes)
					System.out.println("prime " + i);
				break;
			}
		}
	}

	@Test
	public void testSomePrimesWithCalc() {
		assertEquals(true, isConcatenatedPrime(673, 109));
		assertEquals(true, isConcatenatedPrime(673, 7));
		assertEquals(true, isConcatenatedPrime(673, 3));

		assertEquals(true, isConcatenatedPrime(109, 673));
		assertEquals(true, isConcatenatedPrime(109, 7));
		assertEquals(true, isConcatenatedPrime(109, 3));

		assertEquals(true, isConcatenatedPrime(7, 109));
		assertEquals(true, isConcatenatedPrime(7, 673));
		assertEquals(true, isConcatenatedPrime(7, 3));

		assertEquals(true, isConcatenatedPrime(3, 7));
		assertEquals(true, isConcatenatedPrime(3, 109));
		assertEquals(true, isConcatenatedPrime(3, 673));
	}

	@Test
	public void testSomePrimesWithMatrix() {
		assertEquals(true, matrix.contains(673, 109));
		assertEquals(true, matrix.contains(673, 7));
		assertEquals(true, matrix.contains(673, 3));

		assertEquals(true, matrix.contains(109, 673));
		assertEquals(true, matrix.contains(109, 7));
		assertEquals(true, matrix.contains(109, 3));

		assertEquals(true, matrix.contains(7, 109));
		assertEquals(true, matrix.contains(7, 673));
		assertEquals(true, matrix.contains(7, 3));

		assertEquals(true, matrix.contains(3, 7));
		assertEquals(true, matrix.contains(3, 109));
		assertEquals(true, matrix.contains(3, 673));
	}
	
	@Test
	public void test673Primes() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(109);
		list.add(7);
		list.add(3);
		
		assertEquals(true, allPrimesInListAreConcatenatable(673, list));
		
	}

	@Test
	public void test673PrimesWithMatrix() {
		
		matrix.print(673);
		matrix.print(3);
		matrix.print(7);
		matrix.print(109);
		List<Integer> list = getNConcatenatablePrimes(673, 4);
		
		if(list != null)
			for (Integer i : list) 
				System.out.println("t prime "+i);

	}
		
	

	private List<Integer> getNConcatenatablePrimes(int startPrime, int count) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(startPrime);
		Set<Integer> primes = matrix.get(startPrime);
		
		if(primes == null)
			return null;
		if(primes.size() < count-1)
			return null;
		
		return addConcatenatablePrimes(list, primes, count);
	}

	private List<Integer> addConcatenatablePrimes(List<Integer> list, Set<Integer> primes, int count) {
		for(Integer prime : primes) {
			if(list.contains(prime))
				continue;
			if(allPrimesInListAreConcatenatable(prime, list)) {
				List<Integer> result = new ArrayList<Integer>(list);
				result.add(prime);
				if (result.size() == count)
					return result;
				
				result = addConcatenatablePrimes(result, primes, count);
				if (result != null)
					return result;
			}
		}

		return null;
	}
	
	

	private boolean allPrimesInListAreConcatenatable(Integer prime, List<Integer> list) {
		boolean concatenatable = true;
		for(Integer prime2 : list) {
			if(matrix.contains(prime, prime2) == false) {
				concatenatable = false;
				break;
			}
		}
		return concatenatable;
	}


	
	private static boolean isConcatenatedPrime(int primeA, int primeB) {
		String strA = Integer.toString(primeA);
		String strB = Integer.toString(primeB);
		int concatenated1 = Integer.valueOf(strA + strB);
		int concatenated2 = Integer.valueOf(strB + strA);
		
		if (primes.contains(concatenated1)
				&& primes.contains(concatenated2))
			return true;
			
		return false;
	}

	private static SparseMatrix generateConcatenatedPrimeMatrix(int max) {
		SparseMatrix m = new SparseMatrix();
		
		for(int i=0;primes.get(i)<max;i++) {
			for(int j=i;primes.get(j)<max;j++) {
				if (i==j)
					continue;
				
				int primeA = primes.get(i);
				int primeB = primes.get(j);
				if (m.contains(primeA, primeB))
					continue;
				if (isConcatenatedPrime(primeA, primeB))
					m.add(primeA, primeB);
			}
		}
		System.out.println("Matrix "+max+" generated.");
		return m;
	}

	public static List<Integer> createPrimes(int max)
	{
		List<Integer> primes = new ArrayList<Integer>(); 
		BigInteger n = new BigInteger("2");

		while(n.intValue() < max)
		{
			primes.add(n.intValue());
			n = n.nextProbablePrime();
		}
		
		System.out.println("Primes under "+max+" generated.");
		return primes;
	}	
}

class SparseMatrix {
	private Map<Integer, Set<Integer>> map;
	
	public SparseMatrix() {
		map = new HashMap<Integer, Set<Integer>>();
	}
	
	public boolean contains(Integer a, Integer b) {
		Set<Integer> listA = map.get(a);
		
		if (listA == null)
			return false;
		return listA.contains(b);
	}
	
	public Set<Integer> get(int i) {
		return map.get(i);
	}
	
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	public void add(int a, int b) {
		Set<Integer> listA = getList(a);
		Set<Integer> listB = getList(b);
		
		listA.add(b);
		listB.add(a);
	}
	
	private Set<Integer> getList(int key) {
		Set<Integer> list;
		if (map.containsKey(key)) {
			list = map.get(key);
		} else {
			list = new HashSet<Integer>();
			map.put(key, list);
		}
		return list;
	}
	
	public void print(int prime) {
		Set<Integer> list = get(prime);
		if (list == null)
			return;
		
		for(Integer i : list) 
			System.out.println(""+ prime + " and " + i );		
	}
}