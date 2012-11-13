package com.lge.euler;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Problem66 {
	@Test
	public void testCreateSquares()
	{
		assertArrayEquals(new BigInteger[] { new BigInteger("1"),
				new BigInteger("4"),
				new BigInteger("9") }, createSquares(3));
	}

	private BigInteger [] createSquares(int num) {
		List<BigInteger> squares = new ArrayList <BigInteger>();
//		BigInteger prev = new BigInteger("1");
//		BigInteger current = new BigInteger("1");
//		BigInteger maxNum = new BigInteger(Integer.toString(num));
//		
//		int i=1;
//		
//		while (maxNum.multiply(prev).compareTo(current.subtract(BigInteger.ONE)) >= 0)
//		{
//			BigInteger square = new BigInteger(Integer.toString(i));
//			square = square.multiply(square);
//			squares.add(square);
//			prev = current;
//			current = square; 
//			
//			i++;
//		}
		
		return squares.toArray(new BigInteger[0]);
	}

	@Test
	public void testQuadraticDiophantineEquation()
	{
		Map<Integer, Integer> map = createEquationSets(7);
		
		System.out.println(map.toString());
	}

	private Map<Integer, Integer> createEquationSets(int maxD) {
		Map<Integer, Integer> equationSets = new HashMap<Integer, Integer>();
		BigInteger squares[] = createSquares(7);
		
		for(int d=1;d<=maxD; d++)
		{
			BigInteger D = new BigInteger(Integer.toString(d));
			
			if(Arrays.binarySearch(squares, D) >= 0)
				continue;
			for(int i=0;i<squares.length;i++)
				for(int j=0;j<=i;j++)
				{
					int comp;
					BigInteger lValue = squares[i].subtract(D.multiply(squares[j]));
					comp = lValue.compareTo(BigInteger.ONE);
					if (comp == 0)
						equationSets.put(i+1, j+1);
				}
					
		}
		
		return equationSets;
	}
}
