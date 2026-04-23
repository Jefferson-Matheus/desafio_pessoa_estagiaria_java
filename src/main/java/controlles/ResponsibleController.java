package controlles;

import java.io.Serializable;
import java.util.List;

import entities.Responsible;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import repositories.ResponsibleRepository;

@Named
@ViewScoped
public class ResponsibleController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ResponsibleRepository responsibleRepository;

	private Responsible responsible = new Responsible();
	private List<Responsible> listResponsibles;

	@PostConstruct
	public void init() {
		list();
	}

	public void list() {
		listResponsibles = responsibleRepository.getAllResponsibles();
	}

	public List<Responsible> listAll() {
		return listResponsibles;
	}

	public void save() {
		responsibleRepository.saveResponsible(responsible);
		responsible = new Responsible();
		list();
	}

	public void removeResponsible(Long id) {
		responsibleRepository.deleteResponsible(id);
		list();
	}
	
	public void prepareEdit(Responsible r) {
        this.responsible = r;
    }
	
	public void cancel() {
		responsible = new Responsible();
	}

	public ResponsibleRepository getResponsibleRepository() {
		return responsibleRepository;
	}

	public void setResponsibleRepository(ResponsibleRepository responsibleRepository) {
		this.responsibleRepository = responsibleRepository;
	}

	public Responsible getResponsible() {
		return responsible;
	}

	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;
	}

	public List<Responsible> getListResponsibles() {
		return listResponsibles;
	}

	public void setListResponsibles(List<Responsible> listResponsibles) {
		this.listResponsibles = listResponsibles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
