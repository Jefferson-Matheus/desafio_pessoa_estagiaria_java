package controlles;

import java.io.Serializable;
import java.util.List;

import entities.Responsible;
import entities.Task;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import repositories.ResponsibleRepository;
import repositories.TaskRepository;

@Named
@ViewScoped
public class TaskController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TaskRepository taskrepository;

	@Inject
	private ResponsibleRepository responsibleRepository;

	private Task task = new Task();
	private List<Task> tasksList;
	private List<Responsible> resposibleList;
	private Long idFilter;
	private String titleDescriptionFilter;
	private Responsible responsibleFilter;
	private enums.Status statusFilter;

	@PostConstruct
	public void init() {
		list();
	}

	public void list() {
		if (idFilter != null || (titleDescriptionFilter != null && !titleDescriptionFilter.isEmpty())
				|| responsibleFilter != null || statusFilter != null) {
			tasksList = taskrepository.filterTasks(idFilter, titleDescriptionFilter, responsibleFilter, statusFilter);
		} else {
			tasksList = taskrepository.listAll();
		}
		resposibleList = responsibleRepository.getAllResponsibles();
	}

	public List<Task> getAllTasks() {

		return tasksList;
	}

	public void save() {
		taskrepository.saveTask(task);
		task = new Task();
		list();
	}

	public void removeTask(Long id) {
		taskrepository.deleteTask(id);
		list();
	}

	public enums.Priority[] getPriorities() {
		return enums.Priority.values();
	}

	public enums.Status[] getStatuses() {
		return enums.Status.values();
	}

	public void clearFilters() {
		idFilter = null;
		titleDescriptionFilter = null;
		responsibleFilter = null;
		statusFilter = null;
		list();
	}

	public void concludeTask(Long id) {
		taskrepository.concludeTask(id);
		list();
	}

	public void prepareEdit(Task t) {
		this.task = t;
	}

	public Long getIdFilter() {
		return idFilter;
	}

	public void setIdFilter(Long idFilter) {
		this.idFilter = idFilter;
	}

	public String getTitleDescriptionFilter() {
		return titleDescriptionFilter;
	}

	public void setTitleDescriptionFilter(String titleDescriptionFilter) {
		this.titleDescriptionFilter = titleDescriptionFilter;
	}

	public Responsible getResponsibleFilter() {
		return responsibleFilter;
	}

	public void setResponsibleFilter(Responsible responsibleFilter) {
		this.responsibleFilter = responsibleFilter;
	}

	public TaskRepository getTaskrepository() {
		return taskrepository;
	}

	public void setTaskrepository(TaskRepository taskrepository) {
		this.taskrepository = taskrepository;
	}

	public Task getTask() {
		return task;
	}
	
	
	public enums.Status getStatusFilter() {
		return statusFilter;
	}

	public void setStatusFilter(enums.Status statusFilter) {
		this.statusFilter = statusFilter;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Task> getTasksList() {
		return tasksList;
	}

	public void setTasksList(List<Task> tasksList) {
		this.tasksList = tasksList;
	}

	public ResponsibleRepository getResponsibleRepository() {
		return responsibleRepository;
	}

	public void setResponsibleRepository(ResponsibleRepository responsibleRepository) {
		this.responsibleRepository = responsibleRepository;
	}

	public List<Responsible> getResposibleList() {
		return resposibleList;
	}

	public void setResposibleList(List<Responsible> resposibleList) {
		this.resposibleList = resposibleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
