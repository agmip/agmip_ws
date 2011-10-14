package service;

import beans.WeatherDaily;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.core.ResourceContext;
import converter.WeatherDailiesConverter;
import java.lang.reflect.Type;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author fonini
 */
@Path("/weatherDailiesService")
public class WeatherDailiesServiceResource {

	@Context
	private ResourceContext resourceContext;
	@Context
	protected UriInfo uriInfo;

	/** Creates a new instance of WeatherDailiesServiceResource */
	public WeatherDailiesServiceResource() {
	}

	/**
	 * Retrieves representation of an instance of service.WeatherDailiesServiceResource
	 * @return an instance of java.lang.String
	 */

	@GET
    @Produces({"application/xml", "application/json"})
	public WeatherDailiesConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM WeatherDaily e") String query) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			return new WeatherDailiesConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
		}
		finally {
			persistenceSvc.commitTx();
			persistenceSvc.close();
		}
	}

	/*@GET
	@Produces("application/json")
	public String getJson() throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
		PersistenceService persistenceSvc = PersistenceService.getInstance();

		persistenceSvc.beginTx();
		EntityManager em = persistenceSvc.getEntityManager();

		List<WeatherDaily> list = em.createQuery("FROM WeatherDaily").getResultList();

		// Create the XML Document
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		Element root = doc.createElement("weatherDailies");
		doc.appendChild(root);

		for (WeatherDaily weather : list) {
			//create child element, add an attribute, and add to root
			Element child = doc.createElement("weatherDaily");
			//child.setAttribute("name", "value");
			root.appendChild(child);

			//add a text element to the child
			//Text text = doc.createTextNode("Filler, ... I could have had a foo!");
			//child.appendChild(text);


			Element data = doc.createElement("wid");
			Text text = doc.createTextNode(weather.getWeatherDailyPK().getWid()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("wtyr");
			text = doc.createTextNode(weather.getWeatherDailyPK().getWtyr()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("wtday");
			text = doc.createTextNode(weather.getWeatherDailyPK().getWtday()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("srad");
			text = doc.createTextNode(weather.getSrad()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("tmax");
			text = doc.createTextNode(weather.getTmax()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("tmin");
			text = doc.createTextNode(weather.getTmin()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("rain");
			text = doc.createTextNode(weather.getRain()+"");
			data.appendChild(text);
			child.appendChild(data);
		}










		/////////////////
		//Output the XML

		//set up a transformer
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans = transfac.newTransformer();
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		//create string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		trans.transform(source, result);
		String xmlString = sw.toString();

		//print xml
		System.out.println(xmlString);


		return xmlString;
	}*/



	/*@GET
	@Produces("application/xml")
	public String getXml() throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
		PersistenceService persistenceSvc = PersistenceService.getInstance();

		persistenceSvc.beginTx();
		EntityManager em = persistenceSvc.getEntityManager();

		List<WeatherDaily> list = em.createQuery("FROM WeatherDaily").getResultList();

		// Create the XML Document
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		Element root = doc.createElement("weatherDailies");
		doc.appendChild(root);

		for (WeatherDaily weather : list) {
			//create child element, add an attribute, and add to root
			Element child = doc.createElement("weatherDaily");
			//child.setAttribute("name", "value");
			root.appendChild(child);

			//add a text element to the child
			//Text text = doc.createTextNode("Filler, ... I could have had a foo!");
			//child.appendChild(text);


			Element data = doc.createElement("wid");
			Text text = doc.createTextNode(weather.getWeatherDailyPK().getWid()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("wtyr");
			text = doc.createTextNode(weather.getWeatherDailyPK().getWtyr()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("wtday");
			text = doc.createTextNode(weather.getWeatherDailyPK().getWtday()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("srad");
			text = doc.createTextNode(weather.getSrad()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("tmax");
			text = doc.createTextNode(weather.getTmax()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("tmin");
			text = doc.createTextNode(weather.getTmin()+"");
			data.appendChild(text);
			child.appendChild(data);

			data = doc.createElement("rain");
			text = doc.createTextNode(weather.getRain()+"");
			data.appendChild(text);
			child.appendChild(data);
		}










		/////////////////
		//Output the XML

		//set up a transformer
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans = transfac.newTransformer();
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		//create string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		trans.transform(source, result);
		String xmlString = sw.toString();

		//print xml
		System.out.println(xmlString);


		return xmlString;

	}*/

	/**
	 * POST method for creating an instance of WeatherDailyServiceResource
	 * @param content representation for the new resource
	 * @return an HTTP response with content of the created resource
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response postJson(String content) {
		Gson json = new Gson();
		PersistenceService persistenceSvc = PersistenceService.getInstance();
		try {
			persistenceSvc.beginTx();
			EntityManager em = persistenceSvc.getEntityManager();

			Type collectionType = new TypeToken<Collection<WeatherDaily>>() {
			}.getType();
			Collection<WeatherDaily> collection = json.fromJson(content, collectionType);

			for (WeatherDaily daily : collection) {
				em.persist(daily);
			}

			persistenceSvc.commitTx();

			//return Response.created(resourceContext.).build();
			return Response.created(uriInfo.getAbsolutePath()).build();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	/**
	 * Sub-resource locator method for {id}
	 */
	@Path("{id}")
	public WeatherDailyServiceResource getSaveWeatherDailyResource(@PathParam("id") String id) {
		return WeatherDailyServiceResource.getInstance(id);
	}

	/**
	 * Returns a dynamic instance of WeatherDailyResource used for entity navigation.
	 *
	 * @return an instance of WeatherDailyResource
	 */
	@Path("{wid},{wtyr},{wtday}/")
	public WeatherDailyResource getWeatherDailyResource(@PathParam("wid") Integer id1, @PathParam("wtyr") Integer id2, @PathParam("wtday") Integer id3) {
		WeatherDailyResource weatherDailyResource = resourceContext.getResource(WeatherDailyResource.class);
		weatherDailyResource.setId1(id1);
		weatherDailyResource.setId2(id2);
		weatherDailyResource.setId3(id3);
		return weatherDailyResource;
	}

	/**
	 * Returns all the entities associated with this resource.
	 *
	 * @return a collection of WeatherDaily instances
	 */
	protected Collection<WeatherDaily> getEntities(int start, int max, String query) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
	}

	/**
	 * Persist the given entity.
	 *
	 * @param entity the entity to persist
	 */
	protected void createEntity(WeatherDaily entity) {
		EntityManager em = PersistenceService.getInstance().getEntityManager();
		em.persist(entity);
	}
}
