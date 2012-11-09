package com.lge.euler;

import static org.junit.Assert.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

public class Problem62 {
	// 숫자의 3승을 generate
	// 각 숫자들과 비교
	// count 3
	@Test
	public void testGenerateTriples()
	{
		assertArrayEquals(new BigInteger[] {new BigInteger("1"), 
				new BigInteger("8"), 
				new BigInteger("27")}, generateTriples(3));
	}
	
	public BigInteger [] generateTriples(int max)
	{
		List<BigInteger> triples = new ArrayList<BigInteger>();
		BigInteger triple;
		
		for (int i=1;i<=max;i++)
		{
			BigInteger num = new BigInteger(Integer.toString(i));
			triple = new BigInteger(Integer.toString(i));
			triple = triple.multiply(num);
			triple = triple.multiply(num);
			
			triples.add(triple);
		}
		
		return triples.toArray(new BigInteger[0]);
	}
	
	@Test 
	public void testSameNumbers()
	{
		assertEquals(true, hasSameNumbers(new BigInteger("12340404"), new BigInteger("44400312")));
		assertEquals(true, hasSameNumbers(new BigInteger("41063625"), new BigInteger("56623104")));
		assertEquals(true, hasSameNumbers(new BigInteger("41063625"), new BigInteger("66430125")));
		assertEquals(true, hasSameNumbers(new BigInteger("56623104"), new BigInteger("66430125")));
	}

	@Test
	public void testFindIt()
	{
		assertEquals(new BigInteger("41063625"), getSameNumbersN(3, 1000));
//		assertEquals(new BigInteger("1006012008"), getSameNumbersN(4, 3000));
		assertEquals(new BigInteger("127035954683"), getSameNumbersN(5, 10000));
	}

	public BigInteger getSameNumbersN(int numbersCount, int max) {
		BigInteger triples[] = generateTriples(max);
		int count;
		
		for (int i=0; i< triples.length;i++)
		{
			count = 1;
			for (int j=i+1; j<triples.length;j++)
			{
				if (hasSameNumbers(triples[i], triples[j]))
				{
					System.out.println(triples[i]+", "+triples[j]);
					count++;
				}
				if (count == numbersCount)
					return triples[i];
			}
		}
		return null;
	}
	
	
	private boolean hasSameNumbers(BigInteger a, BigInteger b) {
		
		char charA[] = a.toString().toCharArray();
		char charB[] = b.toString().toCharArray();
		
		Arrays.sort(charA);
		Arrays.sort(charB);

		return Arrays.equals(charA, charB);
	}
}


class Problem62old {

	// 숫자의 3승을 구해서
	// 그것의 조합을 돌려(큰수로)
	// cbrt로 정수가 되는 수를 return한다.
	// 그것의 개수가 4개(5개)인 수가 result
	@Test
	public void testGetCubes()
	{
		assertArrayEquals(new BigInteger[] {new BigInteger("41063625"), 
				new BigInteger("56623104"), 
				new BigInteger("66430125")}, getCubes(345));
	}

	
	@Test
	public void testProblem62()
	{
		int i = 1291;
		BigInteger cubes[];
		
		while(true)
		{
			cubes = getCubes(i);
			
			if (cubes.length == 5)
				break;
			i++;
		}
		
		assertEquals(new BigInteger("41063625"), cubes[0]);
	}
	
	private BigInteger[] getCubes(int num) {
		List<BigInteger> cubes = new ArrayList<BigInteger>();
		BigInteger cube = new BigInteger(Integer.toString(num*num*num));
		Permutation perm = new Permutation(cube);

		while(perm.hasNext())
		{
			BigInteger p = perm.next();
			double d = Math.cbrt(p.doubleValue());
			
			if (d - Math.round(d) == 0)
			{
				cubes.add(p);
			}				
		}
		Collections.sort(cubes);
		
		System.out.println(num+cubes.toString());
		
		return cubes.toArray(new BigInteger[0]);
	}
}

class Permutation
{
	private String start;
	private PermutationGenerator pg;
	private List<BigInteger> list;
	private int current;
	
	public Permutation(BigInteger n)
	{
		start = n.toString();
		pg = new PermutationGenerator(start.length());
		generatePermutation();
	}

	private void generatePermutation() {
		
		list = new ArrayList<BigInteger>();
		while(pg.hasMore())
		{
			int[] indices;		
			StringBuffer permutation = new StringBuffer();
			BigInteger num;

			indices = pg.getNext();
			
			if (start.charAt(indices[0]) == '0')
				continue;
			
			for (int i = 0; i < indices.length; i++) {
				permutation.append (start.charAt(indices[i]));
			}		
			
			num = new BigInteger(permutation.toString());

			list.add(num);
		}
		
		HashSet<BigInteger> hs = new HashSet<BigInteger>();
		
		hs.addAll(list);
		list.clear();
		list.addAll(hs);
		
		Collections.sort(list);
		
		current = 0;
	}

	public BigInteger next() {
		return list.get(current++);
	}

	public boolean hasNext() {
		if (current == list.size() - 1)
			return false;
		
		return true;
	}
}

class PermutationGenerator {

	private int[] a;
	private BigInteger numLeft;
	private BigInteger total;

	//-----------------------------------------------------------
	// Constructor. WARNING: Don't make n too large.
	// Recall that the number of permutations is n!
	// which can be very large, even when n is as small as 20 --
	// 20! = 2,432,902,008,176,640,000 and
	// 21! is too big to fit into a Java long, which is
	// why we use BigInteger instead.
	//----------------------------------------------------------

	public PermutationGenerator (int n) {
		if (n < 1) {
			throw new IllegalArgumentException ("Min 1");
		}
		a = new int[n];
		total = getFactorial (n);
		reset ();
	}

	//------
	// Reset
	//------

	public void reset () {
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		numLeft = new BigInteger (total.toString ());
	}

	//------------------------------------------------
	// Return number of permutations not yet generated
	//------------------------------------------------

	public BigInteger getNumLeft () {
		return numLeft;
	}

	//------------------------------------
	// Return total number of permutations
	//------------------------------------

	public BigInteger getTotal () {
		return total;
	}

	//-----------------------------
	// Are there more permutations?
	//-----------------------------

	public boolean hasMore () {
		return numLeft.compareTo (BigInteger.ZERO) == 1;
	}

	//------------------
	// Compute factorial
	//------------------

	private static BigInteger getFactorial (int n) {
		BigInteger fact = BigInteger.ONE;
		for (int i = n; i > 1; i--) {
			fact = fact.multiply (new BigInteger (Integer.toString (i)));
		}
		return fact;
	}

	//--------------------------------------------------------
	// Generate next permutation (algorithm from Rosen p. 284)
	//--------------------------------------------------------

	public int[] getNext () {

		if (numLeft.equals (total)) {
			numLeft = numLeft.subtract (BigInteger.ONE);
			return a;
		}

		int temp;

		// Find largest index j with a[j] < a[j+1]

		int j = a.length - 2;
		while (a[j] > a[j+1]) {
			j--;
		}

		// Find index k such that a[k] is smallest integer
		// greater than a[j] to the right of a[j]

		int k = a.length - 1;
		while (a[j] > a[k]) {
			k--;
		}

		// Interchange a[j] and a[k]

		temp = a[k];
		a[k] = a[j];
		a[j] = temp;

		// Put tail end of permutation after jth position in increasing order

		int r = a.length - 1;
		int s = j + 1;

		while (r > s) {
			temp = a[s];
			a[s] = a[r];
			a[r] = temp;
			r--;
			s++;
		}

		numLeft = numLeft.subtract (BigInteger.ONE);
		return a;

	}

}
