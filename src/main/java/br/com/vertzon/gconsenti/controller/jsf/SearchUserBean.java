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
import br.com.vertzon.gconsenti.domain.repository.UserRepository;
import br.com.vertzon.gconsenti.domain.service.UserService;

@Component
@Scope("view")
public class SearchUserBean implements Serializable {
	private static final long serialVersionUID = -4603180026903804948L;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private JsfLabelRepository jsfLabelRepository;

	private List<User> users;
	private User user;

	private List<JsfLabel> userObjects = new ArrayList<>();
	private List<JsfLabel> genericObjects = new ArrayList<>();
	private List<String> userLabels = new ArrayList<>();
	private List<String> genericLabels = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		userObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.USER_SRC);
		genericObjects = jsfLabelRepository.findAllByJsfBean(JSFBeanEnum.GENERIC_SRC);
		
		for (JsfLabel userLabel : userObjects) {
			userLabels.add(userLabel.getLabel());
		}
		
		for (JsfLabel genericLabel : genericObjects) {
			genericLabels.add(genericLabel.getLabel());
		}
		
		this.clear();
	}
	
	public void clear() {
		this.users = this.userRepository.findAll();
		this.user = new User();
	}

	public void search() {
		if (this.user != null) {
			this.users = this.userService.search(user);
		} else {
			this.clear();
		}

		this.user = new User();
	}
	
	public void delete() {
		try {
			userService.delete(user.getId());
			users.remove(user);
		} catch (BusinessRuleException e) {
			e.getMessage();
		}
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getUserLabels() {
		return userLabels;
	}

	public void setUserLabels(List<String> userLabels) {
		this.userLabels = userLabels;
	}

	public List<String> getGenericLabels() {
		return genericLabels;
	}

	public void setGenericLabels(List<String> genericLabels) {
		this.genericLabels = genericLabels;
	}
	
}
