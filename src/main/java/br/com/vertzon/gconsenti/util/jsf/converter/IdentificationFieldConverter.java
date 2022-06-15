package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.IdentificationField;
import br.com.vertzon.gconsenti.domain.repository.IdentificationFieldRepository;

@Component
@FacesConverter(forClass=IdentificationField.class)
public class IdentificationFieldConverter implements Converter<Object> {

	@Autowired
	private IdentificationFieldRepository identificationFieldRepository;
	
	private IdentificationField identificationField;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			identificationField = identificationFieldRepository.findById(Long.valueOf(value)).get();
		}
		return identificationField;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((IdentificationField) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}