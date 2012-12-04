package com.lge.euler;

import static org.junit.Assert.*;

import org.junit.Test;

// We shall define a square lamina to be a square outline with a square "hole" so that the shape possesses vertical and horizontal symmetry.
// For example, using exactly thirty-two square tiles we can form two different square laminae:
//
// With one-hundred tiles, and not necessarily using all of the tiles at one time, it is possible to form forty-one different square laminae.
//
// Using up to one million tiles how many different square laminae can be formed?


// ...
// . .
// ...

//      ......
// .... .    .
// .  . .    .
// .  . .    .
// .... .    .
//      ......

public class Problem173 {

	@Test
	public void laminaType1() {
		assertEquals(8, lamina1(1));
		assertEquals(16, lamina1(2));
	}

	@Test
	public void laminaSumType1() {
		assertEquals(24, laminaSum1(1,2));
	}

	@Test
	public void laminaSumUnderType1() {
		assertEquals(21, laminasUnder1(100));
	}
	
	@Test
	public void laminaType2() {
		assertEquals(12, lamina2(1));
		assertEquals(20, lamina2(2));
	}
	@Test
	public void laminaSumType2() {
		assertEquals(32, laminaSum2(1,2));
	}
	
	@Test
	public void laminaSumUnderType2() {
		assertEquals(20, laminasUnder2(100));
	}
	
	@Test
	public void run() {
		assertEquals(1572729, laminasUnder1(1000000)+laminasUnder2(1000000));
	}
	
	private int laminasUnder2(int limit) {
		int count = 0;
		int from = 1;
		int to = 1;
		int sum = 0;

		while (from <= to) {
			sum = laminaSum2(from, to);
			if (sum > limit) {
				if (to == from)
					break;
				from++;
				to = from;
			} else {
//				System.out.println("("+sum+")"+from+" ~ "+to);
				count++;
				to++;
			}
		}
		return count;
	}

	private int laminaSum2(int from, int to) {
		int sum=0;
		for(int i=from;i<=to;i++)
			sum += lamina2(i);
		return sum;
	}

	private int lamina2(int n) {
		return 8*n+4;
	}

	private int laminasUnder1(int limit) {
		int count = 0;
		int from = 1;
		int to = 1;
		int sum = 0;

		while (from <= to) {
			sum = laminaSum1(from, to);
			if (sum > limit) {
				if (to == from)
					break;
				from++;
				to = from;
			} else {
//				System.out.println("("+sum+")"+from+" ~ "+to);
				count++;
				to++;
			}
		}
		return count;
	}

	private int laminaSum1(int from, int to) {
		int sum=0;
		for(int i=from;i<=to;i++)
			sum += lamina1(i);
		return sum;
	}

	private int lamina1(int n) {
		return 8*n;
	}
}
