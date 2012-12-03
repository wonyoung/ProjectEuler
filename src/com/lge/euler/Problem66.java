package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.*;

public class Problem66 {

	// x^2 - D* y^2 = 1
	
	@Test
	public void findMinimalXofQuadraticDiophantineEquation1() {
		assertEquals(3, minimalXofSolution1(2));
		assertEquals(2, minimalXofSolution1(3));
		assertEquals(9, minimalXofSolution1(5));
		assertEquals(5, minimalXofSolution1(6));
		assertEquals(8, minimalXofSolution1(7));
		assertEquals(10861079, minimalXofSolution1(449));
		assertEquals(598337, minimalXofSolution1(778));
	}
	
	@Test
	public void findMinimalXofQuadraticDiophantineEquation2() {
		assertEquals(3, minimalXofSolution2(2));
		assertEquals(2, minimalXofSolution2(3));
		assertEquals(9, minimalXofSolution2(5));
		assertEquals(5, minimalXofSolution2(6));
		assertEquals(8, minimalXofSolution2(7));
		assertEquals(10861079, minimalXofSolution2(449));
		assertEquals(598337, minimalXofSolution2(778));
	}
	
	@Ignore
	public void findMinimalXofQuadraticDiophantineEquation3() {
		assertEquals(3, minimalXofSolution3(2));
		assertEquals(2, minimalXofSolution3(3));
		assertEquals(9, minimalXofSolution3(5));
		assertEquals(5, minimalXofSolution3(6));
		assertEquals(8, minimalXofSolution3(7));
		assertEquals(37177335, minimalXofSolution3(449));
		assertEquals(598337, minimalXofSolution3(778));
	}
	
	@Test
	public void findMinimalXofQuadraticDiophantineEquation4() {
		assertEquals(3, minimalXofSolution4(2));
		assertEquals(2, minimalXofSolution4(3));
		assertEquals(9, minimalXofSolution4(5));
		assertEquals(5, minimalXofSolution4(6));
		assertEquals(8, minimalXofSolution4(7));
		assertEquals(371773335, minimalXofSolution4(449));
		assertEquals(598337, minimalXofSolution4(778));
	}
	
	@Test
	public void stepByStep() {
		assertEquals(10861079, minimalXofSolution2(449));
	}

	
	private int minimalXofSolution2(int D) {
		int x = 2;//(int) Math.sqrt(D+3);
		int yyD = x*x - 1;
		while(!((yyD % D == 0) 
				&& Util.isSquare(yyD / D))) {
			x++;
			yyD = x*x - 1;
		}
		print(D, x, Math.sqrt(yyD/D));
		return x;
	}
	private int minimalXofSolution3(long D) {
		long x = 2;//(int) Math.sqrt(D+3);
		long yyD = x*x - 1;
		
		while(!((yyD % D == 0) 
				&& Util.isSquare(((double)(yyD) / (double)D)))) {
			x++;
			yyD = x*x - 1;
		}
//		print(D, x, Math.sqrt(yyD/D));		
		System.out.print("\t"+D+"\t");
		System.out.print("\t"+yyD+"\t");
		System.out.println(Math.sqrt((double)(yyD) / (double)D));
		return (int)x;
	}
		
	@Test
	public void isSquares() {
		assertEquals(false, Util.isSquare(924413651));
	}
	
	private int minimalXofSolution4(int D) {
		long x = 2;//(int) Math.sqrt(D+3);
		long yyD = x*x - 1;
		
		while(!((yyD % D == 0) 
				&& Util.isSquare(yyD / D))) {
			x++;
			yyD = x*x - 1;
		}
		System.out.print("\t"+D+"\t");
		System.out.print("\t"+yyD+"\t");
		System.out.println(Math.sqrt(yyD/D));
		return (int)x;
	}	
	@Test
	public void findDWithLargestXofQuadraticDiophantineEquations() {
		assertEquals(5, largestXofSolutions(7));
		assertEquals(449, largestXofSolutions(1000));
//		assertEquals(778, largestXofSolutions(1000));
	}
	
	private int largestXofSolutions(int maxD) {
		int resultD=0;
		int largestX = 0;
		for(int d=2;d<=maxD;d++) {
			if(Util.isSquare(d))
				continue;
			int x = minimalXofSolution4(d);
			if (largestX <= x) {
				largestX = x;
				resultD = d;
			}
		}
		return resultD;
	}

	private int minimalXofSolution1(int D) {
		int x = startValueofX(D, 1);
		int y = 1;
		int q = quadraticDiophantineEquation(D, x, y);
		while(q!=0) {
			x++;
			y = startValueOfY(D, x);
			q = quadraticDiophantineEquation(D, x, y);
			while(q>0) {
				y++;
				q = quadraticDiophantineEquation(D, x, y);
			}
		}
		
		print(D, x, y);
		return x;
	}
	
	private void print(int D, int x, double y) {
		System.out.println(""+x+"\t^2 - \t"+D+"\t*\t"+y+"\t^2 == 1");
	}
	
	private int startValueofX(int D, int y) {
		return (int)Math.sqrt(D);
//		return Math.round((float)Math.sqrt(D*y*y+1));
	}

	private int startValueOfY(int D, int x) {
		return Math.round((float)Math.sqrt((double)(x*x-1)/D));
	}


	private int quadraticDiophantineEquation(int D, int x, int y) {
		long xDouble = ((long)x)*x;
		long DyDouble = ((long)D)*y*y;
		return (int)(xDouble-DyDouble-1);
	}
}
