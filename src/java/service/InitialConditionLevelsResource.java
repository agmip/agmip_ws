/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.InitialConditionLevels;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import beans.Treatments;
import java.util.Collection;
import converter.InitialConditionLevelsConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author wpavan
 */
public class InitialConditionLevelsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;
    protected Integer id2;
    protected Integer id1;

    /** Creates a new instance of InitialConditionLevelsResource */
    public InitialConditionLevelsResource() {
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    /**
     * Get method for retrieving an instance of InitialConditionLevels identified by id in XML format.
     *
     * @param id identifier for the entity
     * @return an instance of InitialConditionLevelsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public InitialConditionLevelsConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new InitialConditionLevelsConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * Put method for updating an instance of InitialConditionLevels identified by id using XML as the input format.
     *
     * @param id identifier for the entity
     * @param data an InitialConditionLevelsConverter entity that is deserialized from a XML stream
     */
    @PUT
    @Consumes({"application/xml", "application/json"})
    public void put(InitialConditionLevelsConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            updateEntity(getEntity(), data.resolveEntity(em));
            persistenceSvc.commitTx();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Delete method for deleting an instance of InitialConditionLevels identified by id.
     *
     * @param id identifier for the entity
     */
    @DELETE
    public void delete() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            deleteEntity(getEntity());
            persistenceSvc.commitTx();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns an instance of InitialConditionLevels identified by id.
     *
     * @param id identifier for the entity
     * @return an instance of InitialConditionLevels
     */
    protected InitialConditionLevels getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            beans.InitialConditionLevelsPK id = new beans.InitialConditionLevelsPK(id1, id2);
            return (InitialConditionLevels) em.createQuery("SELECT e FROM InitialConditionLevels e where e.initialConditionLevelsPK = :initialConditionLevelsPK").setParameter("initialConditionLevelsPK", id).getSingleResult();
        } catch (NoResultException ex) {
            throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
        }
    }

    /**
     * Updates entity using data from newEntity.
     *
     * @param entity the entity to update
     * @param newEntity the entity containing the new data
     * @return the updated entity
     */
    private InitialConditionLevels updateEntity(InitialConditionLevels entity, InitialConditionLevels newEntity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        Collection<Treatments> treatmentsCollection = entity.getTreatmentsCollection();
        Collection<Treatments> treatmentsCollectionNew = newEntity.getTreatmentsCollection();
        entity = em.merge(newEntity);
        for (Treatments value : treatmentsCollection) {
            if (!treatmentsCollectionNew.contains(value)) {
                throw new WebApplicationException(new Throwable("Cannot remove items from treatmentsCollection"));
            }
        }
        for (Treatments value : treatmentsCollectionNew) {
            if (!treatmentsCollection.contains(value)) {
                InitialConditionLevels oldEntity = value.getInitialConditionLevels();
                value.setInitialConditionLevels(entity);
                if (oldEntity != null && !oldEntity.equals(entity)) {
                    oldEntity.getTreatmentsCollection().remove(value);
                }
            }
        }
        return entity;
    }

    /**
     * Deletes the entity.
     *
     * @param entity the entity to deletle
     */
    private void deleteEntity(InitialConditionLevels entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        if (!entity.getTreatmentsCollection().isEmpty()) {
            throw new WebApplicationException(new Throwable("Cannot delete entity because treatmentsCollection is not empty."));
        }
        em.remove(entity);
    }

    /**
     * Returns a dynamic instance of TreatmentssResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of TreatmentssResource
     */
    @Path("treatmentsCollection/")
    public TreatmentssResource getTreatmentsCollectionResource() {
        TreatmentsCollectionResourceSub treatmentsCollectionResourceSub = resourceContext.getResource(TreatmentsCollectionResourceSub.class);
        treatmentsCollectionResourceSub.setParent(getEntity());
        return treatmentsCollectionResourceSub;
    }

    public static class TreatmentsCollectionResourceSub extends TreatmentssResource {

        private InitialConditionLevels parent;

        public void setParent(InitialConditionLevels parent) {
            this.parent = parent;
        }

        @Override
        protected Collection<Treatments> getEntities(int start, int max, String query) {
            Collection<Treatments> result = new java.util.ArrayList<Treatments>();
            int index = 0;
            for (Treatments e : parent.getTreatmentsCollection()) {
                if (index >= start && (index - start) < max) {
                    result.add(e);
                }
                index++;
            }
            return result;
        }
    }
}
