package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.model.JsfLabel;
import br.com.vertzon.gconsenti.domain.model.User;
import br.com.vertzon.gconsenti.domain.model.enumerator.JSFBeanEnum;
import br.com.vertzon.gconsenti.domain.repository.JsfLabelRepository;
import br.com.vertzon.gconsenti.domain.service.UserService;
import br.com.vertzon.gconsenti.util.jsf.FacesUtil;

@Component
@Scope("view")
public class RegisterUserBean implements Serializable {
	private static final long serialVersionUID = 7274054190849026027L;
	
	@Autowired
	private UserService userService;
	@Autowired
	private RouterBean router;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;
	
	private User user;	
	private List<String> labels = new ArrayList<String>();
	private List<JsfLabel> jsfLabels = new ArrayList<JsfLabel>(); 
	
	@PostConstruct
	public void init() {
		this.user = new User();
		jsfLabels = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.USER_REG);
		
		for (JsfLabel jsfLabel : jsfLabels) {
			labels.add(jsfLabel.getLabel());
		}
	}
	
	public void register() throws BusinessRuleException {		
		try {
			userService.register(user);
			FacesUtil.submitSuccess("O usuário foi cadastrado com sucesso!", router.getSearchUsersPage());
		} catch (BusinessRuleException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
}
