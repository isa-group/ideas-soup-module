/**
 * 
 */
package es.us.isa.puri.mechanism.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ontoware.aifbcommons.collection.ClosableIterator;
import org.ontoware.rdf2go.model.node.URI;
import org.ontoware.rdf2go.model.node.impl.URIImpl;
import org.ontoware.rdfreactor.schema.rdfs.Resource;

import es.us.isa.puri.AbstractRankingMechanism;
import es.us.isa.puri.RankableItem;
import es.us.isa.puri.Ranking;
import es.us.isa.puri.StrictPartialOrderComparator;
import es.us.isa.puri.UnsupportedPreferenceTerm;
import es.us.isa.puri.mechanism.RankingMechanismFactory;
import es.us.isa.puri.model.BalancedPreference;
import es.us.isa.puri.model.PreferenceTerm;
import es.us.isa.puri.model.RankingMechanism;
import es.us.isa.puri.ranking.Poset;

/**
 * @author josemgarcia
 *
 */
public class DefaultBalancedImpl<E extends RankableItem> extends AbstractRankingMechanism<E> {
	
	public static final String MECHANISM = PURI_RANKING_NAMESPACE + "#DefaultBalancedImpl";
	
	private RankingMechanismFactory<RankableItem> factory;
	
	@SuppressWarnings("unchecked")
	public DefaultBalancedImpl(RankingMechanismFactory<E> factory) {
		this.factory = (RankingMechanismFactory<RankableItem>) factory;		
	}

	
	protected StrictPartialOrderComparator<E> getComparator(List<Resource> operands) {
		return new DefaultBalancedComparator(operands);
	}
	

	public boolean supportsPreferenceTerm(PreferenceTerm term) {
		boolean supported = false;
		if (term.isInstanceof(BalancedPreference.RDFS_CLASS)) {
			ClosableIterator<RankingMechanism> it = term.getAllPrefHasRankingMechanism();
			while (it.hasNext()) {
				RankingMechanism rm = it.next();
				if (rm.asURI().equals(getMechanismURI())) {
					supported = true;
				}
			}
		}
		
		return supported;
	}

	
	public URI getMechanismURI() {
		return new URIImpl(MECHANISM, false);
	}
	
	private class DefaultBalancedComparator implements StrictPartialOrderComparator<E> {
		
		private Map<PreferenceTerm,Ranking<RankableItem>> rankingByOperand;
		
		public DefaultBalancedComparator(List<Resource> operands) {
			rankingByOperand = new HashMap<PreferenceTerm, Ranking<RankableItem>>(operands.size());
			for (Resource r : operands) {
				rankingByOperand.put((PreferenceTerm) r.castTo(PreferenceTerm.class), new Poset<RankableItem>());
			}
		}

		
		public boolean areComparable(E o1, E o2) {
			// TODO check if they are comparable
			return true;
		}

		
		public double compare(E o1, E o2) {
			initializeRankingsByOperand(o1, o2);
			double result = doCompare(o1, o2);
			//try the inverse comparison if o1 is not better than o2
			return result!=0.0D ? result : -doCompare(o2, o1);
		}
		
		private double doCompare(E o1, E o2) {
			double result = 0.0D;
			
			Iterator<Ranking<RankableItem>> rankings = rankingByOperand.values().iterator();
			while (result == 0.0D && rankings.hasNext()) {
				Ranking<RankableItem> ranking = rankings.next();
				if (ranking.isBetterThan(o1, o2)) {
					boolean isComparableByBalanced = true;
					Iterator<Ranking<RankableItem>> rest = rankingByOperand.values().iterator();
					while (isComparableByBalanced && rest.hasNext()) {
						Ranking<RankableItem> otherRanking = rest.next();
						if (!otherRanking.equals(ranking) && otherRanking.getRank(o1, o2)<0)
							isComparableByBalanced = false;
					}
					if (isComparableByBalanced) {
						result = 1.0D; //o1 is better than o2
					}
				}
			}
			
			return result;
		}

		private void initializeRankingsByOperand(E o1, E o2) {
			Set<RankableItem> items = new HashSet<RankableItem>();
			items.add(o1);
			items.add(o2);
			for (PreferenceTerm pref : rankingByOperand.keySet()) {
				URI rmURI = pref.getAllPrefHasRankingMechanism_as().firstValue().asURI();
				es.us.isa.puri.RankingMechanism<RankableItem> rm = factory.create(rmURI);
				try {
					Ranking<RankableItem> ranking = rm.rank(items, pref);
					rankingByOperand.put(pref, ranking);
				} catch (UnsupportedPreferenceTerm e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
