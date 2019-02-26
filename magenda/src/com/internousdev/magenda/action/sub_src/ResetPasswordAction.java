package com.internousdev.magenda.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport implements SessionAware{

	private int backFlag;
	private Map<String, Object> session;

	public String execute() {

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		session.remove("loginIdErrorMessageList");
		session.remove("passwordErrorMessageList");
		session.remove("passwordIncorrectErrorMessageList");
		session.remove("newPasswordErrorMessageList");
		session.remove("reConfirmationNewPasswordErrorMessageList");
		session.remove("newPasswordIncorrectErrorMessageList");
		
		if(backFlag != 1) {
			session.remove("loginId");			
		}
		
		return SUCCESS;
	}
	

	public int getBackFlag() {
		return backFlag;
	}


	public void setBackFlag(int backFlag) {
		this.backFlag = backFlag;
	}


	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
