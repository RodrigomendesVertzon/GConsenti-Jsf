package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.DatabaseType;
import br.com.vertzon.gconsenti.domain.repository.DatabaseTypeRepository;

@Component
@FacesConverter(forClass=DatabaseType.class)
public class DatabaseTypeConverter implements Converter<Object> {

	@Autowired
	private DatabaseTypeRepository databaseTypeRepository;
	
	private DatabaseType databaseType = new DatabaseType();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			databaseType = databaseTypeRepository.findById(Long.parseLong(value)).get();
		}
		return databaseType;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((DatabaseType) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}

}