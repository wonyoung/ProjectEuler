package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

import com.google.common.collect.*;
import com.lge.euler.Problem54PokerGame.Num;

public class Problem54PokerGame {

	@Test
	public void readFromFile() {
		
	}
	@Test
	public void rankShouldBeStraightOrNot() {
		Player player1 = new Player();
		Player player2 = new Player();

		startGame(player1, player2, "6C 4S 3C 2H 5S 7D 2S 5D 3S AC");
		assertEquals(true, isStraight(player1.cards));
		assertEquals(false, isStraight(player2.cards));
		
		assertEquals(Rank.STRAIGHT, rank(player1.cards));
		assertEquals(Rank.HIGH_CARD, rank(player2.cards));
	}

	@Test
	public void rankShouldBeFlushOrNot() {
		Player player1 = new Player();
		Player player2 = new Player();

		startGame(player1, player2, "8C TC KC 9C 4C 7D 2S 5D 3S AC");
		assertEquals(true, isFlush(player1.cards));
		assertEquals(false, isFlush(player2.cards));

		assertEquals(Rank.FLUSH, rank(player1.cards));
		assertEquals(Rank.HIGH_CARD, rank(player2.cards));
		assertEquals(Rank.FLUSH, player1.getRank());
		assertEquals(Rank.HIGH_CARD, player2.getRank());
		assertEquals(true, player1.getRank().ordinal() > player2.getRank().ordinal());
	}	

	@Test
	public void rankShouldBeFourOfACardOrNot() {
		Player player1 = new Player();
		Player player2 = new Player();

		startGame(player1, player2, "4C 4H 5S 4S 4D AC 8C 9C 2C 8H");
		assertEquals(true, isFourCard(player1.cards));
		assertEquals(false, isFourCard(player2.cards));

		assertEquals(Rank.FOUR_OF_A_KIND, rank(player1.cards));
		assertEquals(Rank.ONE_PAIR, rank(player2.cards));
	}	
	
	@Test
	public void rankShouldBeTwoPairsOrOnePair() {
		Player player1 = new Player();
		Player player2 = new Player();
		
		startGame(player1, player2, "4C 4H 5S 3S 3D AC 8C 9C 2C 8H");
		assertEquals(2, pairs(player1.cards));
		assertEquals(1, pairs(player2.cards));

		assertEquals(Rank.TWO_PAIR, rank(player1.cards));
		assertEquals(Rank.ONE_PAIR, rank(player2.cards));
	}	
	
	@Test
	public void rankShouldBeRoyalFlushOrNot() {
		Player player1 = new Player();
		Player player2 = new Player();
		
		startGame(player1, player2, "AC TC QC JC KC AD QD JD TD 4D");
		assertEquals(true, isRoyalFlush(player1.cards));
		assertEquals(false, isRoyalFlush(player2.cards));

		assertEquals(Rank.ROYAL_FLUSH, rank(player1.cards));
		assertEquals(Rank.FLUSH, rank(player2.cards));
	}	
	
	@Test
	public void rankShouldBeStraightFlushOrNot() {
		Player player1 = new Player();
		Player player2 = new Player();
		
		startGame(player1, player2, "4D 5D 6D 7D 8D 4C 5C 6C 7C 8D");
		assertEquals(true, isStraightFlush(player1.cards));
		assertEquals(false, isStraightFlush(player2.cards));
		
		assertEquals(Rank.STRAIGHT_FLUSH, rank(player1.cards));
		assertEquals(Rank.STRAIGHT, rank(player2.cards));		
	}	

	@Test
	public void rankShouldBeFullHouseFlushOrNot() {
		Player player1 = new Player();
		Player player2 = new Player();
		
		startGame(player1, player2, "4C 4H 4S 3S 3D AC AH AD 2C 8H");
		assertEquals(true, isFullHouse(player1.cards));
		assertEquals(false, isFullHouse(player2.cards));
		
		assertEquals(Rank.FULL_HOUSE, rank(player1.cards));
		assertEquals(Rank.THREE_OF_A_KIND, rank(player2.cards));		
	}	

	@Test
	public void rankShouldBeThreeOfKindOrNot() {
		Player player1 = new Player();
		Player player2 = new Player();
		
		startGame(player1, player2, "4C 4H 4S 3S 2D AC 8C 9C 2C 8H");
		assertEquals(true, isThreeOfKind(player1.cards));
		assertEquals(false, isThreeOfKind(player2.cards));
		
		assertEquals(Rank.THREE_OF_A_KIND, rank(player1.cards));
		assertEquals(Rank.ONE_PAIR, rank(player2.cards));		
	}	
	
	private void startGame(Player player1, Player player2, String allCards) {
		StringTokenizer st = new StringTokenizer(allCards);
		
		player1.clearCards();
		player2.clearCards();
		for(int i=0;i<5;i++)
			player1.addCard(st.nextToken());
		for(int i=0;i<5;i++)
			player2.addCard(st.nextToken());
		
		player1.generateRank();		
		player2.generateRank();
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
		}
		
		public static Shape get(char num) {
			for (Shape a : Shape.values()) {
				if (a.ch == num)
					return a;
			}
			return NONE;
		}
	}
	
	/*
	 */
	
	enum Rank {
		HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT,
		FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH;		
	};
	
	public static Rank rank(TreeMultimap<Num, Shape> cardset, TreeMultiset<Num> rankset) {
		rankset = TreeMultiset.create();
		if (isRoyalFlush(cardset)) {
			return Rank.ROYAL_FLUSH;
		}
		if (isStraightFlush(cardset)) {
			return Rank.STRAIGHT_FLUSH;
		}
		if (isFourCard(cardset)) {
			rankset.add(getFourCard(cardset));
			return Rank.FOUR_OF_A_KIND;
		}
		if (isFullHouse(cardset)) {
			rankset.addAll(getFullHouse(cardset));
			return Rank.FULL_HOUSE;
		}
		if (isFlush(cardset)) {
			return Rank.FLUSH;
		}
		if (isStraight(cardset)) {
			return Rank.STRAIGHT;
		}
		if (isThreeOfKind(cardset)) {
			rankset.addAll(getThreeOfKind(cardset));
			return Rank.THREE_OF_A_KIND;
		}
		if (pairs(cardset) >= 2) {
			rankset.addAll(getPairs(cardset));
			return Rank.TWO_PAIR;
		}
		if (pairs(cardset) == 1) {
			rankset.addAll(getPairs(cardset));
			return Rank.ONE_PAIR;
		}
		return Rank.HIGH_CARD;	
	}

	
	public static Rank rank(TreeMultimap<Num, Shape> cardset) {
		TreeMultiset<Num> rankset = TreeMultiset.create();
		
		return rank(cardset, rankset);
	}

	
	private static TreeMultiset<Num> getFullHouse(TreeMultimap<Num, Shape> cardset) {
		TreeMultiset<Num> list = TreeMultiset.create();
		
		if (isThreeOfKind(cardset)) {
			Num threeCards = getThreeOfKind(cardset).firstEntry().getElement();
			list.add(threeCards);
			
			TreeMultimap<Num, Shape> rest = TreeMultimap.create(cardset);
			rest.removeAll(threeCards);
			
			list.addAll(getPairs(cardset));
		}
		
		return list;
	}

	
	private static boolean isFullHouse(TreeMultimap<Num, Shape> cardset) {
		if (isThreeOfKind(cardset)) {
			Num threeCards = null;
			Multiset<Num> numbers = cardset.keys();
			SortedSet<Num> numbersSet = cardset.keySet();
			for(Num n : numbersSet) {
				if (numbers.count(n) == 3)
					threeCards = n;
			}
			
			TreeMultimap<Num, Shape> rest = TreeMultimap.create(cardset);
			rest.removeAll(threeCards);
			return pairs(rest) >= 1;			
		}
		
		return false;
	}

	private static TreeMultiset<Num> getPairs(TreeMultimap<Num, Shape> cardset) {
		return getCardsCountN(cardset, 2);
	}
	
	private static TreeMultiset<Num> getThreeOfKind(TreeMultimap<Num, Shape> cardset) {
		return getCardsCountN(cardset, 3);
	}
	
	
	private static TreeMultiset<Num> getCardsCountN(TreeMultimap<Num, Shape> cardset, int targetCount) {
		TreeMultiset<Num> list = TreeMultiset.create();
		Multiset<Num> numbers = cardset.keys();
		SortedSet<Num> numbersSet = cardset.keySet();
		for(Num n : numbersSet) {
			if (numbers.count(n) == targetCount)
				list.add(n);
		}
		return list;
	}

	private static boolean isThreeOfKind(TreeMultimap<Num, Shape> cardset) {
		return !getThreeOfKind(cardset).isEmpty();
	}

	private static boolean isStraightFlush(TreeMultimap<Num, Shape> cardset) {
		if (isStraight(cardset)
				&& isFlush(cardset))
			return true;
		return false;
	}

	private static boolean isRoyalFlush(TreeMultimap<Num, Shape> cardset) {
		if (isStraight(cardset) 
				&& isFlush(cardset)
				&& getEndNumOfStraight(cardset) == Num.ACE)
			return true;
		return false;
	}
	
	private static Num getFourCard(TreeMultimap<Num, Shape> cardset) {
		Multiset<Num> numbers = cardset.keys();
		for(Num n : numbers) {
			if (numbers.count(n) == 4)
				return n;
		}
		return null;
	}

	private static Num getEndNumOfStraight(TreeMultimap<Num, Shape> cardset) {
		Multiset<Num> numbers = cardset.keys();
		Num result = null;

		int preOrdinal = -1;
		int count = 0;
		for(Num n : numbers) {
			if (preOrdinal > 0 && (n.ordinal() - preOrdinal) != 1)
				count = 0;
			preOrdinal = n.ordinal();
			count++;
			
			if(count >= 4)
				result = n;
		}
		
		return result;
	}
	
	public static boolean isStraight(TreeMultimap<Num, Shape> cardset) {
		return getEndNumOfStraight(cardset) != null;
	}	
	
	public static boolean isFlush(TreeMultimap<Num, Shape> cardset) {
		Multiset<Shape> shapes = Multimaps.invertFrom(cardset, TreeMultimap.<Shape, Num> create()).keys();
		
		for(Shape s : shapes) {
			if (shapes.count(s) >= 5)
				return true;
		}
		return false;	
	}
	public static boolean isFourCard(TreeMultimap<Num, Shape> cardset) {
		return getFourCard(cardset) != null;	
	}	
	
	public static int pairs(TreeMultimap<Num, Shape> cardset) {
		return countNcards(cardset, 2);
	}
	
	private static int countNcards(TreeMultimap<Num, Shape> cardset, int targetCount) {
		int count = 0;
		Multiset<Num> numbers = cardset.keys();
		SortedSet<Num> numbersSet = cardset.keySet();
		for(Num n : numbersSet) {
			if (numbers.count(n) == targetCount)
				count++;
		}
		return count;
	}
	
	class Player {
		TreeMultimap<Num, Shape> cards = TreeMultimap.create();
		TreeMultiset<Num> rankCards;
		Rank rank = Rank.HIGH_CARD;
		
		public void addCard(String s) {
			cards.put(Num.get(s.charAt(0)), Shape.get(s.charAt(1)));
		}

		public void generateRank() {
			rank = rank(cards, rankCards);
		}
		
		public Rank getRank() {
			return rank;
		}
		
		public TreeMultiset<Num> getRankCards() {
			return rankCards;
		}

		public void clearCards() {
			cards.clear();
		}
	}
}


