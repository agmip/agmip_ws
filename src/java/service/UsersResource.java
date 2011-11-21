package service;

import beans.User;
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
import converter.UsersConverter;
import converter.UserConverter;
import com.sun.jersey.api.core.ResourceContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author fonini
 */
@Path("/users/")
public class UsersResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of UsersResource */
    public UsersResource() {
    }

    /**
     * Get method for retrieving a collection of User instance in XML format.
     *
     * @return an instance of UsersConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public UsersConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM User e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new UsersConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of User using XML as the input format.
     *
     * @param data an UserConverter entity that is deserialized from an XML stream
     * @return an instance of UserConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
    public Response post(UserConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            User entity = data.resolveEntity(em);
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
     * Returns a dynamic instance of UserResource used for entity navigation.
     *
     * @return an instance of UserResource
     */
    @Path("{userId}/")
    public UserResource getUsersResource(@PathParam("userId") Integer id) {
        UserResource usersResource = resourceContext.getResource(UserResource.class);
        usersResource.setId(id);
        return usersResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of User instances
     */
    protected Collection<User> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(User entity) {
        entity.setUserId(null);
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        for (Treatment value : entity.getTreatmentsCollection()) {
            User oldEntity = value.getUpdateUserId();
            value.setUpdateUserId(entity);
            if (oldEntity != null) {
                oldEntity.getTreatmentsCollection().remove(value);
            }
        }
    }
}