/**
 * generated by http://RDFReactor.semweb4j.org ($Id: CodeGenerator.java 1535 2008-09-09 15:44:46Z max.at.xam.de $) on 4/10/15 20:45
 */
package es.us.isa.puri.model;

import org.ontoware.aifbcommons.collection.ClosableIterator;
import org.ontoware.rdf2go.exception.ModelRuntimeException;
import org.ontoware.rdf2go.model.Model;
import org.ontoware.rdf2go.model.node.BlankNode;
import org.ontoware.rdf2go.model.node.URI;
import org.ontoware.rdf2go.model.node.impl.URIImpl;
import org.ontoware.rdfreactor.runtime.Base;
import org.ontoware.rdfreactor.runtime.ReactorResult;

/**
 * This class manages access to these properties:
 * <ul>
 *   <li> HasScoringFunction </li>
 * </ul>
 *
 * This class was generated by <a href="http://RDFReactor.semweb4j.org">RDFReactor</a> on 4/10/15 20:45
 */
public class ScorePreference extends QuantitativeAtomicPreference {

    /** http://www.isa.us.es/preferences#ScorePreference */
    @SuppressWarnings("hiding")
	public static final URI RDFS_CLASS = new URIImpl("http://www.isa.us.es/preferences#ScorePreference", false);

    /** http://www.isa.us.es/preferences#hasScoringFunction */
    @SuppressWarnings("hiding")
	public static final URI HASSCORINGFUNCTION = new URIImpl("http://www.isa.us.es/preferences#hasScoringFunction",false);

    /** 
     * All property-URIs with this class as domain.
     * All properties of all super-classes are also available. 
     */
    @SuppressWarnings("hiding")
    public static final URI[] MANAGED_URIS = {
      new URIImpl("http://www.isa.us.es/preferences#hasScoringFunction",false) 
    };


	// protected constructors needed for inheritance
	
	/**
	 * Returns a Java wrapper over an RDF object, identified by URI.
	 * Creating two wrappers for the same instanceURI is legal.
	 * @param model RDF2GO Model implementation, see http://rdf2go.semweb4j.org
	 * @param classURI URI of RDFS class
	 * @param instanceIdentifier Resource that identifies this instance
	 * @param write if true, the statement (this, rdf:type, TYPE) is written to the model
	 *
	 * [Generated from RDFReactor template rule #c1] 
	 */
	protected ScorePreference ( Model model, URI classURI, org.ontoware.rdf2go.model.node.Resource instanceIdentifier, boolean write ) {
		super(model, classURI, instanceIdentifier, write);
	}

	// public constructors

	/**
	 * Returns a Java wrapper over an RDF object, identified by URI.
	 * Creating two wrappers for the same instanceURI is legal.
	 * @param model RDF2GO Model implementation, see http://rdf2go.ontoware.org
	 * @param instanceIdentifier an RDF2Go Resource identifying this instance
	 * @param write if true, the statement (this, rdf:type, TYPE) is written to the model
	 *
	 * [Generated from RDFReactor template rule #c2] 
	 */
	public ScorePreference ( Model model, org.ontoware.rdf2go.model.node.Resource instanceIdentifier, boolean write ) {
		super(model, RDFS_CLASS, instanceIdentifier, write);
	}


	/**
	 * Returns a Java wrapper over an RDF object, identified by a URI, given as a String.
	 * Creating two wrappers for the same URI is legal.
	 * @param model RDF2GO Model implementation, see http://rdf2go.ontoware.org
	 * @param uriString a URI given as a String
	 * @param write if true, the statement (this, rdf:type, TYPE) is written to the model
	 * @throws ModelRuntimeException if URI syntax is wrong
	 *
	 * [Generated from RDFReactor template rule #c7] 
	 */
	public ScorePreference ( Model model, String uriString, boolean write) throws ModelRuntimeException {
		super(model, RDFS_CLASS, new URIImpl(uriString,false), write);
	}

	/**
	 * Returns a Java wrapper over an RDF object, identified by a blank node.
	 * Creating two wrappers for the same blank node is legal.
	 * @param model RDF2GO Model implementation, see http://rdf2go.ontoware.org
	 * @param bnode BlankNode of this instance
	 * @param write if true, the statement (this, rdf:type, TYPE) is written to the model
	 *
	 * [Generated from RDFReactor template rule #c8] 
	 */
	public ScorePreference ( Model model, BlankNode bnode, boolean write ) {
		super(model, RDFS_CLASS, bnode, write);
	}

	/**
	 * Returns a Java wrapper over an RDF object, identified by 
	 * a randomly generated URI.
	 * Creating two wrappers results in different URIs.
	 * @param model RDF2GO Model implementation, see http://rdf2go.ontoware.org
	 * @param write if true, the statement (this, rdf:type, TYPE) is written to the model
	 *
	 * [Generated from RDFReactor template rule #c9] 
	 */
	public ScorePreference ( Model model, boolean write ) {
		super(model, RDFS_CLASS, model.newRandomUniqueURI(), write);
	}

    ///////////////////////////////////////////////////////////////////
    // typing

	/**
	 * Return an existing instance of this class in the model. No statements are written.
	 * @param model an RDF2Go model
	 * @param instanceResource an RDF2Go resource
	 * @return an instance of ScorePreference  or null if none existst
	 *
	 * [Generated from RDFReactor template rule #class0] 
	 */
	public static ScorePreference  getInstance(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.getInstance(model, instanceResource, ScorePreference.class);
	}

	/**
	 * Create a new instance of this class in the model. 
	 * That is, create the statement (instanceResource, RDF.type, http://www.isa.us.es/preferences#ScorePreference).
	 * @param model an RDF2Go model
	 * @param instanceResource an RDF2Go resource
	 *
	 * [Generated from RDFReactor template rule #class1] 
	 */
	public static void createInstance(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		Base.createInstance(model, RDFS_CLASS, instanceResource);
	}

	/**
	 * @param model an RDF2Go model
	 * @param instanceResource an RDF2Go resource
	 * @return true if instanceResource is an instance of this class in the model
	 *
	 * [Generated from RDFReactor template rule #class2] 
	 */
	public static boolean hasInstance(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.hasInstance(model, RDFS_CLASS, instanceResource);
	}

	/**
	 * @param model an RDF2Go model
	 * @return all instances of this class in Model 'model' as RDF resources
	 *
	 * [Generated from RDFReactor template rule #class3] 
	 */
	public static ClosableIterator<org.ontoware.rdf2go.model.node.Resource> getAllInstances(Model model) {
		return Base.getAllInstances(model, RDFS_CLASS, org.ontoware.rdf2go.model.node.Resource.class);
	}

	/**
	 * @param model an RDF2Go model
	 * @return all instances of this class in Model 'model' as a ReactorResult,
	 * which can conveniently be converted to iterator, list or array.
	 *
	 * [Generated from RDFReactor template rule #class3-as] 
	 */
	public static ReactorResult<? extends ScorePreference> getAllInstances_as(Model model) {
		return Base.getAllInstances_as(model, RDFS_CLASS, ScorePreference.class );
	}

    /**
	 * Remove rdf:type ScorePreference from this instance. Other triples are not affected.
	 * To delete more, use deleteAllProperties
	 * @param model an RDF2Go model
	 * @param instanceResource an RDF2Go resource
	 *
	 * [Generated from RDFReactor template rule #class4] 
	 */
	public static void deleteInstance(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		Base.deleteInstance(model, RDFS_CLASS, instanceResource);
	}

	/**
	 * Delete all (this, *, *), i.e. including rdf:type
	 * @param model an RDF2Go model
	 * @param resource
	 */
	public static void deleteAllProperties(Model model,	org.ontoware.rdf2go.model.node.Resource instanceResource) {
		Base.deleteAllProperties(model, instanceResource);
	}

    ///////////////////////////////////////////////////////////////////
    // property access methods


    /**
     * Check if org.ontoware.rdfreactor.generator.java.JProperty@4926cac6 has at least one value set 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
     * @return true if this property has at least one value
	 *
	 * [Generated from RDFReactor template rule #get0has-static] 
     */
	public static boolean hasPrefHasScoringFunction(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.has(model, instanceResource, HASSCORINGFUNCTION);
	}

    /**
     * Check if org.ontoware.rdfreactor.generator.java.JProperty@4926cac6 has at least one value set 
     * @return true if this property has at least one value
	 *
	 * [Generated from RDFReactor template rule #get0has-dynamic] 
     */
	public boolean hasPrefHasScoringFunction() {
		return Base.has(this.model, this.getResource(), HASSCORINGFUNCTION);
	}

    /**
     * Check if org.ontoware.rdfreactor.generator.java.JProperty@4926cac6 has the given value (maybe among other values).  
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be checked
     * @return true if this property contains (maybe among other) the given value
	 *
	 * [Generated from RDFReactor template rule #get0has-value-static] 
     */
	public static boolean hasPrefHasScoringFunction(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, org.ontoware.rdf2go.model.node.Node value ) {
		return Base.hasValue(model, instanceResource, HASSCORINGFUNCTION);
	}

    /**
     * Check if org.ontoware.rdfreactor.generator.java.JProperty@4926cac6 has the given value (maybe among other values).  
	 * @param value the value to be checked
     * @return true if this property contains (maybe among other) the given value
	 *
	 * [Generated from RDFReactor template rule #get0has-value-dynamic] 
     */
	public boolean hasPrefHasScoringFunction( org.ontoware.rdf2go.model.node.Node value ) {
		return Base.hasValue(this.model, this.getResource(), HASSCORINGFUNCTION);
	}

     /**
     * Get all values of property HasScoringFunction as an Iterator over RDF2Go nodes 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
     * @return a ClosableIterator of RDF2Go Nodes
	 *
	 * [Generated from RDFReactor template rule #get7static] 
     */
	public static ClosableIterator<org.ontoware.rdf2go.model.node.Node> getAllPrefHasScoringFunction_asNode(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.getAll_asNode(model, instanceResource, HASSCORINGFUNCTION);
	}
	
    /**
     * Get all values of property HasScoringFunction as a ReactorResult of RDF2Go nodes 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
     * @return a List of RDF2Go Nodes
	 *
	 * [Generated from RDFReactor template rule #get7static-reactor-result] 
     */
	public static ReactorResult<org.ontoware.rdf2go.model.node.Node> getAllPrefHasScoringFunction_asNode_(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.getAll_as(model, instanceResource, HASSCORINGFUNCTION, org.ontoware.rdf2go.model.node.Node.class);
	}

    /**
     * Get all values of property HasScoringFunction as an Iterator over RDF2Go nodes 
     * @return a ClosableIterator of RDF2Go Nodes
	 *
	 * [Generated from RDFReactor template rule #get8dynamic] 
     */
	public ClosableIterator<org.ontoware.rdf2go.model.node.Node> getAllPrefHasScoringFunction_asNode() {
		return Base.getAll_asNode(this.model, this.getResource(), HASSCORINGFUNCTION);
	}

    /**
     * Get all values of property HasScoringFunction as a ReactorResult of RDF2Go nodes 
     * @return a List of RDF2Go Nodes
	 *
	 * [Generated from RDFReactor template rule #get8dynamic-reactor-result] 
     */
	public ReactorResult<org.ontoware.rdf2go.model.node.Node> getAllPrefHasScoringFunction_asNode_() {
		return Base.getAll_as(this.model, this.getResource(), HASSCORINGFUNCTION, org.ontoware.rdf2go.model.node.Node.class);
	}
     /**
     * Get all values of property HasScoringFunction     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
     * @return a ClosableIterator of $type
	 *
	 * [Generated from RDFReactor template rule #get11static] 
     */
	public static ClosableIterator<RealFunction> getAllPrefHasScoringFunction(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.getAll(model, instanceResource, HASSCORINGFUNCTION, RealFunction.class);
	}
	
    /**
     * Get all values of property HasScoringFunction as a ReactorResult of RealFunction 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
     * @return a ReactorResult of $type which can conveniently be converted to iterator, list or array
	 *
	 * [Generated from RDFReactor template rule #get11static-reactorresult] 
     */
	public static ReactorResult<RealFunction> getAllPrefHasScoringFunction_as(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		return Base.getAll_as(model, instanceResource, HASSCORINGFUNCTION, RealFunction.class);
	}

    /**
     * Get all values of property HasScoringFunction     * @return a ClosableIterator of $type
	 *
	 * [Generated from RDFReactor template rule #get12dynamic] 
     */
	public ClosableIterator<RealFunction> getAllPrefHasScoringFunction() {
		return Base.getAll(this.model, this.getResource(), HASSCORINGFUNCTION, RealFunction.class);
	}

    /**
     * Get all values of property HasScoringFunction as a ReactorResult of RealFunction 
     * @return a ReactorResult of $type which can conveniently be converted to iterator, list or array
	 *
	 * [Generated from RDFReactor template rule #get12dynamic-reactorresult] 
     */
	public ReactorResult<RealFunction> getAllPrefHasScoringFunction_as() {
		return Base.getAll_as(this.model, this.getResource(), HASSCORINGFUNCTION, RealFunction.class);
	}
 
    /**
     * Adds a value to property HasScoringFunction as an RDF2Go node 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be added
	 *
	 * [Generated from RDFReactor template rule #add1static] 
     */
	public static void addPrefHasScoringFunction( Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, org.ontoware.rdf2go.model.node.Node value) {
		Base.add(model, instanceResource, HASSCORINGFUNCTION, value);
	}
	
    /**
     * Adds a value to property HasScoringFunction as an RDF2Go node 
	 * @param value the value to be added
	 *
	 * [Generated from RDFReactor template rule #add1dynamic] 
     */
	public void addPrefHasScoringFunction( org.ontoware.rdf2go.model.node.Node value) {
		Base.add(this.model, this.getResource(), HASSCORINGFUNCTION, value);
	}
    /**
     * Adds a value to property HasScoringFunction from an instance of RealFunction 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 *
	 * [Generated from RDFReactor template rule #add3static] 
     */
	public static void addPrefHasScoringFunction(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, RealFunction value) {
		Base.add(model, instanceResource, HASSCORINGFUNCTION, value);
	}
	
    /**
     * Adds a value to property HasScoringFunction from an instance of RealFunction 
	 *
	 * [Generated from RDFReactor template rule #add4dynamic] 
     */
	public void addPrefHasScoringFunction(RealFunction value) {
		Base.add(this.model, this.getResource(), HASSCORINGFUNCTION, value);
	}
  

    /**
     * Sets a value of property HasScoringFunction from an RDF2Go node.
     * First, all existing values are removed, then this value is added.
     * Cardinality constraints are not checked, but this method exists only for properties with
     * no minCardinality or minCardinality == 1.
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be set
	 *
	 * [Generated from RDFReactor template rule #set1static] 
     */
	public static void setPrefHasScoringFunction( Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, org.ontoware.rdf2go.model.node.Node value) {
		Base.set(model, instanceResource, HASSCORINGFUNCTION, value);
	}
	
    /**
     * Sets a value of property HasScoringFunction from an RDF2Go node.
     * First, all existing values are removed, then this value is added.
     * Cardinality constraints are not checked, but this method exists only for properties with
     * no minCardinality or minCardinality == 1.
	 * @param value the value to be added
	 *
	 * [Generated from RDFReactor template rule #set1dynamic] 
     */
	public void setPrefHasScoringFunction( org.ontoware.rdf2go.model.node.Node value) {
		Base.set(this.model, this.getResource(), HASSCORINGFUNCTION, value);
	}
    /**
     * Sets a value of property HasScoringFunction from an instance of RealFunction 
     * First, all existing values are removed, then this value is added.
     * Cardinality constraints are not checked, but this method exists only for properties with
     * no minCardinality or minCardinality == 1.
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be added
	 *
	 * [Generated from RDFReactor template rule #set3static] 
     */
	public static void setPrefHasScoringFunction(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, RealFunction value) {
		Base.set(model, instanceResource, HASSCORINGFUNCTION, value);
	}
	
    /**
     * Sets a value of property HasScoringFunction from an instance of RealFunction 
     * First, all existing values are removed, then this value is added.
     * Cardinality constraints are not checked, but this method exists only for properties with
     * no minCardinality or minCardinality == 1.
	 * @param value the value to be added
	 *
	 * [Generated from RDFReactor template rule #set4dynamic] 
     */
	public void setPrefHasScoringFunction(RealFunction value) {
		Base.set(this.model, this.getResource(), HASSCORINGFUNCTION, value);
	}
  


    /**
     * Removes a value of property HasScoringFunction as an RDF2Go node 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be removed
	 *
	 * [Generated from RDFReactor template rule #remove1static] 
     */
	public static void removePrefHasScoringFunction( Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, org.ontoware.rdf2go.model.node.Node value) {
		Base.remove(model, instanceResource, HASSCORINGFUNCTION, value);
	}
	
    /**
     * Removes a value of property HasScoringFunction as an RDF2Go node
	 * @param value the value to be removed
	 *
	 * [Generated from RDFReactor template rule #remove1dynamic] 
     */
	public void removePrefHasScoringFunction( org.ontoware.rdf2go.model.node.Node value) {
		Base.remove(this.model, this.getResource(), HASSCORINGFUNCTION, value);
	}
    /**
     * Removes a value of property HasScoringFunction given as an instance of RealFunction 
     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 * @param value the value to be removed
	 *
	 * [Generated from RDFReactor template rule #remove3static] 
     */
	public static void removePrefHasScoringFunction(Model model, org.ontoware.rdf2go.model.node.Resource instanceResource, RealFunction value) {
		Base.remove(model, instanceResource, HASSCORINGFUNCTION, value);
	}
	
    /**
     * Removes a value of property HasScoringFunction given as an instance of RealFunction 
	 * @param value the value to be removed
	 *
	 * [Generated from RDFReactor template rule #remove4dynamic] 
     */
	public void removePrefHasScoringFunction(RealFunction value) {
		Base.remove(this.model, this.getResource(), HASSCORINGFUNCTION, value);
	}
  
    /**
     * Removes all values of property HasScoringFunction     * @param model an RDF2Go model
     * @param resource an RDF2Go resource
	 *
	 * [Generated from RDFReactor template rule #removeall1static] 
     */
	public static void removeAllPrefHasScoringFunction( Model model, org.ontoware.rdf2go.model.node.Resource instanceResource) {
		Base.removeAll(model, instanceResource, HASSCORINGFUNCTION);
	}
	
    /**
     * Removes all values of property HasScoringFunction	 *
	 * [Generated from RDFReactor template rule #removeall1dynamic] 
     */
	public void removeAllPrefHasScoringFunction() {
		Base.removeAll(this.model, this.getResource(), HASSCORINGFUNCTION);
	}
 }