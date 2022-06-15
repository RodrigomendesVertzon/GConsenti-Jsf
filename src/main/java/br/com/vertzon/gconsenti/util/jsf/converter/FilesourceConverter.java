package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.Filesource;
import br.com.vertzon.gconsenti.domain.repository.FilesourceRepository;

@Component
@FacesConverter(forClass = Filesource.class)
public class FilesourceConverter implements Converter<Object> {
	
	@Autowired
	private FilesourceRepository filesourceRepository;

private Filesource filesource;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			filesource = filesourceRepository.findById(Long.parseLong(value)).get();
		}
		return filesource;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Filesource) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}
