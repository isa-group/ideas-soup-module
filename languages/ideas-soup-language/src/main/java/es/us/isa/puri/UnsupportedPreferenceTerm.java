/**
 * 
 */
package es.us.isa.puri;

import es.us.isa.puri.model.PreferenceTerm;

/**
 * @author josemgarcia
 *
 */
public class UnsupportedPreferenceTerm extends Exception {

	/**
	 * UID version for serialization
	 */
	private static final long serialVersionUID = -4082295874078123298L;

	public UnsupportedPreferenceTerm (PreferenceTerm pref){
		super(pref.getRDFSClassURI() + " preference is not supported");
	}
}
