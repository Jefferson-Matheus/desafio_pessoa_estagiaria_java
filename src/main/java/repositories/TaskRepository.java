package repositories;

import java.util.List;

import entities.Responsible;
import entities.Task;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TaskRepository {
	@PersistenceContext(unitName = "todoPU")
	private EntityManager em;

	@Transactional
	public void saveTask(Task task) {
		if (task.getId() == null) {
			em.persist(task);
		} else {
			em.merge(task);
		}
	}

	@Transactional
	public void deleteTask(Long id) {
		Task taskResult = em.find(Task.class, id);
		if (taskResult != null) {
			em.remove(taskResult);
		}
	}

	@Transactional
	public void concludeTask(Long id) {
		Task taskResult = em.find(Task.class, id);
		if (taskResult != null) {
			taskResult.setStatusTask(enums.Status.CONCLUIDA);
			em.merge(taskResult);
		}
	}

	public List<Task> listAll() {
		String jpql = "SELECT t FROM Task t WHERE t.statusTask = enums.Status.EM_ANDAMENTO ORDER BY t.id ASC";
		return em.createQuery(jpql, Task.class).getResultList();
	}

	public List<Task> filterTasks(Long id, String titleDesctiption, Responsible responsible, enums.Status status) {
		StringBuilder jpql = new StringBuilder("SELECT t FROM Task t WHERE 1=1 ");

		if (id != null) {
			jpql.append("AND t.id = :id ");
		}

		if (status != null) {
			jpql.append("AND t.statusTask = :status ");
		}

		if (titleDesctiption != null && !titleDesctiption.isEmpty()) {
			jpql.append("AND (LOWER(t.title) LIKE :search OR LOWER(t.description) LIKE :search) ");
		}

		if (responsible != null) {
			jpql.append("AND t.responsible = :resp ");
		}

		jpql.append("ORDER BY t.id ASC");

		var query = em.createQuery(jpql.toString(), Task.class);

		if (id != null) {
			query.setParameter("id", id);
		}

		if (titleDesctiption != null && !titleDesctiption.isEmpty()) {
			query.setParameter("search", "%" + titleDesctiption.toLowerCase() + "%");
		}

		if (responsible != null) {
			query.setParameter("resp", responsible);
		}

		if (status != null)
			query.setParameter("status", status);

		return query.getResultList();
	}

}
