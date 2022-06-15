package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.ErrorMessage;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;

@Component
@FacesConverter(forClass=ErrorMessage.class)
public class ErrorMessageConverter implements Converter<Object>{
	
	@Autowired
	private ErrorMessageRepository errorMessageRepository;
	
	private ErrorMessage errorMessage;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			errorMessage = errorMessageRepository.findById(Long.parseLong(value)).get();
		}
		return errorMessage;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((ErrorMessage) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}

}
