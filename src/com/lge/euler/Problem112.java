package com.lge.euler;

import static org.junit.Assert.*;

import org.junit.Test;

public class Problem112 {
	
	enum Number { BOUNCY, INCREASING, DECREASING };

	@Test
	public void shouldBeTrueIncreasingNumber() {
		assertEquals(true, isIncreasing(134468));
		assertEquals(true, isIncreasing(1333));
	}

	@Test
	public void shouldBeFalseWhenBouncyNumber() {
		assertEquals(false, isIncreasing(131));
	}
	
	@Test
	public void shouldBeTrueDecreasingNumber() {
		assertEquals(true, isDecreasingNumber(66420));
	}

	@Test
	public void shouldBeDecreasingFalseWhenBouncyNumber() {
		assertEquals(false, isDecreasingNumber(66839));
	}
	
	private boolean isDecreasingNumber(int num) {
		String s = String.valueOf(num);
		char preChar = '9';
		for (char c : s.toCharArray()) {
			if (preChar < c)
				return false;
			preChar = c;
		}
		return true;
	}

	private boolean isIncreasing(int num) {
		String s = String.valueOf(num);
		char preChar = 0;
		for (char c : s.toCharArray()) {
			if (preChar > c)
				return false;
			preChar = c;
		}
		return true;
	}
	
	@Test
	public void checkNumberBouncy() {
		assertEquals(Number.INCREASING, bouncy(134468));
		assertEquals(Number.DECREASING, bouncy(66420));
		assertEquals(Number.BOUNCY, bouncy(155349));
	}

	private Number bouncy(int num) {
		char[] s = String.valueOf(num).toCharArray();
		char preChar = s[0];
		boolean isIncreasing = true;
		boolean isDecreasing = true;
		for (char c : s) {
			if (preChar > c)
				isIncreasing = false;
			if (preChar < c)
				isDecreasing = false;
			preChar = c;
		}
		return isIncreasing ? Number.INCREASING : (isDecreasing ? Number.DECREASING :Number.BOUNCY);
	}
	
	@Test
	public void run() {
		int countBouncy = 0;
		int ratio=0;
		int num=0;
		
		while(ratio<99) {
			num++;
			if (bouncy(num) == Number.BOUNCY)
				countBouncy++;
			ratio = countBouncy*100/num;
		}
		System.out.println(""+countBouncy+"/"+num+"="+ratio);
		assertEquals(1587000, num);
	}
}
