package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.Finality;
import br.com.vertzon.gconsenti.domain.repository.FinalityRepository;

@Component
@FacesConverter(forClass = Finality.class)
public class FinalityConverter implements Converter<Object> {

	@Autowired
	private FinalityRepository finalityRepository;

	private Finality finality;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			finality = finalityRepository.findById(Long.parseLong(value)).get();
		}
		return finality;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Finality) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}
