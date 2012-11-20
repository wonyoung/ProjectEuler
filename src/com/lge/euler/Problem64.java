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

	@Test
	public void testNormal() {
		SquareRootPart s = squareRoot(23);

		s.print();
		for(int i=0;i<7;i++) {
			s = s.next();
			s.print();
		}
	}


	SquareRootPart squareRoot(int root) {
		SquareRootPart srp = new SquareRootPart();

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

	class SquareRootPart {
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

		SquareRootPart next() {
			SquareRootPart next = new SquareRootPart();

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
	}
}
