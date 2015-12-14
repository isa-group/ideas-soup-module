/**
 * 
 */
package es.us.isa.puri;

import java.util.List;
import java.util.Set;

import org.ontoware.rdf2go.model.node.URI;
import org.ontoware.rdfreactor.schema.rdfs.Resource;

import es.us.isa.puri.model.PreferenceTerm;
import es.us.isa.puri.ranking.Poset;

/**
 * @author josemgarcia
 *
 */
public abstract class AbstractRankingMechanism<E extends RankableItem> implements RankingMechanism<E> {
	
	protected static final String PURI_RANKING_NAMESPACE = "http://www.isa.us.es/puri/ranking";
	
	public abstract URI getMechanismURI();
	
	
	public Ranking<E> rank(Set<E> itemsToRank, PreferenceTerm preference)
	throws UnsupportedPreferenceTerm {
		if (!supportsPreferenceTerm(preference)) {
			throw new UnsupportedPreferenceTerm(preference);
		}
		
		Ranking<E> ranking = new Poset<E>();
		List<Resource> operands = preference.getAllPrefHasOperands_as().asList();
		StrictPartialOrderComparator<E> comparator = getComparator(operands);
		
		for (E item1 : itemsToRank) {
			for (E item2 : itemsToRank) {
				if (item1 != item2) {
					double rank = comparator.compare(item1, item2);
					ranking.addRank(item1, item2, rank);
				}
			}
		}
		
		return ranking;
	}
	
	protected abstract StrictPartialOrderComparator<E> getComparator(List<Resource> operands);

}
