package test;

import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class FlatMap {

	private static List<Card> newDeck() {
		return Stream.of(Suit.values()).
				flatMap(suit -> Stream.of(Rank.values()).map(rank -> new Card(suit, rank)))
				.collect(toList());
	}
	
}

class Suit
{
	static List<Suit> values(){
		return null;
		
	}
}


class Rank
{
	static List<Rank> values(){
		return null;
	}
}



class Card
{
	public Card( List<Suit> suit,List<Rank> rank ) {
		
	}

}