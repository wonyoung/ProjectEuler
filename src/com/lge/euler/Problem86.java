package com.lge.euler;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class Problem86 {

//	A spider, S, sits in one corner of a cuboid room, measuring 6 by 5 by 3, and a fly, F, sits in the opposite corner. 
//	By travelling on the surfaces of the room the shortest "straight line" distance from S to F is 10 and the path is shown on the diagram.
//
//
//	However, there are up to three "shortest" path candidates for any given cuboid and the shortest route doesn't always have integer length.
//
//	By considering all cuboid rooms with integer dimensions, up to a maximum size of M by M by M,
//	there are exactly 2060 cuboids for which the shortest route has integer length when M=100, 
//	and this is the least value of M for which the number of solutions first exceeds two thousand; the number of solutions is 1975 when M=99.
//
//	Find the least value of M such that the number of solutions first exceeds one million.
		
	CuboidRoom cuboid;
	
	@Before
	public void setup() {
	}
	
	@Test
	public void createCuboidRoomObject() {
		cuboid = new CuboidRoom(6, 5, 3);
	}

	@Test
	public void get10When6by5by3() {
		cuboid = new CuboidRoom(6, 5, 3);
		assertEquals(10.0, cuboid.shortestRoute(), 0.00001);
	}
	
	@Test
	public void getAandBwhenShortestRoute() {
		cuboid = new CuboidRoom(6, 5, 3);
		assertArrayEquals(new int[] {6,8}, cuboid.shortestEdge());
	}
	
	@Test
	public void getAandBwhenShortestRouteWithAnotherInput() {
		cuboid = new CuboidRoom(6, 3, 3);
		assertArrayEquals(new int[] {6,6}, cuboid.shortestEdge());
	}

	@Test
	public void shortestRouteIsIntegerValue() {
		cuboid = new CuboidRoom(6, 5, 3);
		assertTrue(cuboid.isIntegerShortestRoute());
	}
		
	@Test
	public void MEquals1ThenReturnsZero() {
		assertEquals(0, findNumberOfCuboidsHavingIntegerShortestRoute(1));
	}

	@Test
	public void MEquals5ThenReturnsOne() {
		assertEquals(3, findNumberOfCuboidsHavingIntegerShortestRoute(5));
	}
	private int findNumberOfCuboidsHavingIntegerShortestRoute(int M) {
		if (M == 1)
			return 0;

		int count = cuboidsWithAddingM(M);
			
		return findNumberOfCuboidsHavingIntegerShortestRoute(M-1) + count;
	}

	private int cuboidsWithAddingM(int M) {
		int count = 0;

		for(int i=1;i<M;i++) {
			for(int j=i;j<M;j++) {
					if(new CuboidRoom(i, j, M).isIntegerShortestRoute())
						count++;
			}
			if(new CuboidRoom(i, M, M).isIntegerShortestRoute())
				count++;
		}
		if(new CuboidRoom(M, M, M).isIntegerShortestRoute())
			count++;
		
		
		return count;
	}

	@Test
	public void runOneCase() {
		int count = 0;
		int M=100;
		for(int i=1;i<=M;i++) {
			for(int j=i;j<=M;j++) {
				for(int k=j;k<=M;k++)
					if(new CuboidRoom(i, j, k).isIntegerShortestRoute())
						count++;
			}
		}
		assertEquals(2060, count);
	}
	
	@Test
	public void runWithPreviousResult() {
		cuboidsOver(1000000);
	}

	private void cuboidsOver(int M) {
		int count = 0;
		for(int i=1;;i++) {
			count += cuboidsWithAddingM(i);
			if (count > M) {
				System.out.println(""+count+" number of Cuboids when M is "+ i);
				break;
			}
		}
	}
}

class CuboidRoom
{
	int [] edges;
	
	public CuboidRoom(int height, int width, int depth) {
		edges = new int [3];
		edges[0] = height;
		edges[1] = width;
		edges[2] = depth;
	}

	public boolean isIntegerShortestRoute() {
		return Util.isSquare(sumOfSquare());
	}

	public int[] shortestEdge() {
		int minDiff = Integer.MAX_VALUE;
		int shortestEdgeBig = 0;
		int shortestEdgeSmall = 0;
		
		for (int i=0; i<3; i++) {
			int a = edges[i];
			int b = edges[(i+1) % 3] + edges[(i+2) % 3];
			int small;
			int big;
			if (a>b) {
				small = b;
				big = a;
			} else {
				small = a;
				big = b;
			} 
			if (big - small < minDiff) {
				minDiff = big - small;
				shortestEdgeBig = big;
				shortestEdgeSmall = small;
			}
		}
		return new int[] {shortestEdgeSmall, shortestEdgeBig};
	}

	public double shortestRoute() {
		int result = sumOfSquare();
		return Math.sqrt(result);
	}

	private int sumOfSquare() {
		int [] shortestEdges = shortestEdge();
		int result = shortestEdges[0]*shortestEdges[0] + shortestEdges[1]*shortestEdges[1];
		return result;
	}
}