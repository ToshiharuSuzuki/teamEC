package com.internousdev.magenda.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class GoLoginAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	public String execute(){

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		if(Boolean.valueOf(String.valueOf(session.get("savedLoginId")))==false){
			session.remove("tempLoginId");
		}

		session.remove("loginIdErrorMessageList");
		session.remove("passwordErrorMessageList");

		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
