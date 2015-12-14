package es.us.isa.puri;

import org.ontoware.rdf2go.model.node.URI;
import java.util.Set;

import es.us.isa.puri.model.PreferenceTerm;

public interface RankingMechanism<E extends RankableItem> {

	Ranking<E> rank(Set<E> itemsToRank, PreferenceTerm preference) throws UnsupportedPreferenceTerm;
	
	boolean supportsPreferenceTerm(PreferenceTerm term);
	
	URI getMechanismURI();
}
