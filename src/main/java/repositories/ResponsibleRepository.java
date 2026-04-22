package repositories;

import java.util.List;

import entities.Responsible;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ResponsibleRepository {
	@PersistenceContext(unitName = "todoPU")
	private EntityManager em;

	@Transactional 
	public void createResponsible(Responsible responsible) {
		if (responsible.getId() == null) {
			em.persist(responsible);
		} else {
			em.merge(responsible);
		}
	}

	public List<Responsible> getAllResponsibles() {
		return em.createQuery("SELECT r FROM Responsible r", Responsible.class).getResultList();
	}

	public Responsible findById(Long id) {
		return em.find(Responsible.class, id);
	}

	@Transactional
	public void deleteResponsible(Long id) {
		Responsible responsibleResult = em.find(Responsible.class, id);
		if (responsibleResult != null) {
			em.remove(responsibleResult);
		}
	}

	@Transactional
	public void updateResponsible(Responsible responsible) {
		if (responsible.getId() != null) {
			em.merge(responsible);
		}
	}
}
