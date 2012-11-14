package com.lge.euler;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.*;

import com.google.common.collect.*;

public class Problem54PokerGame {

	@Test
	public void readFromFile() {
		
	}
	@Test
	public void rankShouldBeStraightOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();

		startGame(player1, player2, "6C 4S 3C 2H 5S 7D 2S 5D 3S AC");
		
		assertEquals(Rank.STRAIGHT, player1.getRank());
		assertEquals(Rank.HIGH_CARD, player2.getRank());
	}

	@Test
	public void rankShouldBeFlushOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();

		startGame(player1, player2, "8C TC KC 9C 4C 7D 2S 5D 3S AC");

		assertEquals(Rank.FLUSH, player1.getRank());
		assertEquals(Rank.HIGH_CARD, player2.getRank());
		assertEquals(Rank.FLUSH, player1.getRank());
		assertEquals(Rank.HIGH_CARD, player2.getRank());
		assertEquals(true, player1.getRank().compareTo(player2.getRank()) > 0);
		
	}	

	@Test
	public void rankShouldBeFourOfACardOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();

		startGame(player1, player2, "4C 4H 5S 4S 4D AC 8C 9C 2C 8H");

		assertEquals(Rank.FOUR_OF_A_KIND, player1.getRank());
		assertEquals(Rank.ONE_PAIR, player2.getRank());
	}	
	
	@Test
	public void rankShouldBeTwoPairsOrOnePair() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		startGame(player1, player2, "4C 4H 5S 3S 3D AC 8C 9C 2C 8H");

		assertEquals(Rank.TWO_PAIR, player1.getRank());
		assertEquals(Rank.ONE_PAIR, player2.getRank());
	}	
	
	@Test
	public void rankShouldBeRoyalFlushOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		startGame(player1, player2, "AC TC QC JC KC AD QD JD TD 4D");

		assertEquals(Rank.ROYAL_FLUSH, player1.getRank());
		assertEquals(Rank.FLUSH, player2.getRank());
	}	
	
	@Test
	public void rankShouldBeStraightFlushOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		startGame(player1, player2, "4D 5D 6D 7D 8D 4C 5C 6C 7C 8D");
		
		assertEquals(Rank.STRAIGHT_FLUSH, player1.getRank());
		assertEquals(Rank.STRAIGHT, player2.getRank());		
	}	

	@Test
	public void rankShouldBeFullHouseFlushOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		startGame(player1, player2, "4C 4H 4S 3S 3D AC AH AD 2C 8H");
		
		assertEquals(Rank.FULL_HOUSE, player1.getRank());
		assertEquals(Rank.THREE_OF_A_KIND, player2.getRank());		
	}	

	@Test
	public void rankShouldBeThreeOfKindOrNot() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		
		startGame(player1, player2, "4C 4H 4S 3S 2D AC 8C 9C 2C 8H");
			
		assertEquals(Rank.THREE_OF_A_KIND, player1.getRank());
		assertEquals(Rank.ONE_PAIR, player2.getRank());		
	}	
	@Test
	public void rankShouldBeNotStraight() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		startGame(player1, player2, "KD 8S 9S 7C 2S 3S 6D 6S 4H KC");
		
		assertEquals(Rank.HIGH_CARD, player1.getRank());
		assertEquals(Rank.ONE_PAIR, player2.getRank());		
	}	
	
	@Test
	public void rankShouldBeNotStraight2() {
		PokerCards player1 = new PokerCards();
		PokerCards player2 = new PokerCards();
		startGame(player1, player2, "4D 8D 8S 6C 7C 6H 7H 8H 5C KC");
		
		assertEquals(Rank.ONE_PAIR, player1.getRank());		
		assertEquals(Rank.HIGH_CARD, player2.getRank());
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
			if (startGame(player1, player2, line))
				count++;
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
	
	
	class PokerCards implements Comparable<Object> {
		TreeMultimap<Num, Shape> cards = TreeMultimap.create();
		SortedMultiset<Num> rankCards = TreeMultiset.create();
		Rank rank = Rank.HIGH_CARD;
		
		public void addCard(String s) {
			cards.put(Num.get(s.charAt(0)), Shape.get(s.charAt(1)));
		}

		public void generateRank() {
			rank = rank();
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


		private Rank rank() {
			Rank rank = Rank.HIGH_CARD;
			
			rankCards.clear();
			if (isRoyalFlush()) {
				rank = Rank.ROYAL_FLUSH;
			} else if (isStraightFlush()) {
				rank = Rank.STRAIGHT_FLUSH;
			} else if (isFourCard()) {
				rankCards.add(getFourCard());
				rank = Rank.FOUR_OF_A_KIND;
			} else if (isFullHouse()) {
				rankCards.addAll(getFullHouse());
				rank = Rank.FULL_HOUSE;
			} else if (isFlush()) {
				rank = Rank.FLUSH;
			} else if (isStraight()) {
				rank = Rank.STRAIGHT;
			} else if (isThreeOfKind()) {
				rankCards.addAll(getThreeOfKind());
				rank = Rank.THREE_OF_A_KIND;
			} else if (pairs(cards) >= 2) {
				rankCards.addAll(getPairs());
				rank = Rank.TWO_PAIR;
			} else if (pairs(cards) == 1) {
				rankCards.addAll(getPairs());
				rank = Rank.ONE_PAIR;
			}

			return rank;	
		}
		

		private TreeMultiset<Num> getFullHouse() {
			TreeMultiset<Num> list = TreeMultiset.create();
			
			if (isThreeOfKind()) {
				Num threeCards = getThreeOfKind().firstEntry().getElement();
				list.add(threeCards);
				
				TreeMultimap<Num, Shape> rest = TreeMultimap.create(cards);
				rest.removeAll(threeCards);
				
				list.addAll(getPairs());
			}
			
			return list;
		}

		
		private boolean isFullHouse() {
			if (isThreeOfKind()) {
				Num threeCards = null;
				Multiset<Num> numbers = cards.keys();
				SortedSet<Num> numbersSet = cards.keySet();
				for(Num n : numbersSet) {
					if (numbers.count(n) == 3)
						threeCards = n;
				}
				
				TreeMultimap<Num, Shape> rest = TreeMultimap.create(cards);
				rest.removeAll(threeCards);
				return pairs(rest) >= 1;			
			}
			
			return false;
		}

		private TreeMultiset<Num> getPairs() {
			return getCardsCountN(2);
		}
		
		private TreeMultiset<Num> getThreeOfKind() {
			return getCardsCountN(3);
		}
		
		
		private TreeMultiset<Num> getCardsCountN(int targetCount) {
			TreeMultiset<Num> list = TreeMultiset.create();
			Multiset<Num> numbers = cards.keys();
			SortedSet<Num> numbersSet = cards.keySet();
			for(Num n : numbersSet) {
				if (numbers.count(n) == targetCount)
					list.add(n);
			}
			return list;
		}

		private boolean isThreeOfKind() {
			return !getThreeOfKind().isEmpty();
		}

		private boolean isStraightFlush() {
			if (isStraight()
					&& isFlush())
				return true;
			return false;
		}

		private boolean isRoyalFlush() {
			if (isStraight() 
					&& isFlush()
					&& getEndNumOfStraight() == Num.ACE)
				return true;
			return false;
		}
		
		private Num getFourCard() {
			Multiset<Num> numbers = cards.keys();
			for(Num n : numbers) {
				if (numbers.count(n) == 4)
					return n;
			}
			return null;
		}

		private Num getEndNumOfStraight() {
			Multiset<Num> numbers = cards.keys();
			Num result = null;

			int preOrdinal = -1;
			int count = 0;
			for(Num n : numbers) {
				if (preOrdinal >= 0 && (n.ordinal() - preOrdinal) != 1)
					count = 0;
				else
					count++;

				preOrdinal = n.ordinal();
				if(count >= 5)
					result = n;
			}
			
			return result;
		}
		
		private boolean isStraight() {
			return getEndNumOfStraight() != null;
		}	
		
		private boolean isFlush() {
			Multiset<Shape> shapes = Multimaps.invertFrom(cards, TreeMultimap.<Shape, Num> create()).keys();
			
			for(Shape s : shapes) {
				if (shapes.count(s) >= 5)
					return true;
			}
			return false;	
		}
		private boolean isFourCard() {
			return getFourCard() != null;	
		}	
		
		private int pairs(TreeMultimap<Num, Shape> cardset) {
			
			return countNcards(cardset, 2);
		}
		
		private int countNcards(TreeMultimap<Num, Shape> cardset, int targetCount) {
			int count = 0;
			Multiset<Num> numbers = cardset.keys();
			SortedSet<Num> numbersSet = cardset.keySet();
			for(Num n : numbersSet) {
				if (numbers.count(n) == targetCount)
					count++;
			}
			return count;
		}		
		
		@Override
		public int compareTo(Object o) throws ClassCastException, IllegalArgumentException {
			if (!(o instanceof PokerCards))
				throw new ClassCastException("A PokerCards object expected.");
			
			return compareTo((PokerCards)o);
		}
		
		private int compareTo(PokerCards o) throws IllegalArgumentException {
//			System.out.println("A:"+getRank().name() +" B:"+o.getRank().name());
			if (getRank().compareTo(o.getRank()) == 0) {
				Num[] myCards;
				Num[] yourCards;
				
				myCards = rankCards.toArray(new Num[0]);
				yourCards = o.rankCards.toArray(new Num[0]);
				for(int i=myCards.length-1; i>=0; i--) {
//					System.out.println("A:"+myCards[i].name() +" B:"+yourCards[i].name());
					int compared = myCards[i].compareTo(yourCards[i]);
					if (compared != 0)
						return compared;
				}
				myCards = cards.keySet().toArray(new Num[0]);
				yourCards = o.cards.keySet().toArray(new Num[0]);
				for(int i=myCards.length-1; i>=0; i--) {
//					System.out.println("A:"+myCards[i].name() +" B:"+yourCards[i].name());
					int compared = myCards[i].compareTo(yourCards[i]);
					if (compared != 0)
						return compared;
				}				
				
				throw new IllegalArgumentException("SAME RANKS");
			}
			return getRank().compareTo(o.getRank());			
			
		}
	}
}


