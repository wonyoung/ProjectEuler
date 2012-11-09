package com.lge.euler;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

class Point {
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int x;
	public int y;
}

public class Problem91 {

	@Test
	public void shouldBeRightTriangle()
	{
		assertEquals(true, isRightTriangle(new Point(1,0), new Point(0,1)));
		assertEquals(false, isRightTriangle(new Point(2,1), new Point(1,2)));
		assertEquals(true, isRightTriangle(new Point(1,1), new Point(2,0)));
	}
	
	@Test
	public void testNumofRightTriangle()
	{
		assertEquals(3, getNumberOfRightTriangles(1,1));
		assertEquals(14, getNumberOfRightTriangles(2,2));
	}

	@Test
	public void run()
	{
		assertEquals(14234, getNumberOfRightTriangles(50, 50));
	}
	
	private int getNumberOfRightTriangles(int width, int height) {
		Point a = new Point(0,0);
		Point b = new Point(0,0);
		int count = 0;
		
		for (a.x=0;a.x<=width;a.x++)
			for (a.y=0;a.y<=height;a.y++)
			{
				if (a.x+a.y == 0)
					continue;
				
				for (b.x=0;b.x<=width;b.x++)
					for (b.y=0;b.y<=height;b.y++)
					{
						if (b.x+b.y == 0)
							continue;
						
						if (isRightTriangle(a, b))
						{
//							System.out.println("("+a.x+","+a.y+"), ("+b.x+","+b.y+")");
							count++;
						}
					}
			}
				
		// TODO Auto-generated method stub
		return count/2;
	}

	private boolean isRightTriangle(Point a, Point b) {
		int toA, toB, AtoB;
		int deltaX, deltaY;
		List <Integer> edges = new ArrayList<Integer>();
		
		toA = a.x*a.x+a.y*a.y;
		toB = b.x*b.x+b.y*b.y;
		deltaX = a.x - b.x;
		deltaY = a.y - b.y;
		AtoB = deltaX*deltaX+deltaY*deltaY;
		
		if (AtoB == 0)
			return false;
		
		edges.add(toA);
		edges.add(toB);
		edges.add(AtoB);
		Collections.sort(edges);
		
		if (edges.get(2) == edges.get(0)+edges.get(1))
			return true;
		
		return false;
	}
}
