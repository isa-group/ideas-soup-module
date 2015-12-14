/**
 * 
 */
package es.us.isa.puri.mechanism.impl;

import java.util.ArrayList;
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
import es.us.isa.puri.model.PreferenceTerm;
import es.us.isa.puri.model.PrioritizedPreference;
import es.us.isa.puri.model.RankingMechanism;

/**
 * @author josemgarcia
 *
 */
public class DefaultPrioritizedImpl<E extends RankableItem> extends AbstractRankingMechanism<E> {
	
	public static final String MECHANISM = PURI_RANKING_NAMESPACE + "#DefaultPrioritizedImpl";
	private RankingMechanismFactory<RankableItem> factory;
	
	@SuppressWarnings("unchecked")
	public DefaultPrioritizedImpl(RankingMechanismFactory<E> factory) {
		this.factory = (RankingMechanismFactory<RankableItem>) factory;		
	}
	
	
	protected StrictPartialOrderComparator<E> getComparator(List<Resource> operands) {
		return new DefaultPrioritizedComparator(operands);
	}

	
	public boolean supportsPreferenceTerm(PreferenceTerm term) {
		boolean supported = false;
		if (term.isInstanceof(PrioritizedPreference.RDFS_CLASS)) {
			ClosableIterator<RankingMechanism> it = term.getAllPrefHasRankingMechanism();
			while (it.hasNext()) {
				RankingMechanism rm = it.next();
				if (rm.asURI().equals((getMechanismURI()))) {
					supported = true;
				}
			}
		}
		
		return supported;
	}

	
	public URI getMechanismURI() {
		return new URIImpl(MECHANISM, false);
	}
	
	private class DefaultPrioritizedComparator implements StrictPartialOrderComparator<E> {
		
		private Map<PreferenceTerm,Ranking<RankableItem>> rankingByOperand;
		private List<PreferenceTerm> operands;
		
		public DefaultPrioritizedComparator(List<Resource> operands) {
			rankingByOperand = new HashMap<PreferenceTerm, Ranking<RankableItem>>(operands.size());
			this.operands = new ArrayList<PreferenceTerm>(operands.size());
			for (Resource r : operands) {
				PreferenceTerm pref = (PreferenceTerm) r.castTo(PreferenceTerm.class);
				this.operands.add(pref);
				rankingByOperand.put(pref, null);
			}
		}

		
		public boolean areComparable(E o1, E o2) {
			// TODO check if they are comparable
			return true;
		}

		
		public double compare(E o1, E o2) {
			double result = 0.0D;
			
			Iterator<PreferenceTerm> preferences = operands.iterator();
			while (result == 0.0D && preferences.hasNext()) {
				PreferenceTerm pref = preferences.next();
				Ranking<RankableItem> ranking = getRanking(o1, o2, pref);
				result = ranking.getRank(o1, o2);
			}
			
			return result;
		}
		
		private Ranking<RankableItem> getRanking(E o1, E o2, PreferenceTerm pref) {
			Set<RankableItem> items = new HashSet<RankableItem>();
			items.add(o1);
			items.add(o2);
			URI rmURI = pref.getAllPrefHasRankingMechanism_as().firstValue().asURI();
			es.us.isa.puri.RankingMechanism<RankableItem> rm = factory.create(rmURI);
			try {
				Ranking<RankableItem> ranking = rm.rank(items, pref);
				rankingByOperand.put(pref, ranking);
			} catch (UnsupportedPreferenceTerm e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rankingByOperand.get(pref);
		}
		
	}


}
