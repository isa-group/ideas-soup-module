package es.us.isa.ideas.controller.soup;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ontoware.aifbcommons.collection.ClosableIterator;
import org.ontoware.rdf2go.ModelFactory;
import org.ontoware.rdf2go.RDF2Go;
import org.ontoware.rdf2go.exception.ModelRuntimeException;
import org.ontoware.rdf2go.model.Model;
import org.ontoware.rdf2go.model.Statement;
import org.ontoware.rdf2go.model.Syntax;
import org.ontoware.rdf2go.model.node.URI;
import org.ontoware.rdf2go.model.node.Variable;
import org.ontoware.rdf2go.model.node.impl.URIImpl;
import org.ontoware.rdf2go.vocabulary.RDF;
import org.openrdf.rdf2go.RepositoryModelFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.viceversatech.rdfbeans.RDFBeanManager;
import com.viceversatech.rdfbeans.exceptions.RDFBeanException;

import es.us.isa.cloudServices.ComputationService;
import es.us.isa.puri.RankingMechanism;
import es.us.isa.puri.UnsupportedPreferenceTerm;
import es.us.isa.puri.mechanism.SOUPRankingMechanismFactory;
import es.us.isa.puri.model.DislikesPreference;
import es.us.isa.puri.model.FavoritesPreference;
import es.us.isa.puri.model.HighestPreference;
import es.us.isa.puri.model.LowestPreference;
import es.us.isa.puri.model.PreferenceTerm;
import es.us.isa.ideas.common.AppResponse;
import es.us.isa.ideas.common.AppResponse.Status;
import es.us.isa.ideas.module.controller.BaseLanguageController;

@Controller
@RequestMapping("/language")
public class SoupLanguageController extends BaseLanguageController {

	@RequestMapping(value = "/operation/{id}/execute", method = RequestMethod.POST)
	@ResponseBody
	public AppResponse executeOperation(String id, String content, String fileUri, String auxArg0) {

		AppResponse appResp = new AppResponse();

		// CREACIÓN DE MODELFACTORY
		RDF2Go.register(new RepositoryModelFactory());
		ModelFactory modelFactory = RDF2Go.getModelFactory();

		// CREACIÓN DE MODELO SOUP
		Model soupModel = modelFactory.createModel();
		soupModel.open();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream is = classLoader.getResourceAsStream("SoupModel.txt");
			soupModel.readFrom(is, Syntax.Turtle);
			is.close();
		} catch (IOException e) {
			appResp.setFileUri(fileUri);
			appResp.setMessage("<pre>Problem while creating SOUP model: " + e.getMessage() + "</pre>");
			appResp.setStatus(Status.OK_PROBLEMS);
		} catch (ModelRuntimeException e) {
			appResp.setFileUri(fileUri);
			appResp.setMessage("<pre>Problem while creating SOUP model: " + e.getMessage() + "</pre>");
			appResp.setStatus(Status.OK_PROBLEMS);
		}

		if (id.equals("rank")) {
			
			// CREACIÓN DE MODELO DE PREFERENCIAS
			Model preferencesModel = modelFactory.createModel();
			preferencesModel.open();
			try {
				InputStream is = new ByteArrayInputStream(content.getBytes());
				preferencesModel.readFrom(is, Syntax.Turtle);
				preferencesModel.addModel(soupModel);
			} catch (IOException e) {
				appResp.setFileUri(fileUri);
				appResp.setMessage("<pre>Problem while creating preferences model: " + e.getMessage() + "</pre>");
				appResp.setStatus(Status.OK_PROBLEMS);
			} catch (ModelRuntimeException e) {
				appResp.setFileUri(fileUri);
				appResp.setMessage("<pre>Problem while creating preferences model: " + e.getMessage() + "</pre>");
				appResp.setStatus(Status.OK_PROBLEMS);
			}

			// CREACIÓN DE MODELO DE SERVICIOS
			Model servicesModel = modelFactory.createModel();
			servicesModel.open();
			try {
				InputStream is = new ByteArrayInputStream(auxArg0.getBytes());
				servicesModel.readFrom(is, Syntax.Turtle);
			} catch (IOException e) {
				appResp.setFileUri(fileUri);
				appResp.setMessage("<pre>Problem while creating services model: " + e.getMessage() + "</pre>");
				appResp.setStatus(Status.OK_PROBLEMS);
			} catch (ModelRuntimeException e) {
				appResp.setFileUri(fileUri);
				appResp.setMessage("<pre>Problem while creating services model:  " + e.getMessage() + "</pre>");
				appResp.setStatus(Status.OK_PROBLEMS);
			}

			// CREACIÓN DE SERVICIOS
			RDFBeanManager manager = new RDFBeanManager(servicesModel);
			Set<ComputationService> services = new HashSet<ComputationService>();
			try {
				Collection<ComputationService> servicesCollection = manager.createAll(ComputationService.class);
				for (ComputationService c : servicesCollection)
					services.add(c);
			} catch (RDFBeanException e) {
				appResp.setFileUri(fileUri);
				appResp.setMessage("<pre>Problem while creating services: " + e.getMessage() + "</pre>");
				appResp.setStatus(Status.OK_PROBLEMS);
			}
						
			// CREACIÓN DE PREFERENCIA
			URI subject = new URIImpl("http://www.example.org#userPreference");
			ClosableIterator<Statement> it = preferencesModel.findStatements(subject, RDF.type, Variable.ANY);
			Statement st = null;
			PreferenceTerm preference = null;
			if (it.hasNext()) {
				st = it.next();
				if (st.getObject().asURI().toString().equals("http://www.isa.us.es/preferences#LowestPreference"))
					preference = new LowestPreference(preferencesModel, st.getSubject(), false);
				if (st.getObject().asURI().toString().equals("http://www.isa.us.es/preferences#HighestPreference"))
					preference = new HighestPreference(preferencesModel, st.getSubject(), false);
				if (st.getObject().asURI().toString().equals("http://www.isa.us.es/preferences#FavoritesPreference"))
					preference = new FavoritesPreference(preferencesModel, st.getSubject(), false);
				if (st.getObject().asURI().toString().equals("http://www.isa.us.es/preferences#DislikesPreference"))
					preference = new DislikesPreference(preferencesModel, st.getSubject(), false);
			}
					
			// RANKING
			List<ComputationService> rankedServices = new ArrayList<ComputationService>();
			SOUPRankingMechanismFactory factory = SOUPRankingMechanismFactory.getInstance();
			try {
				URI mechanismUri = preference.getAllPrefHasRankingMechanism_as().firstValue().asURI();
				RankingMechanism<ComputationService> mechanism = factory.create(mechanismUri);
				rankedServices = mechanism.rank(services, preference).getResultsAsList();

				String res = null;
				if (rankedServices.isEmpty())
					res = "No services to rank";
				else {
					Integer i = 1;
					res = "";
					for (ComputationService c : rankedServices) {
						res += "<pre>" + i.toString() + ". Name: " + c.getHasLabel() + " -  Computing Performance: "
								+ c.getHasComputingPerformance().getHasValueFloat() + " ECU" + " -  IO Performance: "
								+ c.getHasIOPerformance().toString().substring(33) + " -  Internal Storage: "
								+ c.getHasInternalStorage().getHasValueFloat() + " "
								+ c.getHasInternalStorage().getUnit().toString().substring(33) + " -  Memory: "
								+ c.getHasMemory().getHasValueFloat() + " "
								+ c.getHasMemory().getUnit().toString().substring(33) + " -  Virtual Cores: "
								+ c.getHasVirtualCores().getHasValueInteger() + "</pre>";
						i++;
					}
				}
				appResp.setMessage(res);
				appResp.setStatus(Status.OK);
				appResp.setFileUri(fileUri);
			} catch (NullPointerException e) {
				appResp.setMessage("Can't recognize preference: " + e.getMessage());
				appResp.setStatus(Status.OK);
				appResp.setFileUri(fileUri);
			} catch (UnsupportedPreferenceTerm e1) {
				appResp.setMessage("Unsupported preference type");
				appResp.setStatus(Status.OK);
				appResp.setFileUri(fileUri);
			}
		}

		return appResp;
	}

	@RequestMapping(value = "/format/{format}/checkLanguage", method = RequestMethod.POST)
	@ResponseBody
	public AppResponse checkLanguage(String format, String content, String fileUri) {
		return null;
	}

	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	@ResponseBody
	public AppResponse convertFormat(String currentFormat, String desiredFormat, String fileUri, String content) {
		return null;
	}
	
}
