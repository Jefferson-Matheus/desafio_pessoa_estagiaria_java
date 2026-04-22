package repositories;

import java.util.List;

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
	public void createTask(Task task) {
		if (task.getId() == null) {
			em.persist(task);
		} else {
			em.merge(task);
		}
	}

	@Transactional
	public void updateTask(Task task) {
		if (task.getId() != null) {
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

	public List<Task> listarPendentes() {
		String jpql = "SELECT t FROM Task t WHERE t.status_task = enums.Status.EM_ANDAMENTO ORDER BY t.deadline ASC";
		return em.createQuery(jpql, Task.class).getResultList();
	}
}
