package com.lge.euler;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import com.google.common.collect.*;
import com.lge.euler.Problem54PorkerGame.Num;
import com.lge.euler.Problem54PorkerGame.Shape;

public class Problem54PorkerGame {

	@Test
	public void testNormal() {
		Player player1 = new Player();
		Player player2 = new Player();

		
		startGame(player1, player2, "6C 4S 3C 2H 5S 7D 2S 5D 3S AC");
		assertEquals(true, isStraight(player1.cards));
		assertEquals(false, isStraight(player2.cards));

		startGame(player1, player2, "8C TC KC 9C 4C 7D 2S 5D 3S AC");
		assertEquals(true, isFlush(player1.cards));
		assertEquals(false, isFlush(player2.cards));
		
	}
	
	private void startGame(Player player1, Player player2, String allCards) {
		StringTokenizer st = new StringTokenizer(allCards);
		
		player1.clearCards();
		player2.clearCards();
		for(int i=0;i<5;i++)
			player1.addCard(st.nextToken());
		for(int i=0;i<5;i++)
			player2.addCard(st.nextToken());
		
		player1.getRank();		
		player2.getRank();
	}

	enum Num {
		TWO('2'),
		THREE('3'),
		FOUR('4'),
		FIVE('5'),
		SIX('6'),
		SEVEN('7'),
		EIGHT('8'),
		NINE('9'),
		TEN('T'),
		JACK('J'),
		QUEEN('Q'),
		KING('K'),
		ACE('A'),
		
		NONE('-');
		
		private int ch;

		Num(char c) {
			this.ch = c;
//			this.order = getNumber(c);
		}
		
		public static Num get(char num) {
			for (Num a : Num.values()) {
				if (a.ch == num)
					return a;
			}
			return NONE;
		}
		
		private int getNumber(char ch) {
			final char[] numbers = { 'T', 'J', 'Q', 'K', 'A' };
			for(int i=0;i<numbers.length;i++)
				if (ch == numbers[i])
					return (10+i);
			return ch - '0';
		}
		
	}

	enum Shape {
		CLOVER('C'),
		HEART('H'),
		DIAMOND('D'),
		SPADE('S'),
		
		NONE('-');
		
		private int ch;

		Shape(char c) {
			this.ch = c;
//			this.order = getShape(c);
		}
		
		public static Shape get(char num) {
			for (Shape a : Shape.values()) {
				if (a.ch == num)
					return a;
			}
			return NONE;
		}
		
		private int getShape(char ch) {
			final char[] shapes = { 'C', 'H', 'D', 'S' };
			for(int i=0;i<shapes.length;i++)
				if (ch == shapes[i])
					return i;
			return -1;
		}		
		
	}
	
	enum Rank {
		HIGH_CARD(0), ONE_PAIR(1), TWO_PAIR(2), THREE_OF_A_KIND(3), STRAIGHT(4),
		FLUSH(5), FULL_HOUSE(6), FOUR_OF_A_KIND(7), STRAIGHT_FLUSH(8), ROYAL_FLUSH(9);
		
		int level;
		Rank(int order) {
		}
		public void setLevel(int i) {
			level = i;
		}
		
	};
	
	public static boolean isStraight(TreeMultimap<Num, Shape> cardset) {
		Multiset<Num> numbers = cardset.keys();

		int preOrdinal = -1;
		for(Num n : numbers) {
			System.out.println(" "+ n.ordinal() + numbers.count(n));
			
			if (preOrdinal > 0 && (n.ordinal() - preOrdinal) != 1)
				return false;
			if (numbers.count(n) > 1)
				return false;
			preOrdinal = n.ordinal();
		}
		
		return true;
	}
	
	public static boolean isFlush(TreeMultimap<Num, Shape> cardset) {
		Multiset<Shape> shapes = Multimaps.invertFrom(cardset, TreeMultimap.<Shape, Num> create()).keys();
		
		for(Shape s : shapes) {
			if (shapes.count(s) >= 5)
				return true;
		}
		return false;	
	}
	
	class Player {
		TreeMultimap<Num, Shape> cards = TreeMultimap.create();
		
		public void addCard(String s) {
			Card card = new Card(s);
			cards.put(card.number, card.shape);
		}

		public Rank getRank() {
			Rank r = Rank.HIGH_CARD;
			r.setLevel(9);
			return r;
		}
		
 
		

		public void clearCards() {
			cards.clear();
		}
	}
	
	class Card {
		Num number;
		Shape shape;
		
		public Card(String card) {
			number = Num.get(card.charAt(0));
			shape = Shape.get(card.charAt(1));
//			print();
		}

		private void print() {
			System.out.println("num("+number.ordinal()+"), shape("+shape.ordinal()+")");
			
		}


	}
}


