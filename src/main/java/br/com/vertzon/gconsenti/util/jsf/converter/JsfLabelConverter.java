package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;

@Component
@FacesConverter(forClass=JsfLabel.class)
public class JsfLabelConverter implements Converter<Object> {
	
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private JsfLabel jsfLabel;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			jsfLabel = jsfLabelRepository.findById(Long.parseLong(value)).get();
		}
		return jsfLabel;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((JsfLabel) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}
