package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.AppConfiguration;
import br.com.vertzon.gconsenti.domain.repository.AppConfigurationRepository;

@Component
@FacesConverter(forClass=AppConfiguration.class)
public class AppConfigurationConverter implements Converter<Object> {

	@Autowired
	private AppConfigurationRepository appConfigurationRepository;
	
	private AppConfiguration appConfiguration;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			appConfiguration = appConfigurationRepository.findById(Long.parseLong(value)).get();
		}
		return appConfiguration;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((AppConfiguration) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}

