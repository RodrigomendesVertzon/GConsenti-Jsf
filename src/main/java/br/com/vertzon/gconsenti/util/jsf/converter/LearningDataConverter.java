package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.LearningData;
import br.com.vertzon.gconsenti.domain.repository.LearningDataRepository;

@Component
@FacesConverter(forClass = LearningData.class)
public class LearningDataConverter implements Converter<Object> {
	
	@Autowired
	private LearningDataRepository learningDataRepository;

private LearningData learningData;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			learningData = learningDataRepository.findById(Long.parseLong(value)).get();
		}
		return learningData;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((LearningData) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}