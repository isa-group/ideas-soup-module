package es.us.isa.puri.mechanism;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.ontoware.rdf2go.model.node.URI;

import es.us.isa.puri.RankableItem;
import es.us.isa.puri.RankingMechanism;

public abstract class AbstractRankingMechanismFactory<E extends RankableItem> implements RankingMechanismFactory<E> {

	protected Map<URI, Class<RankingMechanism<E>>> mechanisms;
	protected Map<URI, Class<RankingMechanism<E>>> compositeMechanisms;
	
	protected abstract RankingMechanismFactory<E> getFactory();
	
	protected abstract void initializeDefaultMechanisms();

	public RankingMechanism<E> create(URI uri) {
		if (mechanisms.containsKey(uri)) {
			try {
				Class<RankingMechanism<E>> clase=mechanisms.get(uri);
				return clase.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (compositeMechanisms.containsKey(uri)) {
			try {
				Constructor<RankingMechanism<E>> constr = compositeMechanisms.get(uri).getConstructor(RankingMechanismFactory.class);
				return constr.newInstance(getFactory());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void registerMechanism(RankingMechanism<E> mechanism) {
		if (!mechanisms.containsKey(mechanism)) {
			mechanisms.put(mechanism.getMechanismURI(), (Class<RankingMechanism<E>>) mechanism.getClass());
		}
	}
	
	public void unregisterMechanism(RankingMechanism<E> mechanism) {
		if (mechanisms.containsKey(mechanism)) { 
			mechanisms.remove(mechanism.getMechanismURI());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void registerCompositeMechanism(RankingMechanism<E> mechanism) {
		if (!compositeMechanisms.containsKey(mechanism)) {
			compositeMechanisms.put(mechanism.getMechanismURI(), (Class<RankingMechanism<E>>) mechanism.getClass());
		}
	}
	
	public void unregisterCompositeMechanism(RankingMechanism<E> mechanism) {
		if (compositeMechanisms.containsKey(mechanism)) { 
			compositeMechanisms.remove(mechanism.getMechanismURI());
		}
	}
}
