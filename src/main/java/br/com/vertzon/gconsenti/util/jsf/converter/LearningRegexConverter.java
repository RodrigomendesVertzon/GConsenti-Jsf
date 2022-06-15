package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.LearningRegex;
import br.com.vertzon.gconsenti.domain.repository.LearningRegexRepository;

@Component
@FacesConverter(forClass = LearningRegex.class)
public class LearningRegexConverter implements Converter<Object> {
	
	@Autowired
	private LearningRegexRepository learningRegexRepository;

private LearningRegex learningRegex;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			learningRegex = learningRegexRepository.findById(Long.parseLong(value)).get();
		}
		return learningRegex;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((LearningRegex) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}
