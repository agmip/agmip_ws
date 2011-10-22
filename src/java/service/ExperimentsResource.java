package service;

import beans.EnvironModifLevel;
import beans.Experiment;
import beans.FertilizerLevel;
import beans.Field;
import beans.Genotype;
import beans.InitialConditionLevel;
import beans.IrrigationLevel;
import beans.MulchLevel;
import beans.OrganicMaterialLevel;
import beans.Planting;
import beans.SoilAnalysesLevel;
import beans.TillageLevel;
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
import converter.ExperimentsConverter;
import converter.ExperimentConverter;
import com.sun.jersey.api.core.ResourceContext;
import java.util.ArrayList;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author fonini
 */
@Path("/experiments")
public class ExperimentsResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    /** Creates a new instance of ExperimentsResource */
    public ExperimentsResource() {
    }

    /**
     * Get method for retrieving a collection of Experiment instance in XML format.
     *
     * @return an instance of ExperimentsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public ExperimentsConverter get(@QueryParam("start")
            @DefaultValue("0") int start, @QueryParam("max")
            @DefaultValue("10") int max, @QueryParam("expandLevel")
            @DefaultValue("1") int expandLevel, @QueryParam("query")
            @DefaultValue("SELECT e FROM Experiment e") String query) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new ExperimentsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
        } finally {
            //persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Experiment using XML as the input format.
     *
     * @param data an ExperimentConverter entity that is deserialized from an XML stream
     * @return an instance of ExperimentConverter
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Response post(ExperimentConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
			EntityManager em = persistenceSvc.getEntityManager();
			persistenceSvc.beginTx();
			Experiment entity = data.resolveEntity(em);
			createEntity(entity);
			persistenceSvc.commitTx();

            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getExpId() + "/")).build();
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
     * Returns a dynamic instance of ExperimentalDescripsResource used for entity navigation.
     *
     * @return an instance of ExperimentalDescripsResource
     */
    @Path("{expId}/")
    public ExperimentResource getExperimentalDescripsResource(@PathParam("expId") Integer id) {
        ExperimentResource experimentResource = resourceContext.getResource(ExperimentResource.class);
        experimentResource.setId(id);
        return experimentResource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Experiment instances
     */
    protected Collection<Experiment> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Experiment entity) {
        entity.setExpId(null);
        EntityManager em = PersistenceService.getInstance().getEntityManager();

        em.persist(entity);

		Collection<Treatment> treatments = new ArrayList<Treatment>();

		// Iterate over the treatments setting the PK
		for (Treatment treatment : entity.getTreatmentsCollection()){
			treatment.getTreatmentPK().setExpId(entity.getExpId());
			treatment.setExperiment(entity);

			if (treatment.getField() != null){
				System.out.println("FIELDS");
				// Set the Experiment ID to the Field
				treatment.getField().getFieldPK().setExpId(entity.getExpId());
				// Persists the Field
				Field field = treatment.getField();
				em.persist(field);
				treatment.setField(field);
			}

			if (treatment.getGenotype() != null){
				System.out.println(treatment.getGenotype());
				System.out.println(treatment.getGenotype().getClass());
				System.out.println("GENOTYPES");
				// Set the Experiment ID to the Genotype
				treatment.getGenotype().getGenotypePK().setExpId(entity.getExpId());
				// Persists the Genotype
				Genotype genotype = treatment.getGenotype();
				em.persist(genotype);
				treatment.setGenotype(genotype);
			}

			if (treatment.getTillageLevels() != null){
				System.out.println("TILLAGE LEVELS");
				// Set the Experiment ID to the TillageLevel
				treatment.getTillageLevels().getTillageLevelPK().setExpId(entity.getExpId());
				// Persists the TillageLevel
				TillageLevel tillageLevel = treatment.getTillageLevels();
				em.persist(tillageLevel);
				treatment.setTillageLevels(tillageLevel);
			}

			if (treatment.getEnvironModifLevels() != null){
				System.out.println("ENVIRON MODIF LEVELS");
				// Set the Experiment ID to the EnvironModifLevel
				treatment.getEnvironModifLevels().getEnvironModifLevelPK().setExpId(entity.getExpId());
				// Persists the EnvironModifLevel
				EnvironModifLevel environModifLevel = treatment.getEnvironModifLevels();
				em.persist(environModifLevel);
				treatment.setEnvironModifLevels(environModifLevel);
			}


			if (treatment.getFertilizerLevels() != null){
				System.out.println("FERTILIZER LEVELS");
				// Set the Experiment ID to the FertilizerLevel
				treatment.getFertilizerLevels().getFertilizerLevelPK().setExpId(entity.getExpId());
				// Persists the FertilizerLevel
				FertilizerLevel fertilizerLevel = treatment.getFertilizerLevels();
				em.persist(fertilizerLevel);
				treatment.setFertilizerLevels(fertilizerLevel);
			}

			if (treatment.getInitialConditionLevel() != null){
				System.out.println("INITIAL CONDITION LEVELS");
				// Set the Experiment ID to the InitialConditionLevel
				treatment.getInitialConditionLevel().getInitialConditionLevelPK().setExpId(entity.getExpId());
				// Persists the InitialConditionLevel
				InitialConditionLevel initialConditionLevel = treatment.getInitialConditionLevel();
				em.persist(initialConditionLevel);
				treatment.setInitialConditionLevel(initialConditionLevel);
			}


			if (treatment.getIrrigationLevel() != null){
				System.out.println("IRRIGATION LEVELS");
				// Set the Experiment ID to the IrrigationLevel
				treatment.getIrrigationLevel().getIrrigationLevelPK().setExpId(entity.getExpId());
				// Persists the IrrigationLevel
				IrrigationLevel irrigationLevel = treatment.getIrrigationLevel();
				em.persist(irrigationLevel);
				treatment.setIrrigationLevel(irrigationLevel);
			}


			if (treatment.getMulchLevels() != null){
				System.out.println("MULCH LEVELS");
				// Set the Experiment ID to the MulchLevel
				treatment.getMulchLevels().getMulchLevelsPK().setExpId(entity.getExpId());
				// Persists the MulchLevel
				MulchLevel mulchLevel= treatment.getMulchLevels();
				em.persist(mulchLevel);
				treatment.setMulchLevels(mulchLevel);
			}


			if (treatment.getOrganicMaterialLevel() != null){
				System.out.println("ORGANIC MATERIAL LEVELS");
				// Set the Experiment ID to the OrganicMaterialLevel
				treatment.getOrganicMaterialLevel().getOrganicMaterialLevelPK().setExpId(entity.getExpId());
				// Persists the OrganicMaterialLevel
				OrganicMaterialLevel organicMaterialLevel = treatment.getOrganicMaterialLevel();
				em.persist(organicMaterialLevel);
				treatment.setOrganicMaterialLevel(organicMaterialLevel);
			}


			if (treatment.getPlanting() != null){
				System.out.println("PLANTINGS");
				// Set the Experiment ID to the Plantings
				treatment.getPlanting().getPlantingPK().setExpId(entity.getExpId());
				// Persists the Plantings
				Planting planting = treatment.getPlanting();
				em.persist(planting);
				treatment.setPlanting(planting);
			}


			if (treatment.getSoilAnalysesLevel() != null){
				System.out.println("SOIL ANALYSES");
				// Set the Experiment ID to the SoilAnalysesLevels
				treatment.getSoilAnalysesLevel().getSoilAnalysesLevelPK().setExpId(entity.getExpId());
				// Persists the SoilAnalysesLevels
				SoilAnalysesLevel soilAnalyses = treatment.getSoilAnalysesLevel();
				em.persist(soilAnalyses);
				treatment.setSoilAnalysesLevel(soilAnalyses);
			}





			treatments.add(treatment);
		}

		entity.setTreatmentsCollection(treatments);
    }
}
