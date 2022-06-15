package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.DatabaseType;
import br.com.vertzon.gconsenti.domain.model.Datasource;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.LegalBaseFinality;
import br.com.vertzon.gconsenti.domain.model.enumerator.ForDataLearningEnum;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.DatabaseTypeRepository;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseFinalityRepository;
import br.com.vertzon.gconsenti.domain.service.DatasourceService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@ViewScoped
public class RegisterDatasourceBean implements Serializable {
	private static final long serialVersionUID = 7274054190849026027L;

	@Autowired
	private DatasourceService datasourceService;
	@Autowired
	private DatabaseTypeRepository databaseTypeRepository;
	@Autowired
	private LegalBaseFinalityRepository legalBaseFinalityRepository;
	@Autowired
	private RouterBean router;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;

	private Datasource datasource;
	private List<DatabaseType> databaseTypes;
	private List<LegalBaseFinality> legalBaseFinalities;
	private boolean dataLearning;
	private String url = "";
	private List<String> labels = new ArrayList<String>();
	private List<JsfLabel> jsfLabels = new ArrayList<>();

	@PostConstruct
	public void init() {
		datasource = new Datasource();
		databaseTypes = databaseTypeRepository.findAll();
		legalBaseFinalities = legalBaseFinalityRepository.findAll();
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.DATASOURCE_REG);
			
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
	}

	public void register() throws BusinessRuleException {
		try {

			if (dataLearning) {
				datasource.setDataLearning(ForDataLearningEnum.DATA_LEARNING);
			} else {
				datasource.setDataLearning(ForDataLearningEnum.NOT_DATA_LEARNING);
			}

			if (datasource.getDatabaseType() != null && datasource.getIp() != null && datasource.getPort() != null
					&& datasource.getName() != null) {

				if (datasource.getDatabaseType().getDriver().equals("com.mysql.cj.jdbc.Driver")) {
					url = datasource.getDatabaseType().getPrefix() + datasource.getIp() + datasource.getPort() + "/"
							+ datasource.getName();
				}

				if (this.datasource.getDatabaseType().getDriver()
						.equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
					url = datasource.getDatabaseType().getPrefix() + datasource.getIp() + datasource.getPort()
							+ ";databaseName=" + datasource.getName();
				}

				if (this.datasource.getDatabaseType().getDriver().equals("oracle.jdbc.driver.OracleDriver")) {
					url = datasource.getDatabaseType().getPrefix() + datasource.getIp() + datasource.getPort()
							+ ":" + datasource.getName();
				}
				
				if (this.datasource.getDatabaseType().getDriver().equals("mongodb.jdbc.MongoDriver")) {
					url = datasource.getDatabaseType().getPrefix() + datasource.getIp() + datasource.getPort(); 
							//+ "?authorizationDatabase=" + datasource.getAuthorizationDatabase();
				}
			}
			datasource.setUrl(url);
			datasourceService.register(datasource);
			FacesUtil.submitSuccess("A base de dados foi cadastrada com sucesso!", router.getSearchDatasourcesPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Datasource getDatasource() {
		return datasource;
	}

	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
	}

	public List<DatabaseType> getDatabaseTypes() {
		return databaseTypes;
	}

	public void setDatabaseTypes(List<DatabaseType> databaseTypes) {
		this.databaseTypes = databaseTypes;
	}

	public List<LegalBaseFinality> getLegalBaseFinalities() {
		return legalBaseFinalities;
	}

	public void setLegalBaseFinalities(List<LegalBaseFinality> legalBaseFinalities) {
		this.legalBaseFinalities = legalBaseFinalities;
	}

	public boolean isDataLearning() {
		return dataLearning;
	}

	public void setDataLearning(boolean dataLearning) {
		this.dataLearning = dataLearning;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	
}
