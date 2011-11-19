package service;

import beans.ChemicalLevel;
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
import beans.Treatment;
import java.util.Collection;
import converter.ChemicalLevelConverter;
import com.sun.jersey.api.core.ResourceContext;

/**
 *
 * @author fonini
 */
public class ChemicalLevelResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;
    protected Integer id2;
    protected Integer id1;

    /** Creates a new instance of ChemicalLevelResource */
    public ChemicalLevelResource() {
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    /**
     * Get method for retrieving an instance of ChemicalLevel identified by id in XML format.
     *
     * @param id identifier for the entity
     * @return an instance of ChemicalLevelConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public ChemicalLevelConverter get(@QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new ChemicalLevelConverter(getEntity(), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * Put method for updating an instance of ChemicalLevel identified by id using XML as the input format.
     *
     * @param id identifier for the entity
     * @param data an ChemicalLevelConverter entity that is deserialized from a XML stream
     */
    @PUT
    @Consumes({"application/xml", "application/json"})
    public void put(ChemicalLevelConverter data) {
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
     * Delete method for deleting an instance of ChemicalLevel identified by id.
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
     * Returns an instance of ChemicalLevel identified by id.
     *
     * @param id identifier for the entity
     * @return an instance of ChemicalLevel
     */
    protected ChemicalLevel getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            beans.ChemicalLevelPK id = new beans.ChemicalLevelPK(id1, id2);
            return (ChemicalLevel) em.createQuery("SELECT e FROM ChemicalLevel e where e.chemicalLevelPK = :chemicalLevelPK").setParameter("chemicalLevelPK", id).getSingleResult();
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
    private ChemicalLevel updateEntity(ChemicalLevel entity, ChemicalLevel newEntity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        Collection<Treatment> treatmentsCollection = entity.getTreatmentsCollection();
        Collection<Treatment> treatmentsCollectionNew = newEntity.getTreatmentsCollection();
        entity = em.merge(newEntity);
        for (Treatment value : treatmentsCollection) {
            if (!treatmentsCollectionNew.contains(value)) {
                throw new WebApplicationException(new Throwable("Cannot remove items from treatmentsCollection"));
            }
        }
        for (Treatment value : treatmentsCollectionNew) {
            if (!treatmentsCollection.contains(value)) {
                ChemicalLevel oldEntity = value.getChemicalLevels();
                value.setChemicalLevels(entity);
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
    private void deleteEntity(ChemicalLevel entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        if (!entity.getTreatmentsCollection().isEmpty()) {
            throw new WebApplicationException(new Throwable("Cannot delete entity because treatmentsCollection is not empty."));
        }
        em.remove(entity);
    }

    /**
     * Returns a dynamic instance of TreatmentsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of TreatmentsResource
     */
    @Path("treatmentsCollection/")
    public TreatmentsResource getTreatmentsCollectionResource() {
        TreatmentsCollectionResourceSub treatmentsCollectionResourceSub = resourceContext.getResource(TreatmentsCollectionResourceSub.class);
        treatmentsCollectionResourceSub.setParent(getEntity());
        return treatmentsCollectionResourceSub;
    }

    public static class TreatmentsCollectionResourceSub extends TreatmentsResource {

        private ChemicalLevel parent;

        public void setParent(ChemicalLevel parent) {
            this.parent = parent;
        }

        @Override
        protected Collection<Treatment> getEntities(int start, int max, String query) {
            Collection<Treatment> result = new java.util.ArrayList<Treatment>();
            int index = 0;
            for (Treatment e : parent.getTreatmentsCollection()) {
                if (index >= start && (index - start) < max) {
                    result.add(e);
                }
                index++;
            }
            return result;
        }
    }
}
