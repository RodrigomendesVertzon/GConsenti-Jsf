package br.com.vertzon.gconsenti.domain.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "learning_datas")
public class LearningData implements Serializable {

	private static final long serialVersionUID = -5883391653678223276L;

/////////////////////////////////////////////////////
///////////////////////////// ATTRIBUTES
/////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@Column(name = "name")
	private String name;

	@Nullable
	@Column(name = "description")
	private String description;

	@NonNull
	@Column(name = "table_name")
	private String tableName;

	@NonNull
	@Column(name = "decision_column")
	private String decisionColumn;

	@NonNull
	@Column(name = "class_column")
	private String classColumn;

	@NonNull
	@Column(name = "use_idf")
	private boolean useIdf;

	@NonNull
	@Column(name = "use_stemmer")
	private boolean useStemmer;

	@NonNull
	@Column(name = "minimum_term_frequency")
	private int minimumTermFrequency;

	@NonNull
	@Column(name = "minimum_tokenizer_steps")
	private int minimumTokenizerSteps;

	@NonNull
	@Column(name = "maximum_tokenizer_steps")
	private int maximumTokenizerSteps;

	@NonNull
	@Column(name = "words_to_keep")
	private int wordsToKeep;

	@NonNull
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_datasource")
	private Datasource datasource;

	@NonNull
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_personal_data_type")
	private PersonalDataType personalDataType;
	
	@Transient
	private transient boolean editing;
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////
///////////////////////////// GETTERS & SETTERS
/////////////////////////////////////////////////////
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDecisionColumn() {
		return decisionColumn;
	}

	public void setDecisionColumn(String decisionColumn) {
		this.decisionColumn = decisionColumn;
	}

	public String getClassColumn() {
		return classColumn;
	}

	public void setClassColumn(String classColumn) {
		this.classColumn = classColumn;
	}

	public boolean isUseIdf() {
		return useIdf;
	}

	public void setUseIdf(boolean useIdf) {
		this.useIdf = useIdf;
	}

	public boolean isUseStemmer() {
		return useStemmer;
	}

	public void setUseStemmer(boolean useStemmer) {
		this.useStemmer = useStemmer;
	}

	public int getMinimumTermFrequency() {
		return minimumTermFrequency;
	}

	public void setMinimumTermFrequency(int minimumTermFrequency) {
		this.minimumTermFrequency = minimumTermFrequency;
	}

	public int getMinimumTokenizerSteps() {
		return minimumTokenizerSteps;
	}

	public void setMinimumTokenizerSteps(int minimumTokenizerSteps) {
		this.minimumTokenizerSteps = minimumTokenizerSteps;
	}

	public int getMaximumTokenizerSteps() {
		return maximumTokenizerSteps;
	}

	public void setMaximumTokenizerSteps(int maximumTokenizerSteps) {
		this.maximumTokenizerSteps = maximumTokenizerSteps;
	}

	public int getWordsToKeep() {
		return wordsToKeep;
	}

	public void setWordsToKeep(int wordsToKeep) {
		this.wordsToKeep = wordsToKeep;
	}

	public Datasource getDatasource() {
		return datasource;
	}

	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
	}

	public PersonalDataType getPersonalDataType() {
		return personalDataType;
	}

	public void setPersonalDataType(PersonalDataType personalDataType) {
		this.personalDataType = personalDataType;
	}
	
	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////
///////////////////////////// HASHCODE & EQUALS
/////////////////////////////////////////////////////
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LearningData other = (LearningData) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

}
