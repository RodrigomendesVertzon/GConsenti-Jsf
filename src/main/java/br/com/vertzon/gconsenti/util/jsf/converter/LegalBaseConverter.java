package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.LegalBase;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseRepository;

@Component
@FacesConverter(forClass = LegalBase.class)
public class LegalBaseConverter implements Converter<Object> {

	@Autowired
	private LegalBaseRepository legalBaseRepository;
	
private LegalBase legalBase;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			legalBase = legalBaseRepository.findById(Long.parseLong(value)).get();
		}
		return legalBase;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((LegalBase) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}
