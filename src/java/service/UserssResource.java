package service;

import beans.Users;
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
import converter.UserssConverter;
import converter.UsersConverter;
import com.sun.jersey.api.core.ResourceContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author fonini
 */
@Path("/userss/")
public class UserssResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of UserssResource */
    public UserssResource() {
    }

    /**
     * Get method for retrieving a collection of Users instance in XML format.
     *
     * @return an instance of UserssConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public UserssConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM Users e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new UserssConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Users using XML as the input format.
     *
     * @param data an UsersConverter entity that is deserialized from an XML stream
     * @return an instance of UsersConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
    public Response post(UsersConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Users entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();

            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getUserId() + "/")).entity(entity).build();
        }
		catch(ConstraintViolationException e){
			System.out.println(e.getConstraintViolations());
			return null;
		}
		finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of UsersResource used for entity navigation.
     *
     * @return an instance of UsersResource
     */
    @Path("{userId}/")
    public UsersResource getUsersResource(@PathParam("userId") Integer id) {
        UsersResource usersResource = resourceContext.getResource(UsersResource.class);
        usersResource.setId(id);
        return usersResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Users instances
     */
    protected Collection<Users> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Users entity) {
        entity.setUserId(null);
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            Users oldEntity = value.getUpdateUserId();
            value.setUpdateUserId(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}
