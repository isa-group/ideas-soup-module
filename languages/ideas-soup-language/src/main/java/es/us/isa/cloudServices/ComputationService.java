package es.us.isa.cloudServices;

import java.net.URI;
 
import es.us.isa.cloudServices.QuantitativeValue;
import es.us.isa.puri.RankableItem;

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

@RDFBean("cloud:ComputationService")
public interface ComputationService extends RankableItem{
	
	@RDFSubject
	public String getId();
	public void setId(String id);
	
	@RDF("cloud:hasComputingPerformance")
	public QuantitativeValue getHasComputingPerformance();
	public void setHasComputingPerformance(QuantitativeValue hasComputingPerformance);
	
	@RDF("cloud:hasIOPerformance")
	public URI getHasIOPerformance();
	public void setHasIOPerformance(URI hasIOPerformance);
	
	@RDF("cloud:hasInternalStorage") 
	public QuantitativeValue getHasInternalStorage();
	public void setHasInternalStorage(QuantitativeValue hasInternalStorage);
	
	@RDF("cloud:hasMemory")
	public QuantitativeValue getHasMemory();
	public void setHasMemory(QuantitativeValue hasMemory);
	
	@RDF("cloud:hasVirtualCores")
	public QuantitativeValue getHasVirtualCores();
	public void setHasVirtualCores(QuantitativeValue hasVirtualCores );
	
	@RDF("rdfs:label")
	public String getHasLabel();
	public void setHasLabel(String hasLabel);
}
