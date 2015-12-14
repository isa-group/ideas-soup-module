package es.us.isa.puri.mechanism.impl;

import java.util.Set;

import org.ontoware.rdf2go.model.node.URI;
import org.ontoware.rdf2go.model.node.impl.URIImpl;
import es.us.isa.cloudServices.ComputationService;
import es.us.isa.puri.Ranking;
import es.us.isa.puri.RankingMechanism;
import es.us.isa.puri.StrictPartialOrderComparator;
import es.us.isa.puri.UnsupportedPreferenceTerm;
import es.us.isa.puri.model.HighestPreference;
import es.us.isa.puri.model.PreferenceTerm;
import es.us.isa.puri.ranking.Poset;

public class DefaultHighestImpl implements RankingMechanism<ComputationService>{
	
	public static final String MECHANISM = "http://www.isa.us.es/preferences#DefaultHighestImpl";
	
	public DefaultHighestImpl() {
		
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
		if (pref.isInstanceof(HighestPreference.RDFS_CLASS)) {
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
		return new DefaultHighestComparator(preference);
	}
	
	private class DefaultHighestComparator implements StrictPartialOrderComparator<ComputationService> {
		
		String refersTo;
		
		DefaultHighestComparator(PreferenceTerm preference){
			refersTo=preference.getAllPrefRefersTo().next().asURI().toString();
		}
		public boolean areComparable(ComputationService o1, ComputationService o2) {
			return true;
		}
		
		public double compare(ComputationService o1, ComputationService o2) {
			
			double result = 0.0D;
			
			if(refersTo.equals("http://www.example.org#hasComputingPerformance"))
				result=o1.getHasComputingPerformance().getHasValueFloat() - o2.getHasComputingPerformance().getHasValueFloat();
			else if(refersTo.equals("http://www.example.org#hasInternalStorage"))
				result=o1.getHasInternalStorage().getHasValueFloat() - o2.getHasInternalStorage().getHasValueFloat();
			else if(refersTo.equals("http://www.example.org#hasMemory"))
				result= o1.getHasMemory().getHasValueFloat() - o2.getHasMemory().getHasValueFloat();	
			else if(refersTo.equals("http://www.example.org#hasVirtualCores"))
				result=o1.getHasVirtualCores().getHasValueInteger() - o2.getHasVirtualCores().getHasValueInteger();
	
			return result;
		}
	}
}
