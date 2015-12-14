/**
 * 
 */
package es.us.isa.puri.ranking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jgrapht.experimental.dag.DirectedAcyclicGraph;

import es.us.isa.puri.RankableItem;
import es.us.isa.puri.Ranking;

/**
 * @author josemgarcia
 *
 */
public class Poset<E extends RankableItem> implements Ranking<E> {

	private DirectedAcyclicGraph<E, RankedEdge> dag;
	
	public Poset() {
		dag = new DirectedAcyclicGraph<E, RankedEdge>(RankedEdge.class);
	}
	
	
	public Iterator<E> iterator() {
		return dag.iterator();
	}

	
	public List<E> getResultsAsList() {
		List<E> results = new ArrayList<E>();
		Iterator<E> it = dag.iterator();
		while (it.hasNext()) {
			E item = it.next();
			results.add(item);
		}
		return results;
	}

	
	public boolean addRank(E betterItem, E worseItem) {
		dag.addVertex(betterItem);
		dag.addVertex(worseItem);
		RankedEdge newEdge = dag.addEdge(betterItem, worseItem);
		return newEdge != null;
	}
	
	
	public boolean addRank(E betterItem, E worseItem, double difference) {
		RankedEdge newEdge = null;
		
		//add vertices if necessary
		dag.addVertex(betterItem);
		dag.addVertex(worseItem);
			
		//change direction
		if (difference < 0) {
			return addRank(worseItem, betterItem, -difference);
		}
		
		//normal behaviour or previously changed
		if (difference > 0) {
			newEdge = dag.addEdge(betterItem, worseItem);
			if (newEdge != null) {
				dag.setEdgeWeight(newEdge, difference);
				newEdge.setRankingValue(true);
			}
		}
		
		//difference==0 => no need to add an edge
		return newEdge != null;
	}

	
	public boolean removeRank(E betterItem, E worseItem) {
		RankedEdge removedEdge = dag.removeEdge(betterItem, worseItem);
		return removedEdge != null;
	}

	
	public double getRank(E item1, E item2) {
		RankedEdge edge = dag.getEdge(item1, item2);
		boolean inverse = false;
		double rank = 0.0D;
		
		//if not found try the inverse
		if (edge == null) {
			edge = dag.getEdge(item2, item1);
			inverse = true;
		}
		
		if (edge != null) {
			if (edge.hasRankingValue()) {
				rank = dag.getEdgeWeight(edge);
			} else {
				rank = 1.0D;
			}
		}
		
		if (inverse) {
			rank = -rank;
		}
		
		return rank;
	}

	
	public boolean isBetterThan(E item1, E item2) {
		return dag.containsEdge(item1, item2);
	}

	
	public boolean addItem(E rankableItem) {
		return dag.addVertex(rankableItem);
	}

	
	public boolean removeItem(E rankableItem) {
		return dag.removeVertex(rankableItem);
	}

}
