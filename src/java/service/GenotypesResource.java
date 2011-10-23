package service;

import beans.Genotype;
import java.util.Collection;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.persistence.EntityManager;
import beans.Treatment;
import converter.GenotypesConverter;
import converter.GenotypeConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
@Path("/genotypes/")
public class GenotypesResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of GenotypesResource */
    public GenotypesResource() {
    }

    /**
     * Get method for retrieving a collection of Genotype instance in XML format.
     *
     * @return an instance of GenotypesConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public GenotypesConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM Genotype e") String query) {

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new GenotypesConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Genotype using XML as the input format.
     *
     * @param data an GenotypeConverter entity that is deserialized from an XML stream
     * @return an instance of GenotypeConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
    public Response post(GenotypeConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Genotype entity = data.resolveEntity(em);
			System.out.println(entity.getCulName());
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getGenotypePK().getExpId() + "," + entity.getGenotypePK().getGe() + "/")).entity(entity).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of GenotypeResource used for entity navigation.
     *
     * @return an instance of GenotypeResource
     */
    @Path("{expId},{ge}/")
    public GenotypeResource getGenotypesResource(@PathParam("expId") Integer id1, @PathParam("ge") Integer id2) {
        GenotypeResource genotypesResource = resourceContext.getResource(GenotypeResource.class);
        genotypesResource.setId1(id1);
        genotypesResource.setId2(id2);
        return genotypesResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Genotype instances
     */
    protected Collection<Genotype> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Genotype entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            Genotype oldEntity = value.getGenotype();
            value.setGenotype(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
