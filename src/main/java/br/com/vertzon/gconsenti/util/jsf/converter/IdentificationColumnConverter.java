package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.IdentificationColumn;
import br.com.vertzon.gconsenti.domain.repository.IdentificationColumnRepository;

@Component
@FacesConverter(forClass=IdentificationColumn.class)
public class IdentificationColumnConverter implements Converter<Object> {

	@Autowired
	private IdentificationColumnRepository identificationColumnRepository;
	
	private IdentificationColumn identificationColumn;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			identificationColumn = identificationColumnRepository.findById(Long.parseLong(value)).get();
		}
		return identificationColumn;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((IdentificationColumn) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}