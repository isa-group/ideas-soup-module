package es.us.isa.puri.mechanism;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.ontoware.rdf2go.model.node.URI;
import es.us.isa.cloudServices.ComputationService;
import es.us.isa.puri.RankableItem;
import es.us.isa.puri.RankingMechanism;
import es.us.isa.puri.mechanism.impl.DefaultBalancedImpl;
import es.us.isa.puri.mechanism.impl.DefaultDislikesImpl;
import es.us.isa.puri.mechanism.impl.DefaultFavoritesImpl;
import es.us.isa.puri.mechanism.impl.DefaultHighestImpl;
import es.us.isa.puri.mechanism.impl.DefaultLowestImpl;
import es.us.isa.puri.mechanism.impl.DefaultPrioritizedImpl;

public final class SOUPRankingMechanismFactory extends AbstractRankingMechanismFactory<ComputationService>{

	private static WeakReference<SOUPRankingMechanismFactory> factory = new WeakReference<SOUPRankingMechanismFactory>(null);
	
	private SOUPRankingMechanismFactory() {
		mechanisms = new HashMap<URI, Class<RankingMechanism<ComputationService>>>();
		compositeMechanisms = new HashMap<URI, Class<RankingMechanism<ComputationService>>>();
		initializeDefaultMechanisms();
	}
	
	public static SOUPRankingMechanismFactory getInstance() {
		SOUPRankingMechanismFactory m = factory.get();
		if( m != null) {
			return m;
		}

	    synchronized (SOUPRankingMechanismFactory.class) {
	         m = factory.get();
	         if( m != null) {
	        	 return m;
	         }
	         m = new SOUPRankingMechanismFactory();
	         factory = new WeakReference<SOUPRankingMechanismFactory>(m);
	      }	

	      return m;
	}

	@Override
	protected void initializeDefaultMechanisms() {
		
		registerCompositeMechanism(new DefaultBalancedImpl<ComputationService>(this));
		registerCompositeMechanism(new DefaultPrioritizedImpl<ComputationService>(this));
		registerMechanism(new DefaultHighestImpl());
		registerMechanism(new DefaultLowestImpl());
		registerMechanism(new DefaultFavoritesImpl());
		registerMechanism(new DefaultDislikesImpl());
	}

	@Override
	protected RankingMechanismFactory<ComputationService> getFactory() {
		return factory.get();
	}
}
