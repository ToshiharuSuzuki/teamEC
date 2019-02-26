package com.internousdev.magenda.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateUserAction extends ActionSupport implements SessionAware{

	private int backFlag;
	private List<String> sexList=new ArrayList<String>();
	private static final String MALE="男性";
	private static final String FEMALE="女性";
	private Map<String, Object> session;

	public String execute() {

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		session.remove("familyNameErrorMessageList");
		session.remove("firstNameErrorMessageList");
		session.remove("familyNameKanaErrorMessageList");
		session.remove("firstNameKanaErrorMessageList");
		session.remove("emailErrorMessageList");
		session.remove("userLoginIdErrorMessageList");
		session.remove("passwordErrorMessageList");

		if(backFlag != 1) {
			session.remove("familyName");
			session.remove("firstName");
			session.remove("familyNameKana");
			session.remove("firstNameKana");
			session.remove("createLoginId");
			session.remove("createPassword");
			session.remove("email");
			session.put("sex", MALE);
		}
	    sexList.add(MALE);
	    sexList.add(FEMALE);
	    session.put("sexList", sexList);
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