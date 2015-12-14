package es.us.isa.puri.mechanism.impl;

import java.util.Set;

import org.ontoware.rdf2go.model.node.URI;
import org.ontoware.rdf2go.model.node.impl.URIImpl;
import es.us.isa.cloudServices.ComputationService;
import es.us.isa.puri.Ranking;
import es.us.isa.puri.RankingMechanism;
import es.us.isa.puri.StrictPartialOrderComparator;
import es.us.isa.puri.UnsupportedPreferenceTerm;
import es.us.isa.puri.model.DislikesPreference;
import es.us.isa.puri.model.PreferenceTerm;
import es.us.isa.puri.ranking.Poset;

public class DefaultDislikesImpl implements RankingMechanism<ComputationService>{
	
	public static final String MECHANISM = "http://www.isa.us.es/preferences#DefaultDislikesImpl";
	
	public DefaultDislikesImpl() {
		
	}
	@Override
	public Ranking<ComputationService> rank(Set<ComputationService> itemsToRank, PreferenceTerm preference) throws UnsupportedPreferenceTerm {
		
		if (!supportsPreferenceTerm(preference)) {
			throw new UnsupportedPreferenceTerm(preference);
		}
		Ranking<ComputationService> ranking = new Poset<ComputationService>();
		StrictPartialOrderComparator<ComputationService> comparator = getComparator(preference);
		
		for (ComputationService item1 : itemsToRank) {
			for (ComputationService item2 : itemsToRank) {
				if (item1 != item2) {
					double rank = comparator.compare(item1, item2);
					ranking.addRank(item1, item2, rank);
				}
			}
		}
		return ranking;
	}

	public boolean supportsPreferenceTerm(PreferenceTerm pref) {
		boolean supported = false;
		if (pref.isInstanceof(DislikesPreference.RDFS_CLASS)) {
			URI mechanismUri = pref.getAllPrefHasRankingMechanism_as().firstValue().asURI();
			if (mechanismUri.equals((getMechanismURI()))) {
				supported = true;
			}
		}
		return supported;
	}
	
	public URI getMechanismURI() {
		return new URIImpl(MECHANISM, false);
	}
	
	protected StrictPartialOrderComparator<ComputationService> getComparator(PreferenceTerm preference) {
		return new DefaultDislikesComparator(preference);
	}
	
	private class DefaultDislikesComparator implements StrictPartialOrderComparator<ComputationService> {
		
		String refersTo;
		String favors;
		
		DefaultDislikesComparator(PreferenceTerm preference){
			refersTo=preference.getAllPrefRefersTo().next().asURI().toString();
			DislikesPreference favoritesPref = (DislikesPreference) preference;
			favors=favoritesPref.getAllPrefHasDislikes().next().asURI().toString();
		}
		public boolean areComparable(ComputationService o1, ComputationService o2) {
			return true;
		}
		
		public double compare(ComputationService o1, ComputationService o2) {
			
			double result = 0.0D;
			
			if(refersTo.equals("http://www.example.org#hasIOPerformance")){
				Boolean o1Favors = o1.getHasIOPerformance().toString().equals(favors);
				Boolean o2Favors = o2.getHasIOPerformance().toString().equals(favors);
				if(!o1Favors)
					if(o2Favors)
						result= 1;
			}
			return result;
		}
	}
}
