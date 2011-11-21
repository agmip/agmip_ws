package service;

import beans.ChemicalEvent;
import beans.ChemicalLevel;
import beans.EnvironModifEvent;
import beans.EnvironModifLevel;
import beans.Experiment;
import beans.FertilizerEvent;
import beans.FertilizerLevel;
import beans.Field;
import beans.Genotype;
import beans.HarvestEvent;
import beans.HarvestLevel;
import beans.InitialConditionEvent;
import beans.InitialConditionLevel;
import beans.IrrigationEvent;
import beans.IrrigationLevel;
import beans.MulchEvent;
import beans.MulchLevel;
import beans.OrganicMaterialEvent;
import beans.OrganicMaterialLevel;
import beans.Planting;
import beans.SoilAnalysesEvent;
import beans.SoilAnalysesLevel;
import beans.TillageEvent;
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
		}
		finally {
			persistenceSvc.commitTx();
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
	@Produces({"application/xml", "application/json"})
	public Response post(ExperimentConverter data) {
		PersistenceService persistenceSvc = PersistenceService.getInstance();

		Integer id = null;
		Experiment entity = null;

		try {
			EntityManager em = persistenceSvc.getEntityManager();
			persistenceSvc.beginTx();
			entity = data.resolveEntity(em);
			createEntity(entity);
			persistenceSvc.commitTx();
			id = entity.getExpId();

			return Response.created(uriInfo.getAbsolutePath().resolve(entity.getExpId() + "/")).entity(entity).build();
		}
		catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return null;
		}
		finally {
			persistenceSvc.close();

			persistenceSvc.beginTx();

			Integer ti, em, fe, fl, ge, ha, ic, ir, ml, om, pl, sa, ch;

			for (Treatment treatment : entity.getTreatmentsCollection()){
				ti = em = fe = fl = ge = ha = ic = ir = ml = om = pl = sa = ch = null;

				ti = treatment.getTillageLevel() != null ? treatment.getTillageLevel().getTillageLevelPK().getTi() : null;
				em = treatment.getEnvironModifLevel() != null ? treatment.getEnvironModifLevel().getEnvironModifLevelPK().getEm() : null;
				fe = treatment.getFertilizerLevel() != null ? treatment.getFertilizerLevel().getFertilizerLevelPK().getFe() : null;
				fl = treatment.getField() != null ? treatment.getField().getFieldPK().getFl() : null;
				ge = treatment.getGenotype() != null ? treatment.getGenotype().getGenotypePK().getGe() : null;
				ha = treatment.getHarvestLevel() != null ? treatment.getHarvestLevel().getHarvestLevelPK().getHa() : null;
				ic = treatment.getInitialConditionLevel() != null ? treatment.getInitialConditionLevel().getInitialConditionLevelPK().getIc() : null;
				ir = treatment.getIrrigationLevel() != null ? treatment.getIrrigationLevel().getIrrigationLevelPK().getIr() : null;
				ml = treatment.getMulchLevel() != null ? treatment.getMulchLevel().getMulchLevelPK().getMl() : null;
				om = treatment.getOrganicMaterialLevel() != null ? treatment.getOrganicMaterialLevel().getOrganicMaterialLevelPK().getOm() : null;
				pl = treatment.getPlanting() != null ? treatment.getPlanting().getPlantingPK().getPl() : null;
				sa = treatment.getSoilAnalysesLevel() != null ? treatment.getSoilAnalysesLevel().getSoilAnalysesLevelPK().getSa() : null;
				ch = treatment.getChemicalLevel() != null ? treatment.getChemicalLevel().getChemicalLevelPK().getCh() : null;

				String str = "UPDATE treatments SET ti = :ti, ch = :ch, em = :em, fe = :fe, ";
				str += "fl = :fl, ge = :ge, ha = :ha, ic = :ic, ir = :ir, ml = :ml, om = :om, ";
				str += "pl = :pl, sa = :sa WHERE exp_id = :exp_id AND trno = :trno";

				int ret = persistenceSvc.getEntityManager().createNativeQuery(str).setParameter("ti", ti).setParameter("ch", ch).
						setParameter("em", em).setParameter("fe", fe).setParameter("fl", fl).
						setParameter("ge", ge).setParameter("ha", ha).setParameter("ic", ic).
						setParameter("ir", ir).setParameter("ml", ml).setParameter("om", om).
						setParameter("pl", pl).setParameter("sa", sa).setParameter("exp_id", id).
						setParameter("trno", treatment.getTreatmentPK().getTrno()).executeUpdate();
			}

			persistenceSvc.commitTx();
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
		if (entity.getTreatmentsCollection() != null) {
			for (Treatment treatment : entity.getTreatmentsCollection()) {
				treatment.getTreatmentPK().setExpId(entity.getExpId());
				treatment.setExperiment(entity);

				if (treatment.getField() != null) {
					System.out.println("FIELDS");
					// Set the Experiment ID to the Field
					treatment.getField().getFieldPK().setExpId(entity.getExpId());
					// Persists the Field
					Field field = treatment.getField();
					em.persist(field);
					treatment.setField(field);
				}

				if (treatment.getGenotype() != null) {
					System.out.println("GENOTYPES");
					// Set the Experiment ID to the Genotype
					treatment.getGenotype().getGenotypePK().setExpId(entity.getExpId());
					// Persists the Genotype
					Genotype genotype = treatment.getGenotype();
					em.persist(genotype);
					treatment.setGenotype(genotype);
				}

				if (treatment.getChemicalLevel() != null) {
					System.out.println("CHEMICAL LEVELS");
					// Set the Experiment ID to the ChemicalLevel
					treatment.getChemicalLevel().getChemicalLevelPK().setExpId(entity.getExpId());

					/* Gets the ChemicalLevel, add references to the childs
					and then persists it */
					ChemicalLevel chemicalLevel = treatment.getChemicalLevel();

					Collection<ChemicalEvent> list = chemicalLevel.getChemicalEventsCollection();

					if (list != null) {
						Collection<ChemicalEvent> newList = new ArrayList<ChemicalEvent>();

						for (ChemicalEvent obj : list) {
							obj.getChemicalEventPK().setExpId(entity.getExpId());
							newList.add(obj);
						}

						chemicalLevel.setChemicalEventsCollection(newList);
					}

					em.persist(chemicalLevel);
					treatment.setChemicalLevel(chemicalLevel);
				}

				if (treatment.getTillageLevel() != null) {
					System.out.println("TILLAGE LEVELS");
					// Set the Experiment ID to the TillageLevel
					treatment.getTillageLevel().getTillageLevelPK().setExpId(entity.getExpId());

					/* Gets the TillageLevel, add references to the childs
					and then persists it */
					TillageLevel tillageLevel = treatment.getTillageLevel();

					Collection<TillageEvent> list = tillageLevel.getTillageEventsCollection();

					if (list != null) {
						Collection<TillageEvent> newList = new ArrayList<TillageEvent>();

						for (TillageEvent obj : list) {
							obj.getTillageEventPK().setExpId(entity.getExpId());
							newList.add(obj);
						}

						tillageLevel.setTillageEventsCollection(newList);
					}

					em.persist(tillageLevel);
					treatment.setTillageLevel(tillageLevel);
				}

				if (treatment.getEnvironModifLevel() != null) {
					System.out.println("ENVIRON MODIF LEVELS");
					// Set the Experiment ID to the EnvironModifLevel
					treatment.getEnvironModifLevel().getEnvironModifLevelPK().setExpId(entity.getExpId());

					/* Gets the EnvironModifLevel, add references to the childs
					and then persists it */
					EnvironModifLevel environModifLevel = treatment.getEnvironModifLevel();

					Collection<EnvironModifEvent> list = environModifLevel.getEnvironModifEventsCollection();

					if (list != null) {
						Collection<EnvironModifEvent> newList = new ArrayList<EnvironModifEvent>();

						for (EnvironModifEvent obj : list) {
							obj.getEnvironModifEventPK().setExpId(entity.getExpId());
							newList.add(obj);
						}

						environModifLevel.setEnvironModifEventsCollection(newList);
					}

					em.persist(environModifLevel);
					treatment.setEnvironModifLevel(environModifLevel);
				}


				if (treatment.getFertilizerLevel() != null) {
					System.out.println("FERTILIZER LEVELS");
					// Set the Experiment ID to the FertilizerLevel
					treatment.getFertilizerLevel().getFertilizerLevelPK().setExpId(entity.getExpId());

					/* Gets the FertilizerLevel, add references to the childs
					and then persists it */
					FertilizerLevel fertilizerLevel = treatment.getFertilizerLevel();

					Collection<FertilizerEvent> list = fertilizerLevel.getFertilizerEventsCollection();

					if (list != null) {
						Collection<FertilizerEvent> newList = new ArrayList<FertilizerEvent>();

						for (FertilizerEvent obj : list) {
							obj.getFertilizerEventPK().setExpId(entity.getExpId());
							newList.add(obj);
						}

						fertilizerLevel.setFertilizerEventsCollection(newList);
					}

					em.persist(fertilizerLevel);
					treatment.setFertilizerLevel(fertilizerLevel);
				}

				if (treatment.getInitialConditionLevel() != null) {
					System.out.println("INITIAL CONDITION LEVELS");
					// Set the Experiment ID to the InitialConditionLevel
					treatment.getInitialConditionLevel().getInitialConditionLevelPK().setExpId(entity.getExpId());

					/* Gets the InitialConditionLevel, add references to the childs
					and then persists it */
					InitialConditionLevel initialConditionLevel = treatment.getInitialConditionLevel();

					Collection<InitialConditionEvent> list = initialConditionLevel.getInitialConditionEventsCollection();

					if (list != null) {
						Collection<InitialConditionEvent> newList = new ArrayList<InitialConditionEvent>();

						for (InitialConditionEvent obj : list) {
							obj.getInitialConditionEventPK().setExpId(entity.getExpId());
							newList.add(obj);
						}

						initialConditionLevel.setInitialConditionEventsCollection(newList);
					}

					em.persist(initialConditionLevel);
					treatment.setInitialConditionLevel(initialConditionLevel);
				}

				if (treatment.getIrrigationLevel() != null) {
					System.out.println("IRRIGATION LEVELS");
					// Set the Experiment ID to the IrrigationLevel
					treatment.getIrrigationLevel().getIrrigationLevelPK().setExpId(entity.getExpId());

					/* Gets the IrrigationLevel, add references to the childs
					and then persists it */
					IrrigationLevel irrigationLevel = treatment.getIrrigationLevel();

					Collection<IrrigationEvent> list = irrigationLevel.getIrrigationEventsCollection();

					if (list != null) {
						Collection<IrrigationEvent> newList = new ArrayList<IrrigationEvent>();

						for (IrrigationEvent obj : list) {
							obj.getIrrigationEventPK().setExpId(entity.getExpId());
							newList.add(obj);
						}

						irrigationLevel.setIrrigationEventsCollection(newList);
					}

					em.persist(irrigationLevel);
					treatment.setIrrigationLevel(irrigationLevel);
				}


				if (treatment.getMulchLevel() != null) {
					System.out.println("MULCH LEVELS");
					// Set the Experiment ID to the MulchLevel
					treatment.getMulchLevel().getMulchLevelPK().setExpId(entity.getExpId());

					/* Gets the MulchLevel, add references to the childs
					and then persists it */
					MulchLevel mulchLevel = treatment.getMulchLevel();

					Collection<MulchEvent> list = mulchLevel.getMulchEventsCollection();

					if (list != null) {
						Collection<MulchEvent> newList = new ArrayList<MulchEvent>();

						for (MulchEvent obj : list) {
							obj.getMulchEventPK().setExpId(entity.getExpId());
							newList.add(obj);
						}

						mulchLevel.setMulchEventsCollection(newList);
					}

					em.persist(mulchLevel);
					treatment.setMulchLevel(mulchLevel);
				}

				if (treatment.getOrganicMaterialLevel() != null) {
					System.out.println("ORGANIC MATERIAL LEVELS");
					// Set the Experiment ID to the OrganicMaterialLevel
					treatment.getOrganicMaterialLevel().getOrganicMaterialLevelPK().setExpId(entity.getExpId());

					/* Gets the OrganicMaterialLevel, add references to the childs
					and then persists it */
					OrganicMaterialLevel organicMaterialLevel = treatment.getOrganicMaterialLevel();

					Collection<OrganicMaterialEvent> list = organicMaterialLevel.getOrganicMaterialEventsCollection();

					if (list != null) {
						Collection<OrganicMaterialEvent> newList = new ArrayList<OrganicMaterialEvent>();

						for (OrganicMaterialEvent obj : list) {
							obj.getOrganicMaterialEventPK().setExpId(entity.getExpId());
							newList.add(obj);
						}

						organicMaterialLevel.setOrganicMaterialEventsCollection(newList);
					}

					em.persist(organicMaterialLevel);
					treatment.setOrganicMaterialLevel(organicMaterialLevel);
				}

				if (treatment.getHarvestLevel() != null) {
					System.out.println("HARVEST LEVELS");
					// Set the Experiment ID to the HarvestLevel
					treatment.getHarvestLevel().getHarvestLevelPK().setExpId(entity.getExpId());

					/* Gets the HarvestLevel, add references to the childs
					and then persists it */
					HarvestLevel harvestLevel = treatment.getHarvestLevel();

					Collection<HarvestEvent> list = harvestLevel.getHarvestEventsCollection();

					if (list != null) {
						Collection<HarvestEvent> newList = new ArrayList<HarvestEvent>();

						for (HarvestEvent obj : list) {
							obj.getHarvestEventPK().setExpId(entity.getExpId());
							newList.add(obj);
						}

						harvestLevel.setHarvestEventsCollection(newList);
					}

					em.persist(harvestLevel);
					treatment.setHarvestLevel(harvestLevel);
				}


				if (treatment.getPlanting() != null) {
					System.out.println("PLANTINGS");
					// Set the Experiment ID to the Plantings
					treatment.getPlanting().getPlantingPK().setExpId(entity.getExpId());

					// Persists the Field
					Planting planting = treatment.getPlanting();
					em.persist(planting);
					treatment.setPlanting(planting);
				}


				if (treatment.getSoilAnalysesLevel() != null) {
					System.out.println("SOIL ANALYSES");
					// Set the Experiment ID to the SoilAnalysesLevels
					treatment.getSoilAnalysesLevel().getSoilAnalysesLevelPK().setExpId(entity.getExpId());

					/* Gets the OrganicMaterialLevel, add references to the childs
					and then persists it */
					SoilAnalysesLevel soilAnalyses = treatment.getSoilAnalysesLevel();

					Collection<SoilAnalysesEvent> list = soilAnalyses.getSoilAnalysesEventsCollection();

					if (list != null) {
						Collection<SoilAnalysesEvent> newList = new ArrayList<SoilAnalysesEvent>();
						for (SoilAnalysesEvent obj : list) {
							obj.getSoilAnalysesEventPK().setExpId(entity.getExpId());
							newList.add(obj);
						}

						soilAnalyses.setSoilAnalysesEventsCollection(newList);
					}

					em.persist(soilAnalyses);
					treatment.setSoilAnalysesLevel(soilAnalyses);
				}

				treatments.add(treatment);
			}

			entity.setTreatmentsCollection(treatments);
		}
	}
}