package es.us.isa.cloudServices;

import java.net.URI;

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

@RDFBean("gr:QuantitativeValueFloat")
public interface QuantitativeValueFloat {

	@RDFSubject
	public String getId();
	public void setId(String id);
	
	@RDF("rdf:type")
	public URI getType();
	public void setType(URI type);	
	
	@RDF("gr:hasValueFloat")
	public float getHasValueFloat();
	public void setHasValueFloat(float val);
	
	@RDF("gr:hasMaxValueFloat")
	public float getHasMaxValueFloat();
	public void setHasMaxValueFloat(float val);
	
	@RDF("qudt:unit")
	public URI getUnit();
	public void setUnit(URI unit);
}
