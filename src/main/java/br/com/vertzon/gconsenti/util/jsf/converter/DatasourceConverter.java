package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.Datasource;
import br.com.vertzon.gconsenti.domain.repository.DatasourceRepository;

@Component
@FacesConverter(forClass=Datasource.class)
public class DatasourceConverter implements Converter<Object> {

	@Autowired
	private DatasourceRepository datasourceRepository;
	
	private Datasource datasource = new Datasource();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			datasource = datasourceRepository.findById(Long.parseLong(value)).get();
		}
		return datasource;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Datasource) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}

}