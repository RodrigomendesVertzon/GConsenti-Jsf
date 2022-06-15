package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.LegalBaseFinality;
import br.com.vertzon.gconsenti.domain.repository.LegalBaseFinalityRepository;

@Component
@FacesConverter(forClass=LegalBaseFinality.class)
public class LegalBaseFinalityConverter implements Converter<Object> {

	@Autowired
	private LegalBaseFinalityRepository legalBaseFinalityRepository;
	private LegalBaseFinality legalBaseFinality = new LegalBaseFinality();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			legalBaseFinality = legalBaseFinalityRepository.findById(Long.parseLong(value)).get();
		}
		return legalBaseFinality;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((LegalBaseFinality) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}

}
