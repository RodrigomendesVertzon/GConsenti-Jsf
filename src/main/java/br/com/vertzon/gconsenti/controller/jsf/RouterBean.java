package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class RouterBean implements Serializable {
	private static final long serialVersionUID = -7683662393708472582L;
		
	// PAGES
	private String searchAppConfigurationsPage = "/secure/admin/searchappconfigurations.xhtml";
	private String registerAppConfigurationPage = "/secure/admin/registerappconfiguration.xhtml";

	private String searchDatabaseTypesPage = "/secure/admin/searchdatabasetypes.xhtml";
	private String registerDatabaseTypePage = "/secure/admin/registerdatabasetype.xhtml";

	private String searchDatasourcesPage = "/secure/admin/searchdatasources.xhtml";
	private String registerDatasourcePage = "/secure/admin/registerdatasource.xhtml";

	private String searchFilesourcesPage = "/secure/admin/searchfilesources.xhtml";
	private String registerFilesourcePage = "/secure/admin/registerfilesource.xhtml";
	
	private String searchFinalitiesPage = "/secure/admin/searchfinalities.xhtml";
	private String registerFinalityPage = "/secure/admin/registerfinality.xhtml";
	
	private String searchIdentificationColumnsPage = "/secure/admin/searchidentificationcolumns.xhtml";
	private String registerIdentificationColumnPage = "/secure/admin/registeridentificationcolumn.xhtml";

	private String searchIdentificationFieldsPage = "/secure/admin/searchidentificationfields.xhtml";
	private String registerIdentificationFieldPage = "/secure/admin/registeridentificationfield.xhtml";

	private String searchLearningDatasPage = "/secure/admin/searchlearningdatas.xhtml";
	private String registerLearningDataPage = "/secure/admin/registerlearningdata.xhtml";
	
	private String searchLearningRegexesPage = "/secure/admin/searchlearningregexes.xhtml";
	private String registerLearningRegexPage = "/secure/admin/registerlearningregex.xhtml";
	
	private String searchLearningScriptsPage = "/secure/admin/searchlearningscripts.xhtml";
	private String registerLearningScriptPage = "/secure/admin/registerlearningscript.xhtml";
	
	private String searchLegalBasesPage = "/secure/admin/searchlegalbases.xhtml";
	private String registerLegalBasePage = "/secure/admin/registerlegalbase.xhtml";

	private String searchLegalBaseFinalitiesPage = "/secure/admin/searchlegalbasefinalities.xhtml";
	private String registerLegalBaseFinalityPage = "/secure/admin/registerlegalbasefinality.xhtml";
	
	private String searchPersonalDataTypesPage = "/secure/admin/searchpersonaldatatypes.xhtml";
	private String registerPersonalDataTypePage = "/secure/admin/registerpersonaldatatype.xhtml";
	
	private String searchScanSchedulesPage = "/secure/admin/searchscanschedules.xhtml";
	private String registerScanSchedulePage = "/secure/admin/registerscanschedule.xhtml";
	
	private String searchUsersPage = "/secure/admin/searchusers.xhtml";
	private String registerUserPage = "/secure/admin/registeruser.xhtml";
	
	private String searchPersonalDataLocationsPage = "/secure/auditor/searchpersonaldatalocations.xhtml";
	
	private String searchErrorMessagesPage = "/secure/admin/searcherrormessages.xhtml";
	private String registerErrorMessagePage = "/secure/admin/registererrormessages.xhtml";
	
	private String searchJsfLabelsPage = "/secure/admin/searchjsflabels.xhtml";
	private String registerJsfLabelPage = "/secure/admin/registerjsflabel.xhtml";
	
	public String getSearchAppConfigurationsPage() {
		return searchAppConfigurationsPage;
	}
	public void setSearchAppConfigurationsPage(String searchAppConfigurationsPage) {
		this.searchAppConfigurationsPage = searchAppConfigurationsPage;
	}
	public String getRegisterAppConfigurationPage() {
		return registerAppConfigurationPage;
	}
	public void setRegisterAppConfigurationPage(String registerAppConfigurationPage) {
		this.registerAppConfigurationPage = registerAppConfigurationPage;
	}
	public String getSearchDatabaseTypesPage() {
		return searchDatabaseTypesPage;
	}
	public void setSearchDatabaseTypesPage(String searchDatabaseTypesPage) {
		this.searchDatabaseTypesPage = searchDatabaseTypesPage;
	}
	public String getRegisterDatabaseTypePage() {
		return registerDatabaseTypePage;
	}
	public void setRegisterDatabaseTypePage(String registerDatabaseTypePage) {
		this.registerDatabaseTypePage = registerDatabaseTypePage;
	}
	public String getSearchDatasourcesPage() {
		return searchDatasourcesPage;
	}
	public void setSearchDatasourcesPage(String searchDatasourcesPage) {
		this.searchDatasourcesPage = searchDatasourcesPage;
	}
	public String getRegisterDatasourcePage() {
		return registerDatasourcePage;
	}
	public void setRegisterDatasourcePage(String registerDatasourcePage) {
		this.registerDatasourcePage = registerDatasourcePage;
	}
	public String getSearchFilesourcesPage() {
		return searchFilesourcesPage;
	}
	public void setSearchFilesourcesPage(String searchFilesourcesPage) {
		this.searchFilesourcesPage = searchFilesourcesPage;
	}
	public String getRegisterFilesourcePage() {
		return registerFilesourcePage;
	}
	public void setRegisterFilesourcePage(String registerFilesourcePage) {
		this.registerFilesourcePage = registerFilesourcePage;
	}
	public String getSearchFinalitiesPage() {
		return searchFinalitiesPage;
	}
	public void setSearchFinalitiesPage(String searchFinalitiesPage) {
		this.searchFinalitiesPage = searchFinalitiesPage;
	}
	public String getRegisterFinalityPage() {
		return registerFinalityPage;
	}
	public void setRegisterFinalityPage(String registerFinalityPage) {
		this.registerFinalityPage = registerFinalityPage;
	}
	public String getSearchIdentificationColumnsPage() {
		return searchIdentificationColumnsPage;
	}
	public void setSearchIdentificationColumnsPage(String searchIdentificationColumnsPage) {
		this.searchIdentificationColumnsPage = searchIdentificationColumnsPage;
	}
	public String getRegisterIdentificationColumnPage() {
		return registerIdentificationColumnPage;
	}
	public void setRegisterIdentificationColumnPage(String registerIdentificationColumnPage) {
		this.registerIdentificationColumnPage = registerIdentificationColumnPage;
	}
	public String getSearchIdentificationFieldsPage() {
		return searchIdentificationFieldsPage;
	}
	public void setSearchIdentificationFieldsPage(String searchIdentificationFieldsPage) {
		this.searchIdentificationFieldsPage = searchIdentificationFieldsPage;
	}
	public String getRegisterIdentificationFieldPage() {
		return registerIdentificationFieldPage;
	}
	public void setRegisterIdentificationFieldPage(String registerIdentificationFieldPage) {
		this.registerIdentificationFieldPage = registerIdentificationFieldPage;
	}
	public String getSearchLearningDatasPage() {
		return searchLearningDatasPage;
	}
	public void setSearchLearningDatasPage(String searchLearningDatasPage) {
		this.searchLearningDatasPage = searchLearningDatasPage;
	}
	public String getRegisterLearningDataPage() {
		return registerLearningDataPage;
	}
	public void setRegisterLearningDataPage(String registerLearningDataPage) {
		this.registerLearningDataPage = registerLearningDataPage;
	}
	public String getSearchLearningRegexesPage() {
		return searchLearningRegexesPage;
	}
	public void setSearchLearningRegexesPage(String searchLearningRegexesPage) {
		this.searchLearningRegexesPage = searchLearningRegexesPage;
	}
	public String getRegisterLearningRegexPage() {
		return registerLearningRegexPage;
	}
	public void setRegisterLearningRegexPage(String registerLearningRegexPage) {
		this.registerLearningRegexPage = registerLearningRegexPage;
	}
	public String getSearchLearningScriptsPage() {
		return searchLearningScriptsPage;
	}
	public void setSearchLearningScriptsPage(String searchLearningScriptsPage) {
		this.searchLearningScriptsPage = searchLearningScriptsPage;
	}
	public String getRegisterLearningScriptPage() {
		return registerLearningScriptPage;
	}
	public void setRegisterLearningScriptPage(String registerLearningScriptPage) {
		this.registerLearningScriptPage = registerLearningScriptPage;
	}
	public String getSearchLegalBasesPage() {
		return searchLegalBasesPage;
	}
	public void setSearchLegalBasesPage(String searchLegalBasesPage) {
		this.searchLegalBasesPage = searchLegalBasesPage;
	}
	public String getRegisterLegalBasePage() {
		return registerLegalBasePage;
	}
	public void setRegisterLegalBasePage(String registerLegalBasePage) {
		this.registerLegalBasePage = registerLegalBasePage;
	}
	public String getSearchLegalBaseFinalitiesPage() {
		return searchLegalBaseFinalitiesPage;
	}
	public void setSearchLegalBaseFinalitiesPage(String searchLegalBaseFinalitiesPage) {
		this.searchLegalBaseFinalitiesPage = searchLegalBaseFinalitiesPage;
	}
	public String getRegisterLegalBaseFinalityPage() {
		return registerLegalBaseFinalityPage;
	}
	public void setRegisterLegalBaseFinalityPage(String registerLegalBaseFinalityPage) {
		this.registerLegalBaseFinalityPage = registerLegalBaseFinalityPage;
	}
	public String getSearchPersonalDataTypesPage() {
		return searchPersonalDataTypesPage;
	}
	public void setSearchPersonalDataTypesPage(String searchPersonalDataTypesPage) {
		this.searchPersonalDataTypesPage = searchPersonalDataTypesPage;
	}
	public String getRegisterPersonalDataTypePage() {
		return registerPersonalDataTypePage;
	}
	public void setRegisterPersonalDataTypePage(String registerPersonalDataTypePage) {
		this.registerPersonalDataTypePage = registerPersonalDataTypePage;
	}
	public String getSearchScanSchedulesPage() {
		return searchScanSchedulesPage;
	}
	public void setSearchScanSchedulesPage(String searchScanSchedulesPage) {
		this.searchScanSchedulesPage = searchScanSchedulesPage;
	}
	public String getRegisterScanSchedulePage() {
		return registerScanSchedulePage;
	}
	public void setRegisterScanSchedulePage(String registerScanSchedulePage) {
		this.registerScanSchedulePage = registerScanSchedulePage;
	}
	public String getSearchUsersPage() {
		return searchUsersPage;
	}
	public void setSearchUsersPage(String searchUsersPage) {
		this.searchUsersPage = searchUsersPage;
	}
	public String getRegisterUserPage() {
		return registerUserPage;
	}
	public void setRegisterUserPage(String registerUserPage) {
		this.registerUserPage = registerUserPage;
	}
	
	public String getSearchPersonalDataLocationsPage() {
		return searchPersonalDataLocationsPage;
	}
	public void setSearchPersonalDataLocationsPage(String searchPersonalDataLocationsPage) {
		this.searchPersonalDataLocationsPage = searchPersonalDataLocationsPage;
	}
	public String getSearchErrorMessagesPage() {
		return searchErrorMessagesPage;
	}
	public void setSearchErrorMessagesPage(String searchErrorMessagesPage) {
		this.searchErrorMessagesPage = searchErrorMessagesPage;
	}
	public String getRegisterErrorMessagePage() {
		return registerErrorMessagePage;
	}
	public void setRegisterErrorMessagePage(String registerErrorMessagePage) {
		this.registerErrorMessagePage = registerErrorMessagePage;
	}
	public String getSearchJsfLabelsPage() {
		return searchJsfLabelsPage;
	}
	public void setSearchJsfLabelsPage(String searchJsfLabelsPage) {
		this.searchJsfLabelsPage = searchJsfLabelsPage;
	}
	public String getRegisterJsfLabelPage() {
		return registerJsfLabelPage;
	}
	public void setRegisterJsfLabelPage(String registerJsfLabelPage) {
		this.registerJsfLabelPage = registerJsfLabelPage;
	}
}
