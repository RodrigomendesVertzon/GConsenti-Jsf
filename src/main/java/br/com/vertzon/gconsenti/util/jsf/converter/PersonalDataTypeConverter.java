package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.PersonalDataType;
import br.com.vertzon.gconsenti.domain.repository.PersonalDataTypeRepository;

@Component
@FacesConverter(forClass = PersonalDataType.class)
public class PersonalDataTypeConverter implements Converter<Object> {
	
	@Autowired
	private PersonalDataTypeRepository personalDataTypeRepository;

	private PersonalDataType personalDataType;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			personalDataType = personalDataTypeRepository.findById(Long.parseLong(value)).get();
		}
		return personalDataType;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((PersonalDataType) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}
