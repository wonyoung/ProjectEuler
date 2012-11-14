package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.io.*;
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
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();

		startGame(player1, player2, "6C 4S 3C 2H 5S 7D 2S 5D 3S AC");
		assertEquals(true, isStraight(player1.cards));
		assertEquals(false, isStraight(player2.cards));
		
		assertEquals(Rank.STRAIGHT, rank(player1.cards));
		assertEquals(Rank.HIGH_CARD, rank(player2.cards));
	}

	@Test
	public void rankShouldBeFlushOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();

		startGame(player1, player2, "8C TC KC 9C 4C 7D 2S 5D 3S AC");
		assertEquals(true, isFlush(player1.cards));
		assertEquals(false, isFlush(player2.cards));

		assertEquals(Rank.FLUSH, rank(player1.cards));
		assertEquals(Rank.HIGH_CARD, rank(player2.cards));
		assertEquals(Rank.FLUSH, player1.getRank());
		assertEquals(Rank.HIGH_CARD, player2.getRank());
		assertEquals(true, player1.getRank().compareTo(player2.getRank()) > 0);
		
	}	

	@Test
	public void rankShouldBeFourOfACardOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();

		startGame(player1, player2, "4C 4H 5S 4S 4D AC 8C 9C 2C 8H");
		assertEquals(true, isFourCard(player1.cards));
		assertEquals(false, isFourCard(player2.cards));

		assertEquals(Rank.FOUR_OF_A_KIND, rank(player1.cards));
		assertEquals(Rank.ONE_PAIR, rank(player2.cards));
	}	
	
	@Test
	public void rankShouldBeTwoPairsOrOnePair() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		startGame(player1, player2, "4C 4H 5S 3S 3D AC 8C 9C 2C 8H");
		assertEquals(2, pairs(player1.cards));
		assertEquals(1, pairs(player2.cards));

		assertEquals(Rank.TWO_PAIR, rank(player1.cards));
		assertEquals(Rank.ONE_PAIR, rank(player2.cards));
	}	
	
	@Test
	public void rankShouldBeRoyalFlushOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		startGame(player1, player2, "AC TC QC JC KC AD QD JD TD 4D");
		assertEquals(true, isRoyalFlush(player1.cards));
		assertEquals(false, isRoyalFlush(player2.cards));

		assertEquals(Rank.ROYAL_FLUSH, rank(player1.cards));
		assertEquals(Rank.FLUSH, rank(player2.cards));
	}	
	
	@Test
	public void rankShouldBeStraightFlushOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		startGame(player1, player2, "4D 5D 6D 7D 8D 4C 5C 6C 7C 8D");
		assertEquals(true, isStraightFlush(player1.cards));
		assertEquals(false, isStraightFlush(player2.cards));
		
		assertEquals(Rank.STRAIGHT_FLUSH, rank(player1.cards));
		assertEquals(Rank.STRAIGHT, rank(player2.cards));		
	}	

	@Test
	public void rankShouldBeFullHouseFlushOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		startGame(player1, player2, "4C 4H 4S 3S 3D AC AH AD 2C 8H");
		assertEquals(true, isFullHouse(player1.cards));
		assertEquals(false, isFullHouse(player2.cards));
		
		assertEquals(Rank.FULL_HOUSE, rank(player1.cards));
		assertEquals(Rank.THREE_OF_A_KIND, rank(player2.cards));		
	}	

	@Test
	public void rankShouldBeThreeOfKindOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		startGame(player1, player2, "4C 4H 4S 3S 2D AC 8C 9C 2C 8H");
		assertEquals(true, isThreeOfKind(player1.cards));
		assertEquals(false, isThreeOfKind(player2.cards));
		
		assertEquals(Rank.THREE_OF_A_KIND, rank(player1.cards));
		assertEquals(Rank.ONE_PAIR, rank(player2.cards));		
	}	
	
	@Test
	public void runPokerGames() throws IOException {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		BufferedReader in = new BufferedReader(new FileReader("poker.txt"));
		String line;
		int count = 0;
		int lines = 0;
		while( (line = in.readLine()) != null) {
			lines++;
			System.out.println(line);
			if (startGame(player1, player2, line))
				count++;
//			if (lines == 5)
//				break;
		}
		System.out.println(count + " / " + lines);
	}
	private boolean startGame(PokerCards player1, PokerCards player2, String allCards) {
		StringTokenizer st = new StringTokenizer(allCards);
		
		player1.clearCards();
		player2.clearCards();
		for(int i=0;i<5;i++)
			player1.addCard(st.nextToken());
		for(int i=0;i<5;i++)
			player2.addCard(st.nextToken());
		
		player1.generateRank();		
		player2.generateRank();
		
		return player1.wins(player2);
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
	
	public static Rank rank(TreeMultimap<Num, Shape> cardset, SortedMultiset<Num> rankset) {
		Rank rank = Rank.HIGH_CARD;
		
		rankset = TreeMultiset.create();
		if (isRoyalFlush(cardset)) {
			rank = Rank.ROYAL_FLUSH;
		} else if (isStraightFlush(cardset)) {
			rank = Rank.STRAIGHT_FLUSH;
		} else if (isFourCard(cardset)) {
			rankset.add(getFourCard(cardset));
			rank = Rank.FOUR_OF_A_KIND;
		} else if (isFullHouse(cardset)) {
			rankset.addAll(getFullHouse(cardset));
			rank = Rank.FULL_HOUSE;
		} else if (isFlush(cardset)) {
			rank = Rank.FLUSH;
		} else if (isStraight(cardset)) {
			rank = Rank.STRAIGHT;
		} else if (isThreeOfKind(cardset)) {
			rankset.addAll(getThreeOfKind(cardset));
			rank = Rank.THREE_OF_A_KIND;
		} else if (pairs(cardset) >= 2) {
			rankset.addAll(getPairs(cardset));
			rank = Rank.TWO_PAIR;
		} else if (pairs(cardset) == 1) {
			rankset.addAll(getPairs(cardset));
			rank = Rank.ONE_PAIR;
		}
		
		rankset = rankset.descendingMultiset();
		return rank;	
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
	
	class PokerCards implements Comparable {
		TreeMultimap<Num, Shape> cards = TreeMultimap.create();
		SortedMultiset<Num> rankCards = TreeMultiset.create();
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
		
		public SortedMultiset<Num> getRankCards() {
			return rankCards;
		}

		public boolean wins(PokerCards o) {
			return compareTo(o) > 0;
		}

		public void clearCards() {
			cards.clear();
		}

		@Override
		public int compareTo(Object o) throws ClassCastException {
			if (!(o instanceof PokerCards))
				throw new ClassCastException("A PokerCards object expected.");
			
			return compareTo((PokerCards)o);
		}
		
		private int compareTo(PokerCards o) {
			System.out.println("A:"+getRank().name() +" B:"+o.getRank().name());
			if (getRank().compareTo(o.getRank()) == 0) {
				Num[] myCards;
				Num[] yourCards;
				
				myCards = rankCards.toArray(new Num[0]);
				yourCards = o.rankCards.toArray(new Num[0]);
				for(int i=myCards.length-1; i>=0; i--) {
					System.out.println("A:"+myCards[i].name() +" B:"+yourCards[i].name());
					int compared = myCards[i].compareTo(yourCards[i]);
					if (compared != 0)
						return compared;
				}
				myCards = cards.keySet().toArray(new Num[0]);
				yourCards = o.cards.keySet().toArray(new Num[0]);
				for(int i=myCards.length-1; i>=0; i--) {
					System.out.println("A:"+myCards[i].name() +" B:"+yourCards[i].name());
					int compared = myCards[i].compareTo(yourCards[i]);
					if (compared != 0)
						return compared;
				}				
				
				System.out.println("SAME RANKS");
				return 0;
			}
			return getRank().compareTo(o.getRank());			
			
		}
	}
}


