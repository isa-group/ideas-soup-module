package es.us.isa.puri.mechanism;


import java.lang.ref.WeakReference;
import java.util.HashMap;

import org.ontoware.rdf2go.model.node.URI;

import es.us.isa.puri.RankableItem;
import es.us.isa.puri.RankingMechanism;
import es.us.isa.puri.mechanism.impl.DefaultBalancedImpl;
import es.us.isa.puri.mechanism.impl.DefaultPrioritizedImpl;

public final class DefaultRankingMechanismFactory<E extends RankableItem> extends AbstractRankingMechanismFactory<RankableItem>{

	private static WeakReference<DefaultRankingMechanismFactory<RankableItem>> factory = new WeakReference<DefaultRankingMechanismFactory<RankableItem>>(null);
	
	private DefaultRankingMechanismFactory() {
		mechanisms = new HashMap<URI, Class<RankingMechanism<RankableItem>>>();
		compositeMechanisms = new HashMap<URI, Class<RankingMechanism<RankableItem>>>();
		initializeDefaultMechanisms();
	}
	
	public static DefaultRankingMechanismFactory<RankableItem> getInstance() {
		DefaultRankingMechanismFactory<RankableItem> m = factory.get();
		if( m != null) {
			return m;
		}

	    synchronized (DefaultRankingMechanismFactory.class) {
	         m = factory.get();
	         if( m != null) {
	        	 return m;
	         }
	         m = new DefaultRankingMechanismFactory<RankableItem>();
	         factory = new WeakReference<DefaultRankingMechanismFactory<RankableItem>>(m);
	      }	

	      return m;
	}

	@Override
	protected void initializeDefaultMechanisms() {
		registerCompositeMechanism(new DefaultBalancedImpl<RankableItem>(this));
		registerCompositeMechanism(new DefaultPrioritizedImpl<RankableItem>(this));
	}

	@Override
	protected RankingMechanismFactory<RankableItem> getFactory() {
		return factory.get();
	}
	
}
