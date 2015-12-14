package es.us.isa.puri;

import java.util.List;

public interface Ranking<E extends RankableItem> extends Iterable<E>{

	List<E> getResultsAsList();
	
	boolean addItem(E rankableItem);
	boolean removeItem(E rankableItem);
	
	boolean addRank(E betterItem, E worseItem);
	boolean addRank(E betterItem, E worseItem, double difference);
	boolean removeRank(E betterItem, E worseItem);
	double getRank(E item1, E item2);
	boolean isBetterThan (E item1, E item2);
	
}
