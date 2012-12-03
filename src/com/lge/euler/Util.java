package com.lge.euler;

public class Util {
	static boolean isSquare(long root) {
		long sqrt = (long) Math.sqrt(root);
		return sqrt*sqrt == root;
	}

	static boolean isSquare(double root) {
		double sqrt = Math.sqrt(root);
		return sqrt == Math.rint(sqrt);
	}

}
