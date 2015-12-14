package es.us.isa.puri.ranking;

import org.jgrapht.graph.DefaultWeightedEdge;

public class RankedEdge extends DefaultWeightedEdge {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5826398565314754496L;
	
	private boolean hasRankingValue = false;
	
	public boolean hasRankingValue () {
		return hasRankingValue;
	}
	
	public void setRankingValue (boolean rankingValue) {
		this.hasRankingValue = rankingValue;		
	}
}
