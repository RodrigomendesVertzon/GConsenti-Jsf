package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.ScanSchedule;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.ScanScheduleRepository;
import br.com.vertzon.gconsenti.domain.service.ScanScheduleService;

@Component
@Scope("view")
public class SearchScanScheduleBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;
	
	@Autowired
	private ScanScheduleRepository scanScheduleRepository;
	@Autowired
	private ScanScheduleService scanScheduleService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private String selectedScans;
	
	private List<ScanSchedule> scanSchedules = new ArrayList<ScanSchedule>();
	private ScanSchedule scanSchedule;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	private List<JsfLabel> scanScheduleObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> scanScheduleLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		scanScheduleObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.SCAN_SCHEDULE_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel scanScheduleLabel : scanScheduleObjects) {
			scanScheduleLabels.add(scanScheduleLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void search() {
		if (this.scanSchedule != null) {
			this.scanSchedules = this.scanScheduleService.search(scanSchedule);
		} else {
			this.clear();
		}
	}
	
	public void clear() {
		this.selectedScans = "ÚNICOS";
		this.scanSchedules = this.scanScheduleRepository.findAll();
		this.scanSchedules.forEach(scanSchedl -> {
			scanSchedl.setStringDate(formatter.format(scanSchedl.getDatetime()));
		});
		this.scanSchedule = new ScanSchedule();
	}
	
	public void delete() {
		try {
			scanScheduleService.delete(scanSchedule.getId());
			scanSchedules.remove(scanSchedule);
		} catch (BusinessRuleException e) {
			e.getMessage();
		}
	}
	
	public List<ScanSchedule> getScanSchedules() {
		return scanSchedules;
	}
	public void setScanSchedules(List<ScanSchedule> ScanSchedules) {
		this.scanSchedules = ScanSchedules;
	}

	public ScanSchedule getScanSchedule() {
		return scanSchedule;
	}
	public void setScanSchedule(ScanSchedule ScanSchedule) {
		this.scanSchedule = ScanSchedule;
	}

	public String getSelectedScans() {
		return selectedScans;
	}

	public void setSelectedScans(String selectedScans) {
		this.selectedScans = selectedScans;
	}

	public List<String> getScanScheduleLabels() {
		return scanScheduleLabels;
	}

	public void setScanScheduleLabels(List<String> scanScheduleLabels) {
		this.scanScheduleLabels = scanScheduleLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
	
	
}
