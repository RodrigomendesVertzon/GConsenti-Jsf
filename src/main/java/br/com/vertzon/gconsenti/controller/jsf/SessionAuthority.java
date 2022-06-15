package br.com.vertzon.gconsenti.controller.jsf;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.vertzon.gconsenti.domain.model.enumerator.ProfileEnum;

@Component
@Scope("session")
public class SessionAuthority {

	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	public boolean isAdmin() {
		Collection<? extends GrantedAuthority> authenticationRoles = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authenticationRoles) {
			if (grantedAuthority.getAuthority().contains(ProfileEnum.ADMINISTRATOR.getLabel())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isWebmaster() {
		Collection<? extends GrantedAuthority> authenticationRoles = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authenticationRoles) {
			if (grantedAuthority.getAuthority().contains(ProfileEnum.WEBMASTER.getLabel())) {
				return true;
			}
		}
		return false;
	}
}