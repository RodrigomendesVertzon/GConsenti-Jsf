package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.LearningScript;
import br.com.vertzon.gconsenti.domain.repository.LearningScriptRepository;

@Component
@FacesConverter(forClass = LearningScript.class)
public class LearningScriptConverter implements Converter<Object> {
	
	@Autowired
	private LearningScriptRepository learningScriptRepository;

private LearningScript learningScript;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			learningScript = learningScriptRepository.findById(Long.parseLong(value)).get();
		}
		return learningScript;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((LearningScript) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}
