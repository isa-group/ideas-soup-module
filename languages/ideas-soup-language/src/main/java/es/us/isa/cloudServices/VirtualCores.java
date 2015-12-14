package es.us.isa.cloudServices;

import es.us.isa.cloudServices.QuantitativeValue;

import com.viceversatech.rdfbeans.annotations.RDF;
import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFNamespaces;
import com.viceversatech.rdfbeans.annotations.RDFSubject;

@RDFNamespaces({
	"cloud = http://example.org/cloudServices#",
	"gr = http://purl.org/goodrelations/v1#",
	"qudt = http://qudt.org/schema/qudt#",
	"rdf = http://www.w3.org/1999/02/22-rdf-syntax-ns#",
	"rdfs =  http://www.w3.org/2000/01/rdf-schema#",
	"skos = http://www.w3.org/2004/02/skos/core#",
	"xsd = http://www.w3.org/2001/XMLSchema#"
})

@RDFBean("cloud:hasVirtualCores")
public interface VirtualCores {
	
	@RDFSubject()
	public String getId();
	public void setId(String id);
	
	@RDF("rdfs:domain")
	public ComputationService getDomain();
	public void setDomain(ComputationService cs);	
	
	@RDF("rdfs:label")
	public String getLabel();
	public void setLabel(String val);
	
	@RDF("rdfs:range")
	public QuantitativeValue getRange();
	public void setRange(QuantitativeValue val);
}
