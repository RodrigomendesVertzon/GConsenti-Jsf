package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.Datasource;
import br.com.vertzon.gconsenti.domain.model.Filesource;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.ScanSchedule;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.model.enumerator.ScanScheduleIntervalEnum;
import br.com.vertzon.gconsenti.domain.model.enumerator.ScanScheduleStatus;
import br.com.vertzon.gconsenti.domain.repository.DatasourceRepository;
import br.com.vertzon.gconsenti.domain.repository.FilesourceRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.ScanScheduleService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class RegisterScanScheduleBean implements Serializable {
	private static final long serialVersionUID = 7274054190849026027L;

	@Autowired
	private ScanScheduleService scanScheduleService;
	@Autowired
	private DatasourceRepository datasourceRepository;
	@Autowired
	private FilesourceRepository filesourceRepository;
	@Autowired
	private RouterBean router;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;

	private ScanSchedule scanSchedule;
	private List<Datasource> datasources;
	private List<Filesource> filesources;
	private String date;
	private String scanType;
	private LocalDateTime datetime = LocalDateTime.now();
	private List<String> labels = new ArrayList<String>();
	private List<JsfLabel> jsfLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		this.scanSchedule = new ScanSchedule();
		this.datasources = this.datasourceRepository.findAll();
		this.filesources = this.filesourceRepository.findAll();
		datetime = LocalDateTime.now();
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.SCAN_SCHEDULE_DATA_REG);
		jsfLabels.addAll(jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.SCAN_SCHEDULE_FILE_REG));
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
	}

	public void register() throws BusinessRuleException {
		try {
			this.scanSchedule.setStatus(ScanScheduleStatus.AGUARDANDO);
			this.scanSchedule.setType(ScanScheduleIntervalEnum.getValueOfLabel(scanType));
			this.scanSchedule.setDatetime(datetime);
			this.scanScheduleService.register(scanSchedule);
			FacesUtil.submitSuccess("A varredura foi agendada com sucesso!", router.getSearchScanSchedulesPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public ScanSchedule getScanSchedule() {
		return scanSchedule;
	}

	public void setScanSchedule(ScanSchedule scanSchedule) {
		this.scanSchedule = scanSchedule;
	}

	public List<Datasource> getDatasources() {
		return datasources;
	}

	public void setDatasources(List<Datasource> datasources) {
		this.datasources = datasources;
	}

	public List<Filesource> getFilesources() {
		return filesources;
	}

	public void setFilesources(List<Filesource> filesources) {
		this.filesources = filesources;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
}
