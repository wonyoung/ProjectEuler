/*
sqrt(23) = [4;(1,3,1,8)]

4*4 = 16
5*5 = 25
4, 
c
----
sqrt(a)-b

c(sqrt(a) + b)  sqrt(a) - (nextC-b)
----------------
(a - b^2) nextC		


(a - b^2) / c nextC


n = b;
while(n>0  || a - n^2 > 0)
  nextB = n;
  nextK++;
  n = nextB - nextC; 
nextB = -nextB


sqrt(23) -4
----------
23 - 4^2

4+sqrt(23)

(a+b)*(a-b) = a^2- b^2

23 - 16
*/


package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

public class Problem64 {

	@Ignore
	public void testNormal() {
		SquareRootFraction s = squareRoot(23);

		s.print();
		for(int i=0;i<7;i++) {
			s = s.next();
			s.print();
		}
	}

	@Test
	public void testList() {
		List<SquareRootFraction> list = continuedFractions(23);
		
		assertEquals(false, isOddPeriod(list));
		
		for(SquareRootFraction sqp : list)
			sqp.print();
	}

	
	@Test
	public void run() {
		assertEquals(4, countOddPeriodContinuedFractions(13));
		assertEquals(1322, countOddPeriodContinuedFractions(10000));
	}

	private int countOddPeriodContinuedFractions(int j) {
		int count = 0;
		for(int i=1;i<=j;i++) {
			if(Util.isSquare(i))
				continue;
			if (isOddPeriod(continuedFractions(i)))
				count++;
		}
		return count;
	}
	
	private boolean isOddPeriod(List<SquareRootFraction> list) {
		return ((list.size()-1) % 2) != 0;
	}

	private List<SquareRootFraction> continuedFractions(int root) {
		List<SquareRootFraction> list = new ArrayList<SquareRootFraction>();
		
		SquareRootFraction s = squareRoot(root);
		list.add(s);
		
		s = s.next();
		while(!list.contains(s)) {
			list.add(s);
			s = s.next();
		}
		return list;
	}

	@Test
	public void testSquareRoot() {
		for(int i=1;i<26;i++) {
			switch(i) {
			case 1:
			case 4:
			case 9:
			case 16:
			case 25:
				assertEquals(true, Util.isSquare(i));
				break;
			default:
				assertEquals(false, Util.isSquare(i));
			}
		}
	}
	
	SquareRootFraction squareRoot(int root) {
		SquareRootFraction srp = new SquareRootFraction();

		int i;
		for(i=1;i*i<root;i++) {
		}
		i--;

		srp.n = i;
		srp.root = root;
		srp.num = i;
		srp.dem = 1;

		return srp;
	}
	
	class SquareRootFraction {
		int root;
		int num;
		int dem;

		int n;
		//          dem
		// n +  --------------
		//       //root - num
		//
		public void print() {
			System.out.println("         "+dem );
			System.out.println(""+n +" + ------------------");
			System.out.println("          //"+root + "  - " + num );
		}

		SquareRootFraction next() {
			SquareRootFraction next = new SquareRootFraction();

			next.root = root;
			next.dem = (root - num*num) / dem;
			int nn = num - next.dem;
			while(nn>0 || root - nn*nn > 0) {
				next.num = nn;
				next.n++;
				nn = nn - next.dem;
			}
			next.num *= -1;

			return next;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + dem;
			result = prime * result + n;
			result = prime * result + num;
			result = prime * result + root;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SquareRootFraction other = (SquareRootFraction) obj;
			if (dem != other.dem)
				return false;
			if (n != other.n)
				return false;
			if (num != other.num)
				return false;
			if (root != other.root)
				return false;
			return true;
		}
		
	}
}
