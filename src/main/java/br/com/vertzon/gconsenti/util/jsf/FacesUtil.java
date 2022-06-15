package br.com.vertzon.gconsenti.util.jsf;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static void postRedirectGet() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(
				FacesContext.getCurrentInstance().getViewRoot().getViewId()
			);
		} catch (IOException e) {
			e.getMessage();		
		}
	}
	
	public static void postRedirectGetToPage(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			e.getMessage();		
		}
	}
	
	public static void submitSuccess(String message, String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			addSuccessMessage(message);
			FacesContext.getCurrentInstance().getExternalContext().redirect(
				FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() + page
			);
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public static void addSuccessMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(
			null, 
			new FacesMessage(
				FacesMessage.SEVERITY_INFO,
				message, message
			)
		);
	}
	
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(
			null, 
			new FacesMessage(
				FacesMessage.SEVERITY_ERROR,
				message, message
			)
		);
	}
}