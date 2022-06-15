package br.com.vertzon.gconsenti.util.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.ScanSchedule;
import br.com.vertzon.gconsenti.domain.repository.ScanScheduleRepository;

@Component
@FacesConverter(forClass = ScanSchedule.class)
public class ScanScheduleConverter implements Converter<Object> {

	@Autowired
	private ScanScheduleRepository scanScheduleRepository;

	private ScanSchedule scanSchedule;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			scanSchedule = scanScheduleRepository.findById(Long.parseLong(value)).get();
		}
		return scanSchedule;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((ScanSchedule) value).getId();
			String retrn = (id == null ? null : id.toString());
			return retrn;
		}
		return null;
	}
}