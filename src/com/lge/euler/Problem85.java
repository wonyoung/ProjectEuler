package com.lge.euler;

import static org.junit.Assert.*;
import java.util.SortedMap;
import java.util.TreeMap;
import org.junit.Test;

public class Problem85 {

	@Test
	public void testGetRectangles()
	{
		assertEquals(18, getRectangles(3,2));
		assertEquals(9, getRectangles(2,2));
	}

	private int getRectangles(int width, int height) {
		int count = 0;
		
		for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				count += (width-x) * (height-y);
			}
		}
		
		return count;
	}
	
	@Test
	public void testNearestValue1()
	{
		assertEquals(6, getNearestSolutionArea(20));
		assertEquals(22, getNearestSolutionArea(200));
	}
	
	@Test
	public void run()
	{
		assertEquals(2772, getNearestSolutionArea(2000000));
	}

	private int getNearestSolutionArea(int rectangles) {
		int exactlyRectangles;
		int width;
		final int delta = 100;
		SortedMap <Integer,Integer> solutions = new TreeMap<Integer, Integer>();
		
		width = getMaxWidth(rectangles);
		System.out.println("Max width: "+width);
		for (int x=1;x<=width;x++)
			for (int y=1;y<=width;y++) {
				if (x>y)
					continue;
				if (x<=0)
					continue;
				if (y<=0)
					continue;
				exactlyRectangles = getRectangles(x, y);
				if (exactlyRectangles > rectangles+delta)
					break;
				
				if (exactlyRectangles < 0)
					break;

//				System.out.println(x+","+y+":"+exactlyRectangles);
				int d = Math.abs(rectangles - exactlyRectangles);
				if (d < delta)
					solutions.put(d, x*y);
			}

		
//		width = getNearestWidth(rectangles);
//		exactlyRectangles = getRectangles(width, width);				
//
//		for (int x=width-step;x<=width+step;x++)
//			for (int y=width-step;y<=width+step;y++) {
//				if (x>y)
//					continue;
//				if (x<=0)
//					continue;
//				if (y<=0)
//					continue;
//				exactlyRectangles = getRectangles(x, y);
//				solutions.put(Math.abs(rectangles - exactlyRectangles), exactlyRectangles);
//				System.out.println(x+","+y+":"+exactlyRectangles);
//			}
		
		return solutions.get(solutions.firstKey());
	}

	private int getMaxWidth(int nearestRectangles) {
		int width = 1;
		int rectangles = 1;
		
		while (rectangles < nearestRectangles)
		{
			width++;
			rectangles = getRectangles(width, 1);
		}
		
		return width;
	}

//	private int getNearestWidth(int nearestRectangles) {
//		int width = 1;
//		int step = 512;
//		int rectangles = 1;
//		int direction = 1;
//		int preDirection;
//		
//		
//		while (rectangles != nearestRectangles)
//		{
//			rectangles = getRectangles(width, width);
//			preDirection = direction;
//
//			
//			if (rectangles < nearestRectangles) 
//			{
//				direction = 1;
//			} 
//			else 
//			{
//				direction = -1;
//			}
//			if (direction != preDirection)
//			{
//				if (step > 1)
//					step /= 2;
//				else
//					break;
//			}
//			
//			System.out.println(rectangles+":"+width+" "+direction+"("+step+")");
//			width = width + direction*step;
//		}
//		
//		rectangles = getRectangles(width, width);
//		
//		return width;
//	}
}
