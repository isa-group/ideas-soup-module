package es.us.isa.cloudServices;

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

@RDFBean("cloud:IOPerformanceValue")
public interface IOPerformanceValue {
	
	@RDFSubject()
	public String getId();
	public void setId(String id);
	
	@RDF("rdfs:label")
	public String getLabel();
	public void setLabel(String val);
	
	@RDF("gr:greater")
	public IOPerformanceValue getGreater();
	public void setGreater(IOPerformanceValue cs);	
	
	@RDF("gr:lesser")
	public IOPerformanceValue getLesser();
	public void setLesser(IOPerformanceValue val);
}
