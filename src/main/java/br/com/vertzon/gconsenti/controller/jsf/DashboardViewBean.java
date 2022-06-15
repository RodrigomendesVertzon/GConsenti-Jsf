package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.repository.DatasourceRepository;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataLocationRepository;
import br.com.vertzon.gconsenti.domain.repository.UserRepository;

@Component
@Scope("view")
public class DashboardViewBean implements Serializable {
	private static final long serialVersionUID = 879717063599342365L;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PersonalDataLocationRepository personalDataLocationRepository;
	@Autowired
	private DatasourceRepository datasourceRepository;

	private String emptyChart = "N/A";
	private String selectedStatistics;

	private PieChartModel dataPerType;
	private PieChartModel totalDataPerDatasource;
	private PieChartModel identifiedColumnsPerDatasource;
	private PieChartModel identifiedDataPerDatasource;

	private Long numberOfDatasources;
	private Long dataTypesFound;
	private Long completedScans;
	private Long totalDataRead;

	private String lastScanTime;
	private String scanTime;
	private String scanDate;

	private String username;
	
	@PostConstruct
	public void init() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getName();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		this.username = userRepository.findByEmail(username).get().getName();
		this.selectedStatistics = "DADOS";
		this.numberOfDatasources = datasourceRepository.count();
		this.dataTypesFound = personalDataLocationRepository.countPerType();

		retrievePerDataType();
		retrieveTotalDataPerDatasource();
	}

	private void retrievePerDataType() {
		dataPerType = new PieChartModel();
		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();
		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		List<String> bgColors = new ArrayList<>();

		List<Object[]> objects = this.personalDataLocationRepository.findDataPerType();
		if (!objects.isEmpty()) {
			for (int i = 0; i < objects.size(); i++) {
				Object[] object = objects.get(i);
				int valor = Integer.parseInt(object[0].toString());
				String label = object[1].toString();
				values.add(valor);
				labels.add(label);
				bgColors.add(getRandomRgb());
			}
		} else {
			values.add(null);
			labels.add(emptyChart);
		}

		dataSet.setData(values);
		data.setLabels(labels);
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		dataPerType.setData(data);
	}

	private void retrieveTotalDataPerDatasource() {
		totalDataPerDatasource = new PieChartModel();
		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();
		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		List<String> bgColors = new ArrayList<>();

		dataSet.setData(values);
		data.setLabels(labels);
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		totalDataPerDatasource.setData(data);
	}

	public String getRandomRgb() {
		Random random = new Random();
		int low = 0;
		int high = 255;
		int r = 0, g = 0, b = 0;
		for (int i = 0; i < 3; i++) {
			int result = random.nextInt(high - low) + low;

			if (i == 1) {
				r = result;
			} else if (i == 2) {
				g = result;
			} else {
				b = result;
			}
		}

		return "rgb( " + r + "," + b + "," + g + ")";
	}

	public void trimDate() {
		int index = this.lastScanTime.indexOf("T");
		if (index != -1) {
			this.scanDate = this.lastScanTime.substring(0, index);
			this.scanDate = this.scanDate.replace("-", "/");
			this.scanTime = this.lastScanTime.substring(index + 1, this.lastScanTime.length());
		}
	}

	public PieChartModel getDataPerType() {
		return dataPerType;
	}

	public void setDataPerType(PieChartModel dataPerType) {
		this.dataPerType = dataPerType;
	}

	public PieChartModel getTotalDataPerDatasource() {
		return totalDataPerDatasource;
	}

	public void setTotalDataPerDatasource(PieChartModel totalDataPerDatasource) {
		this.totalDataPerDatasource = totalDataPerDatasource;
	}

	public PieChartModel getIdentifiedColumnsPerDatasource() {
		return identifiedColumnsPerDatasource;
	}

	public void setIdentifiedColumnsPerDatasource(PieChartModel identifiedColumnsPerDatasource) {
		this.identifiedColumnsPerDatasource = identifiedColumnsPerDatasource;
	}

	public PieChartModel getIdentifiedDataPerDatasource() {
		return identifiedDataPerDatasource;
	}

	public void setIdentifiedDataPerDatasource(PieChartModel identifiedDataPerDatasource) {
		this.identifiedDataPerDatasource = identifiedDataPerDatasource;
	}

	public String getSelectedStatistics() {
		return selectedStatistics;
	}

	public void setSelectedStatistics(String selectedStatistics) {
		this.selectedStatistics = selectedStatistics;
	}

	public Long getNumberOfDatasources() {
		return numberOfDatasources;
	}

	public void setNumberOfDatasources(Long numberOfDatasources) {
		this.numberOfDatasources = numberOfDatasources;
	}

	public Long getDataTypesFound() {
		return dataTypesFound;
	}

	public void setDataTypesFound(Long dataTypesFound) {
		this.dataTypesFound = dataTypesFound;
	}

	public Long getCompletedScans() {
		return completedScans;
	}

	public void setCompletedScans(Long completedScans) {
		this.completedScans = completedScans;
	}

	public Long getTotalDataRead() {
		return totalDataRead;
	}

	public void setTotalDataRead(Long totalDataRead) {
		this.totalDataRead = totalDataRead;
	}

	public String getScanTime() {
		return scanTime;
	}

	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public String getUsername() {
		return username;
	}
}
