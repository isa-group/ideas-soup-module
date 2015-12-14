package es.us.isa.puri.mechanism;

import org.ontoware.rdf2go.model.node.URI;

import es.us.isa.puri.RankableItem;
import es.us.isa.puri.RankingMechanism;

public interface RankingMechanismFactory<E extends RankableItem> {
	RankingMechanism<E> create(URI uri);
}
